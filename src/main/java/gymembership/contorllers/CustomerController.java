package gymembership.contorllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gymembership.entities.Customer;
import gymembership.service.ICustomerService;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("customer") 
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	//obtener la lista de clientes
	@GetMapping(value = "/list")
	public String listar(Model model) {
		model.addAttribute("title", "Lista de Clientes");
		model.addAttribute("customers", customerService.findAll());
		return "list";
	}
	
	//Crea y muestra el formulario clientes
	@GetMapping (value = "/form")
		public String create(Map<String, Object> model) {
			
		Customer customer = new Customer();
		model.put("customer", customer);
		model.put("title", "Formulario de Clientes");
		return "form";
		}
	
	//Editar clientes. Le paso el id por la url y busco por el id en la db
	@RequestMapping(value = "/form/{id}",method = RequestMethod.GET)
	public String edit(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Customer customer = null;
		
		//Si el id es mayor que cero lo busca en la DB, si no redirige a list
		if(id>0) {
			customer = customerService.findOne(id);
			if(customer == null) {
				flash.addFlashAttribute("error", "El ID no existe");
				return "redirect:/list";
			}
		}else {
			flash.addFlashAttribute("error", "El ID no puede ser cero");
			return "redirect:/list";
		}
		
		model.put("customer", customer);
		model.put("title", "Editar Cliente");
		return "form";		
	}
	
	//Guarda un nuevo cliente
	//pasar @Valid para que tome la validación y BindingResult para ver que no contenga errores
	//El RedirectAttributes es para dar mensajes flash
	@PostMapping(value="/form")
	public String save(@Valid Customer customer, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
	
		//Corroboro que no exista algún error. De ser así vuelve al formulario 
		if(result.hasErrors()) {
			model.addAttribute("title", "Formulario de Clientes");
			return "form";
		}
		//Pregunto si el id es distinto de null para saber si es una deicion o si esta creando uno nuevo
		String mensajeFlash = (customer.getId() !=null )? "Cliente editado con éxito" : "Cliente creado con éxito";
		
		
		customerService.save(customer);
		status.setComplete();
		//Agrego el mensaje en caso de éxito
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}
	
	//elimina cliente
	@RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Long id,  RedirectAttributes flash) {
		
		//Si el id existe lo elimina
		if(id>0) {
			customerService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con éxito");
		}
		return "redirect:listar";
	}
	
}

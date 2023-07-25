package gymembership.contorllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import gymembership.dao.ICustomerDao;
import gymembership.entities.Customer;
import jakarta.validation.Valid;

@Controller
public class CustomerController {
	
	@Autowired
	private ICustomerDao customerDao;
	
	//obtener la lista de clientes
	@GetMapping(value = "/list")
	public String listar(Model model) {
		model.addAttribute("title", "Lista de Clientes");
		model.addAttribute("customers", customerDao.findAll());
		return "list";
	}
	
	//Crea y muestra el formulario clientes
	@GetMapping (value = "/form")
		public String create(Map<String, Object> model) {
			
		Customer customer = new Customer();
		model.put("customer", customer);
		model.put("titel", "Formulario de Clientes");
		return "form";
		}
	
	//Guarda un nuevo cliente
	//pasar @Valid para que tome la validación y BindingResult para ver que no contenga errores
	@PostMapping(value="/form")
	public String save(@Valid Customer customer, BindingResult result, Model model) {
	
		//Corroboro que no exista algún error. De ser así vuelve al formulario 
		if(result.hasErrors()) {
			model.addAttribute("titel", "Formulario de Clientes");
			return "form";
		}
		
		customerDao.save(customer);
		return "redirect:listar";
	}
	
}

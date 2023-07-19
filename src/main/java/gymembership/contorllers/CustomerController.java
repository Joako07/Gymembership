package gymembership.contorllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gymembership.dao.ICustomerDao;

@Controller
public class CustomerController {
	
	@Autowired
	private ICustomerDao customerDao;
	
	//obtener la lista de clientes
	@GetMapping(value = "/list")
	public String listar(Model model) {
		model.addAttribute("title", "List Customers");
		model.addAttribute("customers", customerDao.findAll());
		return "list";
	}
}

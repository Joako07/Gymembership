package gymembership.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gymembership.dao.ICustomerDao;
import gymembership.entities.Customer;

//Patron de diseño "facade"(fachada) un unico punto de acceso a diferentes daos
@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerDao customerDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Customer> findAll() {
		return (List<Customer>) customerDao.findAll(); //Casteo a List por que el findAll en CrudRepository devuelve un iterable 
	}

	@Override
	@Transactional
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	@Transactional(readOnly = true)
	public Customer findOne(Long id) {
		return customerDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		customerDao.deleteById(id);
		
	}

}

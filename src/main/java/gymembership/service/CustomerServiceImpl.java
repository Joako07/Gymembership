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
		return customerDao.findAll();
	}

	@Override
	@Transactional
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	@Transactional(readOnly = true)
	public Customer findOne(Long id) {
		return customerDao.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		customerDao.delete(id);
		
	}

}

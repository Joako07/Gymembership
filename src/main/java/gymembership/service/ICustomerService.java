package gymembership.service;

import java.util.List;

import gymembership.entities.Customer;

public interface ICustomerService {

	public List<Customer> findAll();

	public void save(Customer customer);
	
	public Customer findOne(Long id);
	
    public void delete(Long id);
}

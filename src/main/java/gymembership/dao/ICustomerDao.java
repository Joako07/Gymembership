package gymembership.dao;

import java.util.List;

import gymembership.entities.Customer;

public interface ICustomerDao {
	
	public List<Customer> findAll();

}

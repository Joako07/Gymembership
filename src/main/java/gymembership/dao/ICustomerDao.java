package gymembership.dao;

import org.springframework.data.repository.CrudRepository;

import gymembership.entities.Customer;

public interface ICustomerDao extends CrudRepository <Customer, Long>{
	
	
}

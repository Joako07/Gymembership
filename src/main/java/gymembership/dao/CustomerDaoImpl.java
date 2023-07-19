package gymembership.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gymembership.entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Repository
public class CustomerDaoImpl implements ICustomerDao {

	@PersistenceContext
	private EntityManager em;
	
	//Metodo para obtener la lista completa.
	@Transactional(readOnly=true)
	@SuppressWarnings("unchecked") //Hacemos que ignore las advertencias de eclipce
	@Override
	public List<Customer> findAll() {
		return em.createQuery("from Customer").getResultList();
	}
}


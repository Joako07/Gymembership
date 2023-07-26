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

	// Metodo para obtener la lista completa.
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked") // Hacemos que ignore las advertencias de eclipce
	@Override
	public List<Customer> findAll() {
		return em.createQuery("from Customer").getResultList();
	}

	@Override
	@Transactional
	public void save(Customer customer) {
		// Preguntamos si el id existe, es decir es distinto de nulo y si es mayor que
		// cero.
		if (customer.getId() != null && customer.getId() > 0) {
			// Si existe lo actualizo con merge
			em.merge(customer);
		} else {
			// Si no existe lo creo con persist
			em.persist(customer);
		}
	}

	@Override
	public Customer findOne(Long id) {
		return em.find(Customer.class, id);
	}
}

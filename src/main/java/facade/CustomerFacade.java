package facade;
/*
 * author paepke
 * version 1.0
 */

import entity.Customer;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;

    private CustomerFacade() {
    }

    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Customer> getAll() {
        EntityManager entityManager = getEntityManager();
        try{
            return entityManager.createNamedQuery("Customer.getAll",Customer.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Customer getById(long id) {
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.find(Customer.class, id);
        } finally {
            entityManager.close();
        }
    }

    public Customer create(Customer customer) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return customer;
    }
}

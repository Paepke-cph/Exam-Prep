package facade;
/*
 * author paepke
 * version 1.0
 */

import entity.Order;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class OrderFacade {

    private static OrderFacade instance;
    private static EntityManagerFactory emf;

    private OrderFacade() {
    }

    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static OrderFacade getOrderFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OrderFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Order> getAll() {
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.createNamedQuery("Order.getAll", Order.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    public List<Order> getByCustomer(long id) {
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.createNamedQuery("Order.getByCustomer", Order.class).setParameter("id", id).getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Order getById(long id) {
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.find(Order.class, id);
        } finally {
            entityManager.close();
        }
    }

    public Order create(Order order) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(order);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return order;
    }
}

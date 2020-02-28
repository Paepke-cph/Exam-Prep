package facade;
/*
 * author paepke
 * version 1.0
 */

import entity.ItemType;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class ItemTypeFacade {

    private static ItemTypeFacade instance;
    private static EntityManagerFactory emf;

    private ItemTypeFacade() {
    }

    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static ItemTypeFacade getItemTypeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ItemTypeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<ItemType> getAll() {
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.createNamedQuery("ItemType.getAll", ItemType.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    public ItemType getById(long id) {
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.find(ItemType.class, id);
        } finally {
            entityManager.close();
        }
    }

    public ItemType create(ItemType itemtype) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(itemtype);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return itemtype;
    }
}

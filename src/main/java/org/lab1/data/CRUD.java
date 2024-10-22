package org.lab1.data;

import org.eclipse.persistence.exceptions.DatabaseException;
import org.lab1.data.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CRUD {
    static String persistenceName = "default";

    static Map<String, String> classMap = new HashMap<String, String>() {{
        put(MagicCity.class.getName(), "creature_location_id");
        put(Coordinates.class.getName(), "coordinates_id");
        put(Ring.class.getName(), "ring_id");
    }};

    public static void update(Object o) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(o);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    public static void add(Object o) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
        } catch (DatabaseException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    public static void delete(Object o) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.merge(o));
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    public static <T> T find(Class<T> classname, long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceName);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        T res = em.find(classname, id);
        em.getTransaction().commit();
        em.close();
        emf.close();

        return res;
    }

    public static User getUserByLogin(String login){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceName);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<User> res = em.createQuery("select o from org.lab1.data.entity.User o where o.login = \"" + login + "\"").getResultList();
        em.getTransaction().commit();
        em.close();
        emf.close();
        if (res.isEmpty())
            return null;
        return res.get(0);
    }

    public static <T> List<T> findAll(Class<T> classname) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceName);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<T> res = em.createQuery("select o from " + classname.getName() + " o").getResultList();
        em.getTransaction().commit();
        em.close();
        emf.close();
        return res;
    }

    public static <T> List<BookCreature> findBookCreatureByClassId(Class<T> classname, long id) {
        return findAll(BookCreature.class).stream().filter(bookCreature -> {
            if (classname == Coordinates.class)
                return bookCreature.getRing().getId() == id;
            if (classname == Ring.class){
                long ringId = bookCreature.getRing().getId();
                return ringId == id;
            }

            if (classname == MagicCity.class)
                return bookCreature.getCreatureLocation().getId() == id;
            return false;
        }).collect(Collectors.toList());
    }
}

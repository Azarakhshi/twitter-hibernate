package connection;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtils {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY;

    private HibernateUtils() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (ENTITY_MANAGER_FACTORY == null) {
            ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("twitter");
        }
        return ENTITY_MANAGER_FACTORY;
    }
}
package com.imsavva.hibernatetestapp.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * A utility class that helps us to obtain SessionFactory object. While
 * obtaining for the first time, it reads Hibernate configuration from an xml
 * config file.
 * 
 * @author Savva Kodeikin
 *
 */
public class HibernateUtils {

    private static final String HIBERNATE_ANNOTATION_CONFIG_FILE = "hibernate-annotation.cfg.xml";
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        Configuration config = new Configuration();
        config.configure(HIBERNATE_ANNOTATION_CONFIG_FILE);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties())
                .build();
        SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }

        return sessionFactory;
    }
}

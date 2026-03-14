package com.inventory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            // Create the Configuration instance from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            
            // Register your Entity classes here
            configuration.addAnnotatedClass(Product.class);

            // Building the ServiceRegistry is the modern, stable way to build the factory
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            
            System.out.println("Hibernate SessionFactory Created Successfully!");
        } catch (Throwable ex) {
            // Log the exception to the console so you can see exactly what is missing
            System.err.println("Initial SessionFactory creation failed: " + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
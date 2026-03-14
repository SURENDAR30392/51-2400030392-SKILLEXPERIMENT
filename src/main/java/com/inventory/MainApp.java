package com.inventory;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        seedDatabase(); // Adds new records if database is empty

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("--- Skill 3: HQL Operations ---");
            
            // Task 3: Sorting
            System.out.println("\n--- Sorted by Price (ASC) ---");
            displayAll(session, "FROM Product ORDER BY price ASC");

            // Task 5: Pagination
            System.out.println("\n--- Pagination: First 3 ---");
            Query<Product> query = session.createQuery("FROM Product", Product.class);
            query.setFirstResult(0);
            query.setMaxResults(3);
            query.list().forEach(System.out::println);

            // Task 6: Aggregates
            System.out.println("\n--- Aggregates ---");
            Long total = (Long) session.createQuery("SELECT count(p) FROM Product p").uniqueResult();
            System.out.println("Total Products: " + total);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }

    // This is the method that was causing your error
    public static void displayAll(Session session, String hql) {
        List<Product> products = session.createQuery(hql, Product.class).list();
        for (Product p : products) {
            System.out.println(p);
        }
    }

    private static void seedDatabase() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Long count = (Long) session.createQuery("SELECT count(p) FROM Product p").uniqueResult();
            if (count < 5) {
                session.save(new Product("Laptop", "Electronics", 45000.0, 5));
                session.save(new Product("Mouse", "Hardware", 1200.0, 20));
                session.save(new Product("Phone", "Electronics", 15000.0, 10));
                System.out.println("Database Seeded!");
            }
            tx.commit();
        }
    }
}
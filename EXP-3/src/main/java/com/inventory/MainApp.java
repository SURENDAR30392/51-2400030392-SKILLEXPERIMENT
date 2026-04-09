package com.inventory;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        // Task 2: Insert 5-8 records (Seeding only if needed)
        seedDatabase();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            // Tasks 3 & 4: Sorting
            System.out.println("\n--- Task 3a: Sorted by Price (ASC) ---");
            session.createQuery("FROM Product ORDER BY price ASC", Product.class).list().forEach(System.out::println);
            
            System.out.println("\n--- Task 3b: Sorted by Price (DESC) ---");
            session.createQuery("FROM Product ORDER BY price DESC", Product.class).list().forEach(System.out::println);

            System.out.println("\n--- Task 4: Sorted by Quantity (Highest First) ---");
            session.createQuery("FROM Product ORDER BY quantity DESC", Product.class).list().forEach(System.out::println);

            // Task 5: Pagination
            System.out.println("\n--- Task 5a: First 3 Products ---");
            paginate(session, 0, 3);
            
            System.out.println("\n--- Task 5b: Next 3 Products ---");
            paginate(session, 3, 3);

            // Task 6: Aggregates
            System.out.println("\n--- Task 6: Aggregates ---");
            Long count = (Long) session.createQuery("SELECT count(p) FROM Product p").uniqueResult();
            Object[] minMax = (Object[]) session.createQuery("SELECT min(p.price), max(p.price) FROM Product p").uniqueResult();
            System.out.println("Total Products: " + count);
            System.out.println("Min Price: " + minMax[0] + " | Max Price: " + minMax[1]);

            // Task 7: Group By Description
            System.out.println("\n--- Task 7: Grouped by Description ---");
            List<Object[]> groups = session.createQuery("SELECT p.description, count(p) FROM Product p GROUP BY p.description").list();
            groups.forEach(row -> System.out.println(row[0] + ": " + row[1]));

            // Task 8: Price Range Filter
            System.out.println("\n--- Task 8: Price Filter (100 to 1000) ---");
            Query<Product> rangeQuery = session.createQuery("FROM Product WHERE price BETWEEN :min AND :max", Product.class);
            rangeQuery.setParameter("min", 100.0);
            rangeQuery.setParameter("max", 1000.0);
            rangeQuery.list().forEach(System.out::println);

            // Task 9: LIKE Patterns
            System.out.println("\n--- Task 9: LIKE Patterns (Starting with 'M') ---");
            session.createQuery("FROM Product WHERE name LIKE 'M%'", Product.class).list().forEach(System.out::println);

        } catch (Exception e) {
            System.err.println("Error during HQL execution: " + e.getMessage());
            e.printStackTrace();
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }

    private static void paginate(Session s, int start, int size) {
        Query<Product> q = s.createQuery("FROM Product", Product.class);
        q.setFirstResult(start);
        q.setMaxResults(size);
        q.list().forEach(System.out::println);
    }

    private static void seedDatabase() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            // We check the count so we don't keep adding the same items every time you run the app
            Long count = (Long) session.createQuery("SELECT count(p) FROM Product p").uniqueResult();
            if (count < 5) {
                session.save(new Product("Monitor", "Electronics", 12000.0, 5));
                session.save(new Product("Keyboard", "Hardware", 1500.0, 20));
                session.save(new Product("Mango", "Fruit", 80.0, 50));
                session.save(new Product("Apple", "Fruit", 150.0, 30));
                session.save(new Product("Charger", "Electronics", 900.0, 15));
                System.out.println("Database Seeded Successfully!");
            }
            tx.commit();
        } catch (Exception e) {
            System.err.println("Seeding failed: " + e.getMessage());
        }
    }
}
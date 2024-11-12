package fr.digi.cda2024.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("movies");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        System.out.println(em);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}

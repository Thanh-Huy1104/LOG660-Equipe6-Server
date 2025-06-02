package com.equipe6.util;

import com.equipe6.model.Personne;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HibernateUtilTest {
    public static void main(String[] args) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Personne p = new Personne();
            p.setIdPersonne("P1001");
            p.setNom("Nguyen");
            p.setLieuNaissance("Montreal");
            p.setPhoto("https://example.com/photo.jpg");
            p.setBiographie("Développeur passionné par les bases de données et le cinéma.");

            // Set birth date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = sdf.parse("1999-05-15");
            p.setDateNaissance(birthDate);

            session.persist(p);
            tx.commit();

            System.out.println("✅ Personne inserted successfully!");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }
}

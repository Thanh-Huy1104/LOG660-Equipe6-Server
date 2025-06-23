package com.equipe6.util;

import com.equipe6.model.Utilisateur;
import com.equipe6.security.PasswordUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PasswordMigrationTool {

    private static final int BATCH_SIZE = 30;

    public static void main(String[] args) {
        boolean dryRun = false; // Set to true to test without committing

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Query<Utilisateur> query = session.createQuery("FROM Utilisateur", Utilisateur.class);
            List<Utilisateur> utilisateurs = query.list();

            int updatedCount = 0;
            int skippedCount = 0;
            int batchCount = 0;

            for (int i = 0; i < utilisateurs.size(); i++) {
                Utilisateur u = utilisateurs.get(i);
                String oldPassword = u.getMotDePasse();

                if (oldPassword != null && !oldPassword.startsWith("$2a$")) {
                    String hashed = PasswordUtils.hashPassword(oldPassword);
                    u.setMotDePasse(hashed);
                    session.merge(u);
                    updatedCount++;
                } else {
                    skippedCount++;
                }

                batchCount++;

                if (batchCount % BATCH_SIZE == 0) {
                    session.flush();
                    session.clear();
                    System.out.printf("✅ Processed %d users so far...%n", i + 1);
                }
            }

            if (dryRun) {
                tx.rollback();
                System.out.printf("🧪 Dry run complete. Would hash: %d | Already hashed: %d%n", updatedCount, skippedCount);
            } else {
                tx.commit();
                System.out.printf("✅ Migration complete. Hashed: %d | Skipped (already hashed): %d%n", updatedCount, skippedCount);
            }

        } catch (Exception e) {
            System.err.println("❌ Password migration failed:");
            e.printStackTrace();
        }
    }
}

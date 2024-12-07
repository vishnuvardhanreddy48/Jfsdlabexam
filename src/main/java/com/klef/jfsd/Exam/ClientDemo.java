package com.klef.jfsd.Exam;

import org.hibernate.Session;
public class ClientDemo {
    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure();
        SessionFactory factory = cfg.buildSessionFactory();
       
        
//         insertDepartment(factory);

         deleteDepartment(factory, 2); 

        factory.close();
    }

    private static void insertDepartment(SessionFactory factory) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Department dept1 = new Department("CSE", "C Block", "JSK");

        session.save(dept1);

        tx.commit();
        session.close();
        System.out.println("departments inserted");
    }

    private static void deleteDepartment(SessionFactory factory, int deptId) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        String hql = "DELETE FROM Department WHERE id = :n";	
        int rowsAffected = session.createQuery(hql)
                                  .setParameter("n", deptId)
                                  .executeUpdate();

        tx.commit();
        session.close();

		if (rowsAffected > 0) {
			System.out.println("Department with ID " + deptId + " deleted.");
		} else {
			System.out.println("No department found with ID " + deptId);
		}
	}
}

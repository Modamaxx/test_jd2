package service.HibernateCommunications.OneToOne;

import org.hibernate.Session;
import service.HibernateCommunications.OneToOne.model.Department;
import service.HibernateCommunications.OneToOne.model.Employer;

import java.sql.SQLException;

public class Task {
    public static void main(String[] args) throws SQLException {
        Session sessionOne = HibernateUtil.getSessionFactory().openSession();
        sessionOne.beginTransaction();

        Department department = new Department("Колхоз 777");

        sessionOne.save(department);


        Employer employer = new Employer();
        employer.setName("9999");
        employer.setDepartment(department);

        System.out.println(employer);

        Employer employee = sessionOne.get(Employer.class, 1);



        sessionOne.getTransaction().commit();


        HibernateUtil.shutdown();
    }
}

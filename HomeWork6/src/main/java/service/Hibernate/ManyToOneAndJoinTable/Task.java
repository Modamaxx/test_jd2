package service.Hibernate.ManyToOneAndJoinTable;



import org.hibernate.Session;
import service.Hibernate.ManyToOneAndJoinTable.model.Department;
import service.Hibernate.ManyToOneAndJoinTable.model.Employer;

import java.sql.SQLException;

public class Task {
    public static void main(String[] args) throws SQLException {
        Session sessionOne = HibernateUtil.getSessionFactory().openSession();
        sessionOne.beginTransaction();

        Department department = new Department("Колхоз 777");

        sessionOne.save(department);


//        Employer employer = new Employer();
//        employer.setName("9999");
//        employer.setDepartment(department);




        sessionOne.getTransaction().commit();


        HibernateUtil.shutdown();
    }
}

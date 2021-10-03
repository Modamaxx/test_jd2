package service.Hibernate.ManyToOneAndOneToMany;

import org.hibernate.Session;
import service.Hibernate.ManyToOneAndOneToMany.model.Department;
import service.Hibernate.ManyToOneAndOneToMany.model.Employer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

public class Task {
    public static void main(String[] args) throws SQLException {
        Session sessionOne = HibernateUtil.getSessionFactory().openSession();
        sessionOne.beginTransaction();
        CriteriaBuilder criteriaBuilder = HibernateUtil.getSessionFactory().createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Employer> criteriaQuery = criteriaBuilder.createQuery(Employer.class);

        Root<Employer> itemRoot = criteriaQuery.from(Employer.class);

        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(itemRoot.get("name"), "9999")
                )
        );

        List<Employer> resultList = sessionOne.createQuery(criteriaQuery).getResultList();

        for (Employer employee : resultList) {
            System.out.println(employee);
        }





        //        sessionOne.beginTransaction();
//
//        Department department = new Department("Колхоз 777");
//
//        sessionOne.save(department);
//
//
//        Employer employer = new Employer();
//        employer.setName("9999");
//
//        employer.setDepartment(department);
//            sessionOne.save(employer);






        sessionOne.getTransaction().commit();


        HibernateUtil.shutdown();
    }
}
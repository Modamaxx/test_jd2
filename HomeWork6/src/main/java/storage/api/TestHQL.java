package storage.api;

import model.Employer;
import org.hibernate.Session;
import service.Hibernate.OneToOne.model.Department;
import storage.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

public class TestHQL {
    public static void main(String[] args) throws SQLException {
        Session sessionOne = storage.HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = HibernateUtil.getSessionFactory().createEntityManager().getCriteriaBuilder();
        CriteriaQuery<model.Employer> criteriaQuery = criteriaBuilder.createQuery(model.Employer.class);

        Root<model.Employer> itemRoot = criteriaQuery.from(Employer.class);

        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(itemRoot.get("name"), "Aaron")
                )
        );
        List<Employer> resultList = sessionOne.createQuery(criteriaQuery).getResultList();

        for (Employer employee : resultList) {
            System.out.println(employee);
        }
    }
}

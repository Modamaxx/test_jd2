package storage.Hibernate;

import model.Department;
import model.Employer;
import model.Position;
import model.dto.EPredicateOperator;
import model.dto.ESalaryOperator;
import model.dto.EmployerSearchFilter;
import model.dto.PageableFilter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import storage.api.IEmployerStorage;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HQLEmployerStorage implements IEmployerStorage {

    private final static HQLEmployerStorage instance = new HQLEmployerStorage();
    private final SessionFactory sessionFactory;
    private final String PATH = "D:\\Java\\courses\\DZ\\Home\\HomeWork6\\src\\main\\resources\\FileForRead\\Employers.txt";


    public HQLEmployerStorage() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public int addEmployer(Employer employer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int id = (int) session.save(employer);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    @Override
    public Employer get(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Employer employer = session.get(Employer.class, id);
        session.getTransaction().commit();
        session.close();
        return employer;
    }

    @Override
    public void generationEmployers(int[] arrDepartment, int[] arrPosition) throws IOException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Path path = Paths.get(PATH);
        Scanner scannerName = new Scanner(path);
        for (int i = 0; i < 10_000; i++) {
            Employer employer = new Employer();

            employer.setName(scannerName.next());
            employer.setSalary(Math.random() * 100);

            Department department = new Department(arrDepartment[(int) (Math.random() * (5))]);
            Position position = new Position(arrPosition[(int) (Math.random() * (10))]);

            employer.setDepartment(department);
            employer.setPosition(position);
            session.save(employer);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Employer> page(PageableFilter filter) {
        Session session =sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Employer> cr = cb.createQuery(Employer.class);
        Root<Employer> root = cr.from(Employer.class);
        cr.select(root);

        Query  query = session.createQuery(cr);

        query.setFirstResult(filter.getSize() * (filter.getPage()- 1));
        query.setMaxResults( filter.getSize());

        List<Employer> results = query.getResultList();


        return results;
    }

    @Override
    public int countEmployer() {
        return 0;
    }

    @Override
    public List<Employer> pageFilter(EmployerSearchFilter filter) {
        Session sessionOne = HibernateUtil.getSessionFactory().openSession();
        sessionOne.beginTransaction();

        CriteriaBuilder criteriaBuilder = HibernateUtil.getSessionFactory().createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Employer> criteriaQuery = criteriaBuilder.createQuery(Employer.class);

        Root<Employer> itemRoot = criteriaQuery.from(Employer.class);

        List<Predicate> predicates = new ArrayList<>();
        if(!filter.getName().equals("")){
            predicates.add(criteriaBuilder.equal(itemRoot.get("name"), filter.getName()));
        }

        if (!filter.getSalary().equals("")){
            ESalaryOperator operator = filter.getSalaryOperator();
            if(operator == null){
                operator = ESalaryOperator.GREAT_OR_EQUAL;
            }
            Predicate predicate;
            switch (operator){
                case GREAT_OR_EQUAL:
                    predicate = criteriaBuilder.ge(itemRoot.get("salary"), Double.valueOf(filter.getSalary()));
                    break;
                case LESS_OR_EQUAL:
                    predicate = criteriaBuilder.le(itemRoot.get("salary"), Double.valueOf(filter.getSalary()));
                    break;
                default:
                    predicate = null;
            }

            if(predicate == null){
                throw new IllegalArgumentException("Я не знаю как обработать переданный поисковый оператор");
            }

            predicates.add(predicate);
        }

        EPredicateOperator predicateOperator = filter.getPredicateOperator();

        if(predicateOperator == null){
            predicateOperator = EPredicateOperator.AND;
        }

        Predicate[] predicatesArr = predicates.toArray(new Predicate[0]);

        Expression<Boolean> restriction;

        if(EPredicateOperator.AND.equals(predicateOperator)){
            restriction = criteriaBuilder.and(predicatesArr);
        } else {
            restriction = criteriaBuilder.or(predicatesArr);
        }

        criteriaQuery.where(restriction);

        org.hibernate.query.Query<Employer> query = sessionOne.createQuery(criteriaQuery);

        int size = filter.getSize();

        if(size == 0){
            size = 50;
        }

        query.setMaxResults(size);
        query.setFirstResult((filter.getPage()-1) * size);

        List<Employer> resultList = query.getResultList();

        sessionOne.getTransaction().commit();

        return resultList;
    }

    public static HQLEmployerStorage getInstance() {
        return instance;
    }
}

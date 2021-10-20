package storage.Hibernate;

import model.Department;
import model.Employer;
import model.Position;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import storage.SQL.DepartmentStorage;
import storage.api.IDepartmentStorage;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HQLDepartmentStorage implements IDepartmentStorage {

    private final int NUMBER_DEPARTMENT = 5;
    private final String PATH = "D:\\Java\\courses\\DZ\\Home\\HomeWork6\\src\\main\\resources\\FileForRead\\Departments.txt";
    private final SessionFactory sessionFactory;

    public HQLDepartmentStorage(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void generationDepartments() throws IOException {
        Session session = this.sessionFactory.openSession();

        Path path = Paths.get(PATH);
        Scanner scanner = new Scanner(path);
        for (int i = 0; i < NUMBER_DEPARTMENT; i++) {
            String name = scanner.next();
            String parent = scanner.next();

            if (parent.equals("Empty")) {
                session.beginTransaction();
                Department department = new Department(name);
                session.save(department);
                session.getTransaction().commit();

                continue;
            }

            int idParent = getIdName(parent);

            session.beginTransaction();

            Department department = new Department(name, idParent);
            session.save(department);
            session.getTransaction().commit();


        }

    }

    @Override
    public int getIdName(String name) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = sessionFactory.createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Integer> cr = criteriaBuilder.createQuery(Integer.class);
        Root<Department> itemRoot = cr.from(Department.class);

        cr.select(itemRoot.get("id")).where(criteriaBuilder.equal(itemRoot.get("name"), name));

        Query query = session.createQuery(cr);
        Integer singResult = (Integer) query.getSingleResult();
        session.getTransaction().commit();
        session.close();

        return singResult;
    }

    @Override
    public List<Department> pageDepartment() {
        Session session =sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Department> cr = cb.createQuery(Department.class);
        Root<Department> root = cr.from(Department.class);
        cr.select(root);

        Query  query = session.createQuery(cr);
        List<Department> results = query.getResultList();
        return results;
    }

    @Override
    public List<Employer> cardDepartment(int id) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder= sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Employer> cr= criteriaBuilder.createQuery(Employer.class);
        Root <Employer> itemRoot=cr.from(Employer.class);

        cr.where(criteriaBuilder.equal(itemRoot.get("department"),id));

        Query  query= session.createQuery(cr);
        List<Employer> resultList = query.getResultList();

        session.getTransaction().commit();
        session.close();
        return resultList;
    }

    @Override
    public ArrayList<String> readFile() throws IOException {
        ArrayList<String> str = new ArrayList<>();
        Path path = Paths.get(PATH);
        Scanner scanner = new Scanner(path);
        for (int i = 0; i < NUMBER_DEPARTMENT; i++) {
            str.add(scanner.next());
            scanner.next();
        }
        return str;
    }
}

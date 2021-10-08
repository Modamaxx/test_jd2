package storage.Hibernate;

import model.Department;
import model.Employer;
import model.Position;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import storage.api.IPositionStorage;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HQLPositionStorage implements IPositionStorage {
    private static final HQLPositionStorage instance = new HQLPositionStorage();
    private final String PATH = "D:\\Java\\courses\\DZ\\Home\\HomeWork6\\src\\main\\resources\\FileForRead\\Position.txt";
    private final int NUMBER_POSITIONS = 10;
    private final SessionFactory sessionFactory;

    public HQLPositionStorage() {
        this.sessionFactory=HibernateUtil.getSessionFactory();
    }

    @Override
    public void generationPosition() throws IOException {
        Session session= this.sessionFactory.openSession();
        Path path = Paths.get(PATH);
        Scanner scanner = new Scanner(path);
        session.beginTransaction();
        for (int i = 0; i < NUMBER_POSITIONS; i++) {
            Position position = new Position(scanner.next());
            session.save(position);
        }

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Position> pagePosition() {
        Session session =sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Position> cr = cb.createQuery(Position.class);
        Root<Position> root = cr.from(Position.class);
        cr.select(root);

        Query  query = session.createQuery(cr);
        List<Position> results = query.getResultList();
        return results;
    }

    @Override
    public List<Employer> cardPosition(int id) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder= sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Employer> cr= criteriaBuilder.createQuery(Employer.class);
        Root <Employer> itemRoot=cr.from(Employer.class);

        cr.where(criteriaBuilder.equal(itemRoot.get("position"),id));

        Query  query= session.createQuery(cr);
        List<Employer> resultList = query.getResultList();

        session.getTransaction().commit();
        session.close();
        return resultList;
    }

    @Override
    public int getIdName(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder= sessionFactory.createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Integer> cr= criteriaBuilder.createQuery(Integer.class);
        Root<Position> itemRoot = cr.from(Position.class);

        cr.select(itemRoot.get("id")).where(criteriaBuilder.equal(itemRoot.get("name"),name));

        Query query=session.createQuery(cr);
        Integer singResult = (Integer) query.getSingleResult();
        session.getTransaction().commit();
        session.close();

        return singResult;
    }

    @Override
    public ArrayList<String> readFile() throws IOException {
        ArrayList<String> str = new ArrayList<>();
        Path path = Paths.get(PATH);
        Scanner scanner = new Scanner(path);
        for (int i = 0; i < NUMBER_POSITIONS; i++) {
            str.add(scanner.next());
        }
        return str;
    }

    public static HQLPositionStorage getInstance(){
        return instance;
    }
}

package storage.SQL;

import model.Department;
import model.Employer;
import model.Position;
import model.dto.EPredicateOperator;
import model.dto.ESalaryOperator;
import model.dto.EmployerSearchFilter;
import model.dto.PageableFilter;
import org.hibernate.Session;
import org.hibernate.query.Query;
import storage.Hibernate.HibernateUtil;
import storage.api.IEmployerStorage;

import javax.persistence.criteria.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class EmployerStorage implements IEmployerStorage {
    private final String PATH = "D:\\Java\\courses\\DZ\\Home\\HomeWork6\\src\\main\\resources\\FileForRead\\Employers.txt";
    private static final EmployerStorage instance = new EmployerStorage();

    private EmployerStorage() {

    }

    public int addEmployer(Employer employer) {
        try (Connection con = ConnectionStorage.getInstance();) {
            try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.employers(\n" +
                    "name, salary, position, department)" +
                    "VALUES (?,?,?,?);", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, employer.getName());
                preparedStatement.setDouble(2, employer.getSalary());
                preparedStatement.setInt(3, employer.getPosition().getId());
                preparedStatement.setInt(4, employer.getDepartment().getId());
                preparedStatement.executeUpdate();
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys();) {
                    generatedKeys.next();
                    return Integer.parseInt(String.valueOf(generatedKeys.getLong(1)));
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных");
        }

    }

    public Employer get(int id) {
        String sql = "SELECT  employers.name,employers.salary,\n" +
                "departments.name as department,\n" +
                "positions.name as positions\n" +
                "From application.employers\n" +
                "Join application.departments on employers.department=departments.id\n" +
                "Join application.positions on employers.position=positions.id\n" +
                "where employers.id=?";
        try (Connection con = ConnectionStorage.getInstance();
             PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String name = resultSet.getString(1);
            Double salary = resultSet.getDouble(2);
            String nameDepartment = resultSet.getString(3);
            String namePosition = resultSet.getString(4);
            Employer employer = new Employer(name, salary, nameDepartment, namePosition);
            return employer;

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
    }

    public void generationEmployers(int[] arrDepartment, int[] arrPosition) {
        try (Connection con = ConnectionStorage.getInstance();
             Statement statement = con.createStatement();) {
            try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.employers(\n" +
                    "name, salary, position, department)" +
                    "VALUES (?,?,?,?);")) {
                Path path = Paths.get(PATH);
                Scanner scannerName = new Scanner(path);

                for (int i = 0; i < 10_000; i++) {
                    preparedStatement.setString(1, scannerName.next());
                    preparedStatement.setDouble(2, Math.random() * 100);
                    preparedStatement.setInt(3, arrPosition[(int) (Math.random() * (10))]);
                    preparedStatement.setInt(4, arrDepartment[(int) (Math.random() * (5))]);
                    preparedStatement.addBatch();
                    if (i % 1_000 == 0) {
                        preparedStatement.executeBatch();
                    }

                }
            }


        } catch (SQLException | IOException e) {
            throw new IllegalStateException("Ошибка работы с базой данных");
        }
    }

    public List<Employer> page(PageableFilter filter) {
        String sql = "SELECT employers.id,employers.name,employers.salary,\n" +
                "                departments.name as department,\n" +
                "                positions.name as positions \n" +
                "                From application.employers \n" +
                "                Join application.departments on employers.department=departments.id \n" +
                "                Join application.positions on employers.position=positions.id " +
                "LIMIT ? OFFSET ?";
        try (Connection con = ConnectionStorage.getInstance();
             PreparedStatement statement = con.prepareStatement(sql);) {

            statement.setInt(1, filter.getSize());
            statement.setInt(2, filter.getSize() * (filter.getPage()- 1));
            List<Employer> list = new LinkedList<>();
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Employer employer = new Employer();
                    Position position = new Position();
                    Department department = new Department();
                    employer.setId(resultSet.getInt(1));
                    employer.setName(resultSet.getString(2));
                    employer.setSalary(resultSet.getDouble(3));
                    position.setName(resultSet.getString(4));
                    employer.setPosition(position);
                    department.setName(resultSet.getString(5));
                    employer.setDepartment(department);
                    list.add(employer);
                }

            }
            return list;
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }

    }


    public int countEmployer() {
        String sql = "select count(employers.id) from application.employers ";
        try (Connection con = ConnectionStorage.getInstance();
             Statement statement = con.createStatement();) {
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getInt(1);

        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
    }

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

        Query<Employer> query = sessionOne.createQuery(criteriaQuery);

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

    public static EmployerStorage getInstance() {
        return instance;
    }

}

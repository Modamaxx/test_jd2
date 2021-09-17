package storage;

import model.Department;
import model.Employer;
import model.Position;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class EmployerStorage {
    private final String PATH = "D:\\Java\\courses\\DZ\\Home\\HomeWork6\\src\\main\\resources\\Employers.txt";
    private static final EmployerStorage instance = new EmployerStorage();

    private EmployerStorage() {

    }

    public int addEmployer(Employer employer, int idDepartment, int idPosition) {
        try (Connection con = ConnectionStorage.getInstance();) {
            try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.employers(\n" +
                    "name, salary, position, department)" +
                    "VALUES (?,?,?,?);", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, employer.getName());
                preparedStatement.setDouble(2, employer.getSalary());
                preparedStatement.setInt(3, idPosition);
                preparedStatement.setInt(4, idDepartment);
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

                for (int i = 0; i < 10000; i++) {
                    preparedStatement.setString(1, scannerName.next());
                    preparedStatement.setDouble(2, Math.random());
                    preparedStatement.setInt(3, arrPosition[(int) (Math.random() * (9)) + 1]);
                    preparedStatement.setInt(4, arrDepartment[(int) (Math.random() * (4)) + 1]);
                    preparedStatement.executeUpdate();
                }
            }


        } catch (SQLException | IOException e) {
            throw new IllegalStateException("Ошибка работы с базой данных");
        }
    }

    public List<Employer> employerCard() {
        String sql = "SELECT employers.id, employers.name,employers.salary,\n" +
                "                departments.name as department,\n" +
                "                positions.name as positions \n" +
                "                From application.employers \n" +
                "                Join application.departments on employers.department=departments.id \n" +
                "                Join application.positions on employers.position=positions.id";
        try (Connection con = ConnectionStorage.getInstance();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);) {
            List<Employer> list = new LinkedList<>();

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

            return list;
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }

    }

    public List<Employer> employerCardTest(int pageNumber) {
        String sql = "SELECT employers.name,employers.salary,\n" +
                "                departments.name as department,\n" +
                "                positions.name as positions \n" +
                "                From application.employers \n" +
                "                Join application.departments on employers.department=departments.id \n" +
                "                Join application.positions on employers.position=positions.id " +
                "LIMIT ? OFFSET ?";
        try (Connection con = ConnectionStorage.getInstance();
             PreparedStatement statement = con.prepareStatement(sql);) {

            statement.setLong(1, 50);
            statement.setLong(2, 10L *pageNumber);
            List<Employer> list = new LinkedList<>();
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Employer employer = new Employer();
                    Position position = new Position();
                    Department department = new Department();
                    employer.setName(resultSet.getString(1));
                    employer.setSalary(resultSet.getDouble(2));
                    position.setName(resultSet.getString(3));
                    employer.setPosition(position);
                    department.setName(resultSet.getString(4));
                    employer.setDepartment(department);
                    list.add(employer);
                }

            }
            return list;
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }

    }

    public static EmployerStorage getInstance() {
        return instance;
    }

}

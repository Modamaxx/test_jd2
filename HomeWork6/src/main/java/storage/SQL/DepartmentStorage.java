package storage.SQL;

import model.Department;
import model.Employer;
import model.Position;
import storage.api.IDepartmentStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DepartmentStorage implements IDepartmentStorage {
    private static final DepartmentStorage instance = new DepartmentStorage();

    private final int NUMBER_DEPARTMENT = 5;
    private final String PATH = "D:\\Java\\courses\\DZ\\Home\\HomeWork6\\src\\main\\resources\\FileForRead\\Departments.txt";

    private DepartmentStorage() {

    }

    public void generationDepartments() {
        try (Connection con = ConnectionStorage.getInstance();) {
            try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.departments(name,parent)\n" +
                    "\tVALUES (?,?);");) {
                Path path = Paths.get(PATH);
                Scanner scanner = new Scanner(path);
                for (int i = 0; i < NUMBER_DEPARTMENT; i++) {
                    preparedStatement.setString(1, scanner.next());
                    String name = scanner.next();
                    if (name.equals("Empty")) {
                        preparedStatement.setNull(2, Types.INTEGER);
                        preparedStatement.executeUpdate();
                        continue;
                    }
                    int id = getIdName(name);
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                }

            }


        } catch (SQLException | IOException e) {
            throw new IllegalStateException("Ошибка работы с базой данных");
        }
    }

    public int getIdName(String name) {
        String sql = "SELECT id FROM application.departments where name=?";
        try (Connection con = ConnectionStorage.getInstance();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);


        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
    }

    public List<Department> pageDepartment() {
        String sql = "Select d1.id,d1.name,d2.name\n" +
                "from application.departments d1\n" +
                " left join application.departments d2 on d1.parent=d2.id";
        try (Connection con = ConnectionStorage.getInstance();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);) {
            List<Department> departments = new ArrayList<>();

            while (resultSet.next()) {
                departments.add(new Department(resultSet.getInt(1),
                        resultSet.getString(2)));
            }

            return departments;
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
    }

    public List<Employer> cardDepartment(int id) {
        String sql = "SELECT employers.id, employers.name,employers.salary,\n" +
                "                departments.name as department,\n" +
                "                positions.name as positions \n" +
                "                From application.employers \n" +
                "                Join application.departments on employers.department=departments.id \n" +
                "                Join application.positions on employers.position=positions.id" +
                " where employers.department=?";
        try (Connection con = ConnectionStorage.getInstance();
             PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Employer> employers = new LinkedList<>();

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
                employers.add(employer);
            }

            return employers;
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
    }

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

    public static DepartmentStorage getInstance() {
        return instance;
    }
}

package storage;

import model.Department;
import model.Employer;
import model.Position;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

public class DBStorage {
    private static final DBStorage instance = new DBStorage();
    private final String PATH = "D:\\Java\\courses\\DZ\\Home\\HomeWork6\\src\\main\\resources";

    static {
        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Ошибка загрузки драйвера", e);
        }
    }

    public long addEmployer(Employer employer, int idDepartment, int idPosition) {
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crm",
                "postgres", "eputet91");
             Statement statement = con.createStatement();) {
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
                    return generatedKeys.getLong(1);
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
                "where employers.id=\n" + String.valueOf(id);
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/crm",
                "postgres", "eputet91");
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);) {
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

    public int getIdName(String name, char c) {
        String sql;
        if (c == 'D') {
            sql = "SELECT id FROM application.departments where name='" + name + "'";

        } else
            sql = "SELECT id FROM application.positions where name='" + name + "'";
        return getId(sql);
    }

    public int getId(String sql) {
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/crm",
                "postgres", "eputet91");
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);) {
            resultSet.next();

            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
    }

    public void generationDepartments() {
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crm",
                "postgres", "eputet91");
             Statement statement = con.createStatement();) {
            try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.departments(name,parent)\n" +
                    "\tVALUES (?,?);");) {
                Path path = Paths.get(PATH + "/Departments.txt");
                Scanner scanner = new Scanner(path);
                String sql = "SELECT id FROM application.departments where name=";
                for (int i = 0; i < 5; i++) {
                    preparedStatement.setString(1, scanner.next());
                    String name = scanner.next();
                    if (name.equals("Empty")) {
                        preparedStatement.setNull(2, Types.INTEGER);
                        preparedStatement.executeUpdate();
                        continue;
                    }
                    int id = getId(sql + "'" + name + "'");
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                }

            }


        } catch (SQLException | IOException e) {
            throw new IllegalStateException("Ошибка работы с базой данных");
        }
    }

    public void generationPosition() {
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crm",
                "postgres", "eputet91");
             Statement statement = con.createStatement();) {
            try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.positions(\n" +
                    "\t name) VALUES (?);");) {
                Path path = Paths.get(PATH + "/Position.txt");
                Scanner scanner = new Scanner(path);
                for (int i = 0; i < 10; i++) {
                    preparedStatement.setString(1, scanner.next());
                    preparedStatement.executeUpdate();
                }

            }


        } catch (SQLException | IOException e) {
            throw new IllegalStateException("Ошибка работы с базой данных");
        }
    }

    public void generationEmployers(int[] arrDepartment, int[] arrPosition) {
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crm",
                "postgres", "eputet91");
             Statement statement = con.createStatement();) {
            try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.employers(\n" +
                    "name, salary, position, department)" +
                    "VALUES (?,?,?,?);")) {
                Path path = Paths.get(PATH + "/Employers.txt");
                Scanner scannerName = new Scanner(path);

                for (int i = 0; i < 10000; i++) {
                    preparedStatement.setString(1, scannerName.next());
                    preparedStatement.setDouble(2, Math.random());
                    preparedStatement.setInt(3, arrPosition[(int) Math.random() * (9) + 1]);
                    preparedStatement.setInt(4, arrDepartment[(int) Math.random() * (4) + 1]);
                    preparedStatement.executeUpdate();
                }
            }


        } catch (SQLException | IOException e) {
            throw new IllegalStateException("Ошибка работы с базой данных");
        }
    }

    public  ArrayList<String> positionCard() {
        String sql ="SELECT name FROM application.positions\n" +
                "ORDER BY id ASC ";
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/crm",
                "postgres", "eputet91");
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);) {
            ArrayList<String> str= new ArrayList<>();
            while (resultSet.next()) {
                str.add(resultSet.getString(1));
            }

            return str;
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
    }

    public List<Employer> employerCard(){
        String sql ="SELECT employers.name,employers.salary,\n" +
                "                departments.name as department,\n" +
                "                positions.name as positions \n" +
                "                From application.employers \n" +
                "                Join application.departments on employers.department=departments.id \n" +
                "                Join application.positions on employers.position=positions.id";
        try (Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/crm",
                "postgres", "eputet91");
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);) {
            List<Employer> list= new LinkedList<>();

            while (resultSet.next()) {
                Employer employer= new Employer();
                Position position= new Position();
                Department department= new Department();
                employer.setName(resultSet.getString(1));
                employer.setSalary(resultSet.getDouble(2));
                position.setName(resultSet.getString(3));
                employer.setPosition(position);
                department.setName(resultSet.getString(4));
                employer.setDepartment(department);
                list.add(employer);
            }

            return list;
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }

    }

    public static DBStorage getInstance() {
        return instance;
    }
}

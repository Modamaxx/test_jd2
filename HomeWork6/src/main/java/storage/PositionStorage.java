package storage;

import model.Department;
import model.Employer;
import model.Position;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PositionStorage {
    private static final PositionStorage instance = new PositionStorage();
    private final String PATH = "D:\\Java\\courses\\DZ\\Home\\HomeWork6\\src\\main\\resources\\Position.txt";
    private final int NUMBER_POSITIONS = 10;

    private PositionStorage() {

    }

    public void generationPosition() {
        try (Connection con = ConnectionStorage.getInstance();) {
            try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO application.positions(\n" +
                    "\t name) VALUES (?);");) {
                Path path = Paths.get(PATH);
                Scanner scanner = new Scanner(path);
                for (int i = 0; i < NUMBER_POSITIONS; i++) {
                    preparedStatement.setString(1, scanner.next());
                    preparedStatement.executeUpdate();
                }

            }


        } catch (SQLException | IOException e) {
            throw new IllegalStateException("Ошибка работы с базой данных");
        }
    }

    public ArrayList<Position> pagePosition() {
        String sql = "SELECT id, name FROM application.positions\n" +
                "ORDER BY id ASC ";
        try (Connection con = ConnectionStorage.getInstance();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);) {
            ArrayList<Position> str = new ArrayList<>();
            while (resultSet.next()) {
                str.add(new Position(resultSet.getInt(1), resultSet.getString(2)));
            }

            return str;
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка работы с базой данных", e);
        }
    }

    public List<Employer> cardPosition(int id) {
        String sql = "SELECT employers.id, employers.name,employers.salary,\n" +
                "                departments.name as department,\n" +
                "                positions.name as positions \n" +
                "                From application.employers \n" +
                "                Join application.departments on employers.department=departments.id \n" +
                "                Join application.positions on employers.position=positions.id" +
                " where employers.position=?";
        try (Connection con = ConnectionStorage.getInstance();
             PreparedStatement preparedStatement = con.prepareStatement(sql);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
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

    public int getIdName(String name) {
        String sql = "SELECT id FROM application.positions where name=?";
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

    public ArrayList<String> readFile() throws IOException {
        ArrayList<String> str = new ArrayList<>();
        Path path = Paths.get(PATH);
        Scanner scanner = new Scanner(path);
        for (int i = 0; i < NUMBER_POSITIONS; i++) {
            str.add(scanner.next());
        }
        return str;
    }

    public static PositionStorage getInstance() {
        return instance;
    }
}

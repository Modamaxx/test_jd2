package service;

import storage.DepartmentStorage;
import storage.EmployerStorage;
import storage.PositionStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CalculationsService {
    private final String PATH = "D:\\Java\\courses\\DZ\\Home\\HomeWork6\\src\\main\\resources";

    private static final CalculationsService instance = new CalculationsService();
    private final DepartmentStorage departmentStorage;
    private final PositionStorage positionStorage;
    private final EmployerStorage employerStorage;

    public CalculationsService() {
        this.departmentStorage = DepartmentStorage.getInstance();
        this.positionStorage = PositionStorage.getInstance();
        this.employerStorage=EmployerStorage.getInstance();
    }

    public int[] Department() throws IOException {
        int[] arr = new int[5];
        Path path = Paths.get(PATH + "/Departments.txt");
        Scanner scanner = new Scanner(path);
        for (int i = 0; i < 5; i++) {
            arr[i] = departmentStorage.getIdName(scanner.next());
            scanner.next();
        }
        return arr;
    }

    public int[] Position() throws IOException {
        int[] arr = new int[10];
        Path path = Paths.get(PATH + "/Position.txt");
        Scanner scanner = new Scanner(path);
        for (int i = 0; i < 10; i++) {
            arr[i] = positionStorage.getIdName(scanner.next());
        }
        return arr;
    }

    public String pageCount(double limit){
          int countEmployers= employerStorage.countEmployer();
          return String.valueOf((long) Math.ceil((double) countEmployers/limit));
    }

    public static CalculationsService getInstance() {
        return instance;
    }
}

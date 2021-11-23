package service;

import service.api.ICalculationService;
import storage.SQL.DepartmentStorage;
import storage.SQL.EmployerStorage;
import storage.SQL.PositionStorage;
import storage.api.IDepartmentStorage;
import storage.api.IEmployerStorage;
import storage.api.IPositionStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CalculationsService implements ICalculationService {
    private final String PATH = "D:\\Java\\courses\\DZ\\Home\\HomeWork6\\src\\main\\resources";

    private final IDepartmentStorage departmentStorage;
    private final IPositionStorage positionStorage;
    private final IEmployerStorage employerStorage;

    public CalculationsService(IDepartmentStorage iDepartmentStorage,
                               IPositionStorage iPositionStorage,
                               IEmployerStorage iEmployerStorage) {
        this.departmentStorage = iDepartmentStorage;
        this.positionStorage = iPositionStorage;
        this.employerStorage = iEmployerStorage;
    }

    public int[] Department() throws IOException {
        int[] arr = new int[5];
        Path path = Paths.get(PATH + "/FileForRead/Departments.txt");
        Scanner scanner = new Scanner(path);
        for (int i = 0; i < 5; i++) {
            arr[i] = departmentStorage.getIdName(scanner.next());
            scanner.next();
        }
        return arr;
    }

    public int[] Position() throws IOException {
        int[] arr = new int[10];
        Path path = Paths.get(PATH + "/FileForRead/Position.txt");
        Scanner scanner = new Scanner(path);
        for (int i = 0; i < 10; i++) {
            arr[i] = positionStorage.getIdName(scanner.next());
        }
        return arr;
    }

    public String pageCount(double limit) {
        int countEmployers = Math.toIntExact(employerStorage.countEmployer());
        return String.valueOf((long) Math.ceil((double) countEmployers / limit));
    }

}

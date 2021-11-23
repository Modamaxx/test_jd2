package service;

import model.Department;
import model.Employer;
import service.api.IDepartmentService;
import storage.api.IDepartmentStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class DepartmentService implements IDepartmentService {
    private final IDepartmentStorage departmentStorage;
    private final String PATH = "D:\\Java\\courses\\DZ\\Home\\HomeWork6\\src\\main\\resources\\FileForRead\\Departments.txt";

    public DepartmentService(IDepartmentStorage departmentStorage) {

        this.departmentStorage = departmentStorage;
    }

    public void generationDepartments(int number) throws IOException {
        Path path = Paths.get(PATH);
        Scanner scanner = new Scanner(path);
        for (int i = 0; i < number; i++) {
            String name = scanner.next();
            String parent = scanner.next();
            Department department;
            if (parent.equals("Empty")) {
                department = new Department(name);
            } else {
                int idParent = departmentStorage.getIdName(parent);
                department = new Department(name, idParent);
            }
            departmentStorage.save(department);
        }
    }

    public List<Department> pageDepartment() {
        return departmentStorage.pageDepartment();
    }

    public List<Employer> cardDepartment(int id) {
        return departmentStorage.cardDepartment(id);
    }
}

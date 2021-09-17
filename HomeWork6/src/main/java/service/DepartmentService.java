package service;

import model.Department;
import model.Employer;
import storage.DepartmentStorage;
import storage.EmployerStorage;
import storage.PositionStorage;

import java.util.ArrayList;
import java.util.List;

public class DepartmentService {
    private static final DepartmentService instance = new DepartmentService();
    private final DepartmentStorage departmentStorage;
    private DepartmentService() {

        this.departmentStorage=DepartmentStorage.getInstance();
    }
    public void generationDepartments(){
        departmentStorage.generationDepartments();
    }

    public List<Department> pageDepartment(){
        return  departmentStorage.pageDepartment();
    }
    public List<Employer> cardDepartment(int id){
        return departmentStorage.cardDepartment(id);
    }

    public static DepartmentService getInstance() {
        return instance;
    }
}

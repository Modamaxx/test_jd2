package service;

import model.AppParam;
import model.Department;
import model.Employer;
import service.api.IDepartmentService;
import storage.SQL.DepartmentStorage;
import storage.api.IDepartmentStorage;

import java.io.IOException;
import java.util.List;

public class DepartmentService implements IDepartmentService {
    private static final DepartmentService instance = new DepartmentService();
    private final IDepartmentStorage departmentStorage;
    private DepartmentService() {

        this.departmentStorage= AppParam.getInstance().getDepartmentStorage();
    }
    public void generationDepartments() throws IOException {
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

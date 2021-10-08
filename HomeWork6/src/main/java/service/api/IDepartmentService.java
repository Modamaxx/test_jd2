package service.api;

import model.Department;
import model.Employer;

import java.io.IOException;
import java.util.List;

public interface IDepartmentService {
    void generationDepartments() throws IOException;

    List<Department> pageDepartment();

    List<Employer> cardDepartment(int id);

}

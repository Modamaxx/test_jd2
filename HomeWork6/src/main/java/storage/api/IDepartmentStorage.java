package storage.api;

import model.Department;
import model.Employer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IDepartmentStorage {
    void generationDepartments() throws IOException;
    int getIdName(String name);
    List<Department> pageDepartment();
    List<Employer> cardDepartment(int id);
    ArrayList<String> readFile() throws IOException;

}

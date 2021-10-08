package service.api;

import model.Employer;
import model.dto.EmployerSearchFilter;
import model.dto.PageableFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IEmployerService {

    int addEmployer(Employer employer);
    ArrayList<String> readFilePositions() throws IOException;
    ArrayList<String> readFileDepartments() throws IOException;
    Employer get(int id);
    List<Employer> page(PageableFilter filter);
    void generationEmployers() throws IOException;
}

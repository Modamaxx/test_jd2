package storage.api;

import model.Employer;
import model.dto.EmployerSearchFilter;
import model.dto.PageableFilter;

import java.io.IOException;
import java.util.List;

public interface IEmployerStorage {
    int addEmployer(Employer employer);
    Employer get(int id);
    void generationEmployers(int[] arrDepartment, int[] arrPosition) throws IOException;
    List<Employer> page(PageableFilter filter);
    Long countEmployer();
    List<Employer> pageFilter(EmployerSearchFilter filter);

}

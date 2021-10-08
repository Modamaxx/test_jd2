package service;

import model.AppParam;
import model.Department;
import model.Employer;
import model.Position;
import model.dto.EmployerSearchFilter;
import model.dto.PageableFilter;
import service.api.IEmployerService;
import storage.SQL.DepartmentStorage;
import storage.SQL.EmployerStorage;
import storage.SQL.PositionStorage;
import storage.api.IDepartmentStorage;
import storage.api.IEmployerStorage;
import storage.api.IPositionStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployerService implements IEmployerService {
    private static final EmployerService instance = new EmployerService();
    private final IEmployerStorage employerStorage;
    private final IDepartmentStorage departmentStorage;
    private final IPositionStorage positionStorage;
    private final CalculationsService calculationsService;


    public EmployerService() {
        this.employerStorage = AppParam.getInstance().getEmployerStorage();
        this.departmentStorage = AppParam.getInstance().getDepartmentStorage();
        this.positionStorage = AppParam.getInstance().getPositionStorage();
        this.calculationsService = CalculationsService.getInstance();
    }

    public int addEmployer(Employer employer) {
        int idDepartment = departmentStorage.getIdName(employer.getDepartment().getName());
        int idPosition = positionStorage.getIdName(employer.getPosition().getName());

        Department department = new Department(idDepartment);
        Position position = new Position(idPosition);

        employer.setDepartment(department);
        employer.setPosition(position);

        return employerStorage.addEmployer(employer);
    }

    public ArrayList<String> readFilePositions() throws IOException {
        return positionStorage.readFile();
    }

    public ArrayList<String> readFileDepartments() throws IOException {
        return departmentStorage.readFile();
    }

    public Employer get(int id) {
        return employerStorage.get(id);
    }

    public List<Employer> page(PageableFilter filter) {

        return employerStorage.page(filter);
    }
    public List<Employer> pageFilter(EmployerSearchFilter filter) {

        return employerStorage.pageFilter(filter);
    }

    public void generationEmployers() throws IOException {
        int[] arrDepartment = calculationsService.Department();
        int[] arrPosition = calculationsService.Position();
        employerStorage.generationEmployers(arrDepartment, arrPosition);
    }

    public static EmployerService getInstance() {
        return instance;
    }

}

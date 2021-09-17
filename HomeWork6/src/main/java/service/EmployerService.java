package service;

import model.Employer;
import storage.DepartmentStorage;
import storage.EmployerStorage;
import storage.PositionStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployerService {
    private static final EmployerService instance = new EmployerService();
    private final EmployerStorage employerStorage;
    private final DepartmentStorage departmentStorage;
    private final PositionStorage positionStorage;
    private final CalculationsService calculationsService;


    public EmployerService() {
        this.employerStorage = EmployerStorage.getInstance();
        this.departmentStorage= DepartmentStorage.getInstance();
        this.positionStorage= PositionStorage.getInstance();
        this.calculationsService=CalculationsService.getInstance();
    }

    public int addEmployer(Employer employer) {
     int idDepartment=departmentStorage.getIdName(employer.getDepartment().getName());
     int idPosition=positionStorage.getIdName(employer.getPosition().getName());
        return employerStorage.addEmployer(employer,idDepartment,idPosition);
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
    public List<Employer> pageEmployer(){
        return employerStorage.employerCard();
    }

    public void generationEmployers() throws IOException {
        int [] arrDepartment=calculationsService.Department();
        int [] arrPosition=calculationsService.Position();
        employerStorage.generationEmployers(arrDepartment,arrPosition);
    }

    public List<Employer> employerCardTest(int pageNumber){
        return   employerStorage.employerCardTest(pageNumber);
    }

    public static EmployerService getInstance() {
        return instance;
    }
}
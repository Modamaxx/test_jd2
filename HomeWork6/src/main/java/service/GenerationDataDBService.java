package service;

import storage.DBStorage;

import java.io.IOException;

public class GenerationDataDBService {

    private static final GenerationDataDBService instance = new GenerationDataDBService();
    private final DBStorage dbStorage;
    private final CalculationsService calculationsService;

    public GenerationDataDBService() {
        dbStorage = DBStorage.getInstance();
        calculationsService=CalculationsService.getInstance();
    }

    public void generationDepartments(){
        dbStorage.generationDepartments();
    }
    public void generationEmployers() throws IOException {
     int [] arrDepartment=calculationsService.Department();
     int [] arrPosition=calculationsService.Position();
        dbStorage.generationEmployers(arrDepartment,arrPosition);
    }
    public void generationPosition(){
        dbStorage.generationPosition();
    }
    public static GenerationDataDBService getInstance() {
        return instance;
    }
}

package service;

import model.Employer;
import storage.DBStorage;

public class UserService {
    private static final UserService instance = new UserService();
    private final DBStorage dbStorage;

    public UserService() {
        dbStorage = DBStorage.getInstance();
    }

    public long addEmployer(Employer employer) {
     int idDepartment=dbStorage.getIdName(employer.getDepartment().getName(),'D');
     int idPosition=dbStorage.getIdName(employer.getPosition().getName(),'P');
        return dbStorage.addEmployer(employer,idDepartment,idPosition);
    }

    public Employer get(int id) {
        return dbStorage.get(id);
    }

    public static UserService getInstance() {
        return instance;
    }
}

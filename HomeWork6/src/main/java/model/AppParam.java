package model;

import storage.api.IDepartmentStorage;
import storage.api.IEmployerStorage;
import storage.api.IPositionStorage;

import java.time.LocalDateTime;

public class AppParam {

    private static AppParam instance=new AppParam();
    private IDepartmentStorage departmentStorage;
    private IEmployerStorage employerStorage;
    private IPositionStorage positionStorage;

    private AppParam(){

    }

    public static void setInstance(AppParam instance) {
        AppParam.instance = instance;
    }

    public IDepartmentStorage getDepartmentStorage() {
        return departmentStorage;
    }

    public void setDepartmentStorage(IDepartmentStorage departmentStorage) {
        this.departmentStorage = departmentStorage;
    }

    public IEmployerStorage getEmployerStorage() {
        return employerStorage;
    }

    public void setEmployerStorage(IEmployerStorage employerStorage) {
        this.employerStorage = employerStorage;
    }

    public IPositionStorage getPositionStorage() {
        return positionStorage;
    }

    public void setPositionStorage(IPositionStorage positionStorage) {
        this.positionStorage = positionStorage;
    }

    public static AppParam getInstance(){
        return instance;
    }
}

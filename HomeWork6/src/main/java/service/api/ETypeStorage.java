package service.api;

import storage.Hibernate.HQLDepartmentStorage;
import storage.Hibernate.HQLEmployerStorage;
import storage.Hibernate.HQLPositionStorage;
import storage.SQL.DepartmentStorage;
import storage.SQL.EmployerStorage;
import storage.SQL.PositionStorage;
import storage.api.IDepartmentStorage;
import storage.api.IEmployerStorage;
import storage.api.IPositionStorage;

public enum ETypeStorage {
    HQL(HQLDepartmentStorage.getInstance(), HQLEmployerStorage.getInstance(), HQLPositionStorage.getInstance()),
    SQL (DepartmentStorage.getInstance(), EmployerStorage.getInstance(), PositionStorage.getInstance()),
    ;
    private final IDepartmentStorage departmentStorage;
    private final IEmployerStorage employerStorage;
    private final IPositionStorage positionStorage;

    ETypeStorage(IDepartmentStorage departmentStorage,
                 IEmployerStorage employerStorage,IPositionStorage positionStorage) {
        this.departmentStorage=departmentStorage;
        this.employerStorage=employerStorage;
        this.positionStorage=positionStorage;
    }
    public static ETypeStorage valueOfIgnoreCase(String name){
        for (ETypeStorage value : values()) {
            if(value.name().equalsIgnoreCase(name)){
                return value;
            }
        }
        throw new IllegalArgumentException("Неизвесный тип хранилища");
    }

    public  IDepartmentStorage getDepartmentStorage(){
        return departmentStorage;
    }
    public  IEmployerStorage getEmployerStorage(){
        return employerStorage;
    }
    public  IPositionStorage getPositionStorage(){
        return positionStorage;
    }

}

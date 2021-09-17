package service;

import model.Employer;
import model.Position;
import storage.DepartmentStorage;
import storage.EmployerStorage;
import storage.PositionStorage;

import java.util.ArrayList;
import java.util.List;

public class PositionService {

    private static final PositionService instance = new PositionService();
    private final PositionStorage positionStorage;

    private PositionService() {
        this.positionStorage= PositionStorage.getInstance();
    }

    public ArrayList<Position> pagePosition(){
        return positionStorage.pagePosition();
    }

    public List<Employer> cardPosition(int id){
      return this.positionStorage.cardPosition(id);
    }

    public void generationPosition(){
        positionStorage.generationPosition();
    }
    public static PositionService getInstance() {
        return instance;
    }
}

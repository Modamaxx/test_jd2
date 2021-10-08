package service;

import model.AppParam;
import model.Employer;
import model.Position;
import service.api.IPositionService;
import storage.SQL.PositionStorage;
import storage.api.IPositionStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PositionService implements IPositionService {

    private static final PositionService instance = new PositionService();
    private final IPositionStorage positionStorage;

    private PositionService() {
        this.positionStorage= AppParam.getInstance().getPositionStorage();
    }

    public List<Position> pagePosition(){
        return positionStorage.pagePosition();
    }

    public List<Employer> cardPosition(int id){
      return this.positionStorage.cardPosition(id);
    }

    public void generationPosition() throws IOException {
        positionStorage.generationPosition();
    }
    public static PositionService getInstance() {
        return instance;
    }
}

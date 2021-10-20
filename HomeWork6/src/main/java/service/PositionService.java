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

    private final IPositionStorage positionStorage;

    public PositionService(IPositionStorage positionStorage) {
        this.positionStorage= positionStorage;
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

}

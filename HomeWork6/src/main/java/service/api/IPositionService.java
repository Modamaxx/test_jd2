package service.api;

import model.Employer;
import model.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IPositionService {
    List<Position> pagePosition();

    List<Employer> cardPosition(int id);

    void generationPosition() throws IOException;
}

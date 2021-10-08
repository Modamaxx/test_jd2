package storage.api;

import model.Employer;
import model.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IPositionStorage {
    void generationPosition() throws IOException;
    List<Position> pagePosition();
    List<Employer> cardPosition(int id);
    int getIdName(String name);
    ArrayList<String> readFile() throws IOException;

}

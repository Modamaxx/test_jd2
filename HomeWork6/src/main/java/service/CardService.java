package service;

import model.Employer;
import storage.DBStorage;

import java.util.ArrayList;
import java.util.List;

public class CardService{
    private static final CardService instance = new CardService();
    private final DBStorage dbStorage;

    private CardService() {
        this.dbStorage = DBStorage.getInstance();
    }

    public ArrayList<String> positionCard(){
       return dbStorage.positionCard();
    }
    public List<Employer> employerCard(){
        return dbStorage.employerCard();
    }

    public static CardService getInstance() {
        return instance;
    }
}

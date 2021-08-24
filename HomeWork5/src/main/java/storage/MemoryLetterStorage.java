package storage;

import model.Letter;
import model.User;
import storage.api.ILetterStorage;
import storage.api.IUserStorage;

import java.time.LocalDate;

public class MemoryLetterStorage implements ILetterStorage {
    private static final MemoryLetterStorage instance= new MemoryLetterStorage();
    private final IUserStorage users;

    private MemoryLetterStorage() {
        this.users= MemoryUserStorage.getInstance();
    }

    public void addLetter(Letter letter,User user){
       user.getLetters().add(letter);
    }
    public static MemoryLetterStorage getInstance(){
        return instance;
    }
}

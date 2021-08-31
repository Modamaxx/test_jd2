package storage;

import model.Letter;
import model.User;
import storage.api.ILetterStorage;
import storage.api.IUserStorage;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileLetterStorage implements ILetterStorage {
    private static final FileLetterStorage instance = new FileLetterStorage();
    private final IUserStorage userStorage;

    public FileLetterStorage() {
        this.userStorage = FileUserStorage.getInstance();
    }

    @Override
    public void addLetter(Letter letter, User user) {

        user.getLetters().add(letter);
        userStorage.add(user);

    }
    public static FileLetterStorage getInstance(){
        return instance;
    }
}

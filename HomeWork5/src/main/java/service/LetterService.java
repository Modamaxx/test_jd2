package service;

import model.AppParam;
import model.Letter;
import model.User;
import service.api.ILetterService;
import storage.FileLetterStorage;
import storage.FileUserStorage;
import storage.MemoryLetterStorage;
import storage.MemoryUserStorage;
import storage.api.ILetterStorage;
import storage.api.IUserStorage;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LetterService implements ILetterService {
    private static final LetterService instance = new LetterService();
    private final ILetterStorage letterStorage;
    private final IUserStorage userStorage;

    private LetterService() {
        letterStorage = AppParam.getInstance().getLetterStorage();
        userStorage = AppParam.getInstance().getUserStorage();
    }

    @Override
    public boolean addLetter(String recipient, String message, String from) {


        Letter letter = new Letter();
        letter.setData(LocalDateTime.now().toString());
        letter.setFrom(from);
        letter.setMessage(message);

        User user = userStorage.get(recipient);
        if(user==null)
            return false;
        letterStorage.addLetter(letter,user);
        return true;

    }

    public static LetterService getInstance() {
        return instance;
    }
}

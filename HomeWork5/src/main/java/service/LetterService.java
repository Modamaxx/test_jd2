package service;

import model.Letter;
import model.User;
import service.api.ILetterService;
import storage.MemoryUserStorage;
import storage.api.IUserStorage;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class LetterService implements ILetterService {
    private static final LetterService instance= new LetterService();
    private final IUserStorage users;

    private LetterService() {
        this.users= MemoryUserStorage.getInstance();
    }

    public void addLetter(String recipient, String message,String from){
       User user= users.get(recipient);
       user.getLetters().add(new Letter(LocalDate.now().toString(), from,message));
    }
    public static LetterService getInstance(){
        return instance;
    }
}

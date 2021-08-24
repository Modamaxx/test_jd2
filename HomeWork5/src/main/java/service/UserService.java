package service;

import model.Letter;
import model.User;
import service.api.IUserService;
import service.api.IValidationService;
import storage.FileUserStorage;
import storage.MemoryUserStorage;
import storage.api.IUserStorage;

import java.util.Collection;

public class UserService implements IUserService {
    private static final UserService instance = new UserService();

    private final IValidationService validationService;
    private final IUserStorage userStorage;

    private UserService() {
        this.validationService = ValidationService.getInstance();
        this.userStorage = FileUserStorage.getInstance();
    }

    @Override
    public boolean signUp(User user) {
        if(validationService.validSignUp(user)){
            userStorage.add(user);
            return true;
        }
        return false;
    }

    @Override
    public User get(String login) {
       return userStorage.get(login);
    }

    @Override
    public Collection<User> getAll() {
       return this.userStorage.getAll();
    }



    public static UserService getInstance() {
        return instance;
    }
}

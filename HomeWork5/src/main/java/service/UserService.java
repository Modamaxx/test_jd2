package service;

import model.User;
import service.api.IUserService;
import service.api.IValidationService;
import storage.MemoryUserStorage;
import storage.api.IUserStorage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserService implements IUserService {
    private static final UserService instance = new UserService();

    private final IValidationService validationService;
    private final IUserStorage users;

    private UserService() {
        this.validationService = ValidationService.getInstance();
        this.users= MemoryUserStorage.getInstance();
    }

    @Override
    public boolean signUp(User user) {
        if(validationService.validSignUp(user)){
            users.add(user);
            return true;
        }
        return false;
    }

    @Override
    public void get(String login) {

    }

    @Override
    public void getAll() {

    }
    public static UserService getInstance() {
        return instance;
    }
}

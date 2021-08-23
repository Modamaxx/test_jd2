package service;

import model.User;
import service.api.IValidationService;
import storage.MemoryUserStorage;
import storage.api.IUserStorage;

public class ValidationService implements IValidationService {
    private static final ValidationService instance = new ValidationService();
    private final IUserStorage users;

    private ValidationService() {
        this.users = MemoryUserStorage.getInstance();
    }

    @Override
    public boolean validSignUp(User user) {
        if ((user.getLogin().equals("")) || (user.getPassword().equals(""))
                || (user.getBirthday().equals("")) || (user.getFio().equals(""))) {
            return false;
        }

        return true;
    }

    @Override
    public User validSignIn(String login, String password) {
        User user = users.get(login);
        if ((user.getLogin().equals(login)) && (user.getPassword().equals(password))) {
            return user;
        }

        return null;
    }


    public static ValidationService getInstance() {
        return instance;
    }
}

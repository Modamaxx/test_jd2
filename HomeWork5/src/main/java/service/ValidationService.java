package service;

import model.AppParam;
import model.User;
import service.api.IValidationService;
import storage.FileUserStorage;
import storage.MemoryUserStorage;
import storage.api.IUserStorage;

public class ValidationService implements IValidationService {
    private static final ValidationService instance = new ValidationService();
    private final IUserStorage users;

    private ValidationService() {
        this.users = AppParam.getInstance().getUserStorage();
    }

    @Override
    public boolean validSignUp(User user) {
        if ((user.getLogin().equals("")) || (user.getPassword().equals(""))
                || (user.getFio().equals("")) || (user.getBirthday().equals(""))) {
            return false;
        }

        return true;
    }

    @Override
    public User validSignIn(String login, String password) {
        User user = users.get(login);
        if (user == null)
            return null;
        if ((user.getLogin().equals(login)) && (user.getPassword().equals(password))) {
            return user;
        }
        return null;

    }


    public static ValidationService getInstance() {
        return instance;
    }
}

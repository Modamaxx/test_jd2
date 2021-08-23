package service.api;

import model.User;

public interface IValidationService {
    boolean validSignUp(User user);
    User validSignIn(String login,String password);
}

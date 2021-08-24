package service.api;

import model.User;

import java.util.Collection;

public interface IUserService {
  boolean signUp(User user);
  User get(String login);
  Collection<User> getAll();

}

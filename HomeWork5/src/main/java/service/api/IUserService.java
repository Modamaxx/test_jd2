package service.api;

import model.User;

import java.util.Collection;

public interface IUserService {
  boolean signUp(User user);
  void get(String login);
  Collection<User> getAll();
}

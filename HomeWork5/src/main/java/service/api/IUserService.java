package service.api;

import model.User;

import java.util.Collection;

public interface IUserService {
  void addUser(User user);
  User getUser(String login);
  Collection<User> getAll();

}

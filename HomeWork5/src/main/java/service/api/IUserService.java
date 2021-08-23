package service.api;

import model.User;

public interface IUserService {
  boolean signUp(User user);
  void get(String login);
  void getAll();
}

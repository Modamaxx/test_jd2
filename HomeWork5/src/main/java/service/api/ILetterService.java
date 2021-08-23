package service.api;

import model.User;

public interface ILetterService {
  void  addLetter(String recipient,String message, String from);
}

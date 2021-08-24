package storage.api;

import model.Letter;
import model.User;

public interface ILetterStorage {
  void  addLetter(Letter letter,User user);
}

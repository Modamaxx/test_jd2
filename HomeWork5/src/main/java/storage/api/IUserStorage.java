package storage.api;

import model.User;

import java.util.Collection;

public interface IUserStorage {
        User get(String login);
        void add(User user);
        Collection<User> getAll();
}

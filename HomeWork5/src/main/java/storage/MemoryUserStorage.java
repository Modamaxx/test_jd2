package storage;

import model.User;
import storage.api.IUserStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryUserStorage implements IUserStorage {
    private static final MemoryUserStorage instance = new MemoryUserStorage();
    private final Map<String, User> users = new HashMap<>();
    public static MemoryUserStorage getInstance(){
        return instance;
    }

    private MemoryUserStorage() {
    }

    @Override
    public User get(String login) {
        return this.users.get(login);
    }

    @Override
    public void add(User user) {
     if(this.users.containsKey(user.getLogin())){
         throw new IllegalArgumentException("Пользователь с логином " + user.getLogin() + " уже сущуствует");
     }
       this.users.put(user.getLogin(),user);
    }
}

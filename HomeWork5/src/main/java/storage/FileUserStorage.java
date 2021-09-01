package storage;

import model.Letter;
import model.User;
import storage.api.IUserStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FileUserStorage implements IUserStorage {
    private static final FileUserStorage instance = new FileUserStorage();
    private Map<String, User> users = new HashMap<>();
    private final String FILE_PATH = "D:\\Java\\courses\\DZ\\Home\\HomeWork5\\src\\main\\resources\\UsersData.dat";

    private FileUserStorage() {

    }

    @Override
    public User get(String login) {

        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Map<String, User> us = (Map<String, User>) ois.readObject();
            ois.close();
            return us.get(login);

        } catch (ClassNotFoundException | IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(User user) {
        if(!users.containsKey(user.getLogin())){
            throw new IllegalArgumentException("Пользователь с логином " + user.getLogin() + " уже сущуствует");
        }
        this.users.put(user.getLogin(), user);
        try {
            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Collection<User> getAll() {
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Map<String, User> us = (Map<String, User>) ois.readObject();
            ois.close();
            return us.values();

        } catch (ClassNotFoundException | IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return null;
    }

    public void init() {
        File f = new File(FILE_PATH);
        if (f.exists()) {
            try {
                FileInputStream fis = new FileInputStream(FILE_PATH);
                ObjectInputStream ois = new ObjectInputStream(fis);
                this.users = (Map<String, User>) ois.readObject();
            } catch (ClassNotFoundException | IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
    }

    public static FileUserStorage getInstance() {
        return instance;
    }
}

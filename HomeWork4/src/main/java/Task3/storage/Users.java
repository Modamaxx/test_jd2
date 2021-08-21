package Task3.storage;

import Task3.model.Person;

import java.util.ArrayList;

public class Users {
    private static final Users instance = new Users();
    private final static ArrayList<Person> users = new ArrayList<Person>();
    public static Users getInstance(){
        return instance;
    }

    private Users() {
    }

    public ArrayList<Person> getUsers() {
        return users;
    }
}

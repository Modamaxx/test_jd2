package Task3.service;

import Task3.controller.web.servlet.Registration;
import Task3.model.Person;
import Task3.storage.Users;

public class Validation {

    private static final Validation instance = new Validation();
    private final Users users;

    private Validation() {
        this.users=Users.getInstance();
    }

    public boolean validRegistration(Person person){
        if ((person.getLogin().equals("")) || (person.getPassword().equals(""))
                || (person.getBirthday().equals("")) || (person.getFio().equals(""))) {
            return false;
        }
        for (Person p: users.getUsers()) {
            if( person.getLogin().equals(p.getLogin())){
                return false;
            }
        }
        return true;
    }

    public Person validAuthorization(String login, String password) {
        for (Person p : users.getUsers()) {
            if ((p.getLogin().equals(login)) && (p.getPassword().equals(password))) {
                return p;
            }
        }
        return null;
    }



    public static Validation getInstance() {
        return instance;
    }
}

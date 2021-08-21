package Task3.service;

import Task3.model.Letter;
import Task3.model.Person;
import Task3.storage.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class WorkWithPerson {
    private static final WorkWithPerson instance = new WorkWithPerson();
    private final Validation validation;
    private final Users users;

    private WorkWithPerson() {
        this.validation = Validation.getInstance();
        this.users=Users.getInstance();
    }

    public boolean registration(Person person){
        if(validation.validRegistration(person)){
            users.getUsers().add(person);

            return true;
        }
        return false;
    }

    public Person givePerson(HttpServletRequest req){
        HttpSession session = req.getSession();
        Person person = (Person) session.getAttribute("person");
        return person;
    }

    public void addLetter(String recipient,Person person,HttpServletRequest req){
        for (Person p : users.getUsers()) {
            if (p.getLogin().equals(recipient)) {
                p.getLetters().add(new Letter(LocalDate.now().toString(), person.getLogin(), req.getParameter("message")));
            }
        }
    }





    public static WorkWithPerson getInstance() {
        return instance;
    }
}


package service.api;

import model.Person;
import service.api.IHandleRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionHandler implements IHandleRequest {

    private static final SessionHandler instance = new SessionHandler();

    private final static String PERSON_ATTRIBUTE_NAME = "person";

    @Override
    public void save(HttpServletRequest req, HttpServletResponse resp, Person person) {
        HttpSession session = req.getSession();
        session.setAttribute(PERSON_ATTRIBUTE_NAME, person);
    }

    @Override
    public Person get(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE_NAME);

        if(person==null){
            throw new IllegalArgumentException("Вы не сохранили данные в сессию");
        }

        return person;
    }

    public static SessionHandler getInstance() {
        return instance;
    }
}

package service.api;

import model.Person;
import service.api.IHandleRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.CodeSigner;
import java.security.Key;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class CookiesHandler implements IHandleRequest {
    private static final String FIRST_NAME_PARAM = "firstName";
    private static final String LAST_NAME_PARAM = "lastName";
    private static final String AGE_PARAM = "age";

    private final static CookiesHandler instance= new CookiesHandler();

    private CookiesHandler() {
    }

    private void saveCookies(HttpServletResponse resp, String Key, Person person) {
        Cookie cookie = new Cookie(Key, person.getFirstName());
        cookie.setMaxAge(Math.toIntExact(TimeUnit.DAYS.toSeconds(1)));
        resp.addCookie(cookie);
    }

    @Override
    public void save(HttpServletRequest req, HttpServletResponse resp, Person person) {
        saveCookies(resp, FIRST_NAME_PARAM, person);
        saveCookies(resp, LAST_NAME_PARAM, person);
        saveCookies(resp, AGE_PARAM, person);
    }

    @Override
    public Person get(HttpServletRequest req) {
        Person person = new Person();
        person.setFirstName(getValue(req, FIRST_NAME_PARAM));
        person.setFirstName(getValue(req, LAST_NAME_PARAM));
        person.setFirstName(getValue(req, AGE_PARAM));

        return null;
    }

    private String getValue(HttpServletRequest req, String key) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            String val = Arrays.stream(cookies)
                    .filter(c -> key.equalsIgnoreCase(c.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
            return val;
        } else {
            throw new IllegalArgumentException("Вы не сохранили данные в куки");
        }
    }

    public static CookiesHandler getInstance (){
        return instance;
    }
}

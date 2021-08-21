package Task3.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Person {
    private String login;
    private String password;
    private String fio;
    private String birthday;
    private ArrayList<Letter> letters;

    public Person(){
        letters= new ArrayList<Letter>();
    }

    public Person(String login, String password, String fio, String birthday) {
        this.login = login;
        this.password = password;
        this.fio = fio;
        this.birthday = birthday;
        this.letters = new ArrayList<Letter>();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        Person person = (Person) o;
        return Objects.equals(login, person.login) && Objects.equals(password, person.password) && Objects.equals(fio, person.fio) && Objects.equals(birthday, person.birthday) && Objects.equals(letters, person.letters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, fio, birthday, letters);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public ArrayList<Letter> getLetters() {
        return letters;
    }

    public void setLetters(ArrayList<Letter> letters) {
        this.letters = letters;
    }
}

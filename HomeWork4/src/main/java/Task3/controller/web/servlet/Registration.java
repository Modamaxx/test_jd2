package Task3.controller.web.servlet;

import Task3.model.Person;
import Task3.service.WorkWithPerson;
import Task3.service.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Registration", urlPatterns = "/signUp")
public class Registration extends HttpServlet {
    private final WorkWithPerson workWithPerson;
    public Registration() {

        this.workWithPerson = WorkWithPerson.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person = new Person(req.getParameter("login"), req.getParameter("password"),
                req.getParameter("fio"), req.getParameter("birthday"));
        if (workWithPerson.registration(person)) {
            HttpSession session = req.getSession();
            session.setAttribute("person", person);
            req.setAttribute("person", person);
            req.getRequestDispatcher("/views/menu.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "you have not entered all the data or this username is already occupied");
            req.getRequestDispatcher("/views/signUp.jsp").forward(req, resp);
        }
    }
}

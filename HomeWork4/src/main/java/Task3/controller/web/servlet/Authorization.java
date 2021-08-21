package Task3.controller.web.servlet;

import Task3.model.Person;
import Task3.service.Validation;
import Task3.service.WorkWithPerson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Authorization", urlPatterns = "/signIn")
public class Authorization extends HttpServlet {
    private final WorkWithPerson workWithPerson;
    private final Validation validation;

    public Authorization() {
        this.workWithPerson = WorkWithPerson.getInstance();
        this.validation = Validation.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/views/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person = validation.validAuthorization(req.getParameter("login"), req.getParameter("password"));

        if (person != null) {
            HttpSession session = req.getSession();
            session.setAttribute("person", person);
            req.setAttribute("person", person);
            req.getRequestDispatcher("/views/menu.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "The data entered is incorrect, or you have not registered");
            req.getRequestDispatcher("/views/signIn.jsp").forward(req, resp);
        }


    }
}

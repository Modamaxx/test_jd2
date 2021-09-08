package controllers.web.servlets;

import model.User;
import service.UserService;
import service.ValidationService;
import service.api.IUserService;
import service.api.IValidationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "SingUpServlet", urlPatterns = "/signUp")
public class SingUpServlet extends HttpServlet {
    private final IUserService userService;
    private final IValidationService validationService;


    public SingUpServlet() {
        this.userService = UserService.getInstance();
        this.validationService = ValidationService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String fio = req.getParameter("fio");
        String birthday = req.getParameter("birthday");
        User user = new User(login, password, fio, birthday, LocalDateTime.now());
        if (validationService.validSignUp(user)) {
            userService.addUser(user);

            HttpSession session = req.getSession();
            session.setAttribute("person", user);

            req.setAttribute("person", user);
            req.getRequestDispatcher("/views/menu.jsp").forward(req, resp);
        } else {
                req.setAttribute("error", "you have not entered all the data or this username is already occupied");
                req.getRequestDispatcher("/views/signUp.jsp").forward(req, resp);
        }

    }
}

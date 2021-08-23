package controllers.web.servlets;

import model.User;
import service.ValidationService;
import service.UserService;
import service.api.IUserService;
import service.api.IValidationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(name="SingInServlet",urlPatterns = "/signIn")
public class SingInServlet extends HttpServlet {
    private final IUserService userService;
    private final IValidationService validationService;

    public SingInServlet() {
        this.userService = UserService.getInstance();
        this.validationService = ValidationService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/views/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login=req.getParameter("login");
        String password=req.getParameter("password");
        User user = validationService.validSignIn(login,password);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("person", user);

            req.setAttribute("person", user);
            req.getRequestDispatcher("/views/menu.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "The data entered is incorrect, or you have not registered");
            req.getRequestDispatcher("/views/signIn.jsp").forward(req, resp);
        }


    }
}

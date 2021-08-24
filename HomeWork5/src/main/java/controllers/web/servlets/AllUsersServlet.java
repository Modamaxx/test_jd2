package controllers.web.servlets;

import model.User;
import service.UserService;
import service.api.IUserService;
import storage.api.IUserStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

@WebServlet(name = "AllUsersServlet", urlPatterns = "/user")
public class AllUsersServlet extends HttpServlet {
    private final IUserService userService;

    public AllUsersServlet() {
        this.userService = UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Collection<User> users= userService.getAll();
        req.setAttribute("users",users);
        req.getRequestDispatcher("/views/user.jsp").forward(req, resp);
    }
}

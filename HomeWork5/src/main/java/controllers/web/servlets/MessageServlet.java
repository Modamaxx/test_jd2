package controllers.web.servlets;

import model.User;
import service.UserService;
import service.api.IUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(name = "MessageServlet", urlPatterns = "/Message")

public class MessageServlet extends HttpServlet {
    private final IUserService userService;

    public MessageServlet() {
        this.userService = UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("person");
        User user1= userService.get(user.getLogin());
        req.setAttribute("letters", user1.getLetters());
        req.getRequestDispatcher("/views/message.jsp").forward(req, resp);
    }
}

package controllers.web.servlets;

import model.User;
import service.LetterService;
import service.api.ILetterService;
import storage.MemoryLetterStorage;
import storage.api.ILetterStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ChatsServlet", urlPatterns = "/chats")
public class ChatsServlet extends HttpServlet {
    private final ILetterService letterService;
    public ChatsServlet() {
        this.letterService = LetterService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/chats.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("person");

        String recipient=req.getParameter("recipient");
        String message= req.getParameter("message");

       if(!letterService.addLetter(recipient, message, user.getLogin())){
           req.setAttribute("error", "the data is specified incorrectly");
           req.getRequestDispatcher("/views/chats.jsp").forward(req, resp);
       }

        String path = req.getContextPath() + "/views/menu.jsp";
        resp.sendRedirect(path);
    }
}

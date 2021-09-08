package controllers.servlets;

import model.Employer;
import service.CardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name ="CardEmployersServlet",urlPatterns = "/CardEmployers")
public class CardEmployersServlet extends HttpServlet {
    private final CardService cardService;

    public CardEmployersServlet() {

        this.cardService = service.CardService.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employer> employers= cardService.employerCard();
        req.setAttribute("employers",employers);

        req.getRequestDispatcher("/views/cardAllEmployer.jsp").forward(req,resp);
    }
}

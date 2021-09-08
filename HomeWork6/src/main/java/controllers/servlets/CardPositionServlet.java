package controllers.servlets;

import service.CardService;
import service.GenerationDataDBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="CardPositionServlet", urlPatterns = "/CardPosition")
public class CardPositionServlet extends HttpServlet {
    private final CardService cardService;

    public CardPositionServlet() {

        this.cardService = service.CardService.getInstance();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      ArrayList<String> str= cardService.positionCard();
        req.setAttribute("str", str);

        req.getRequestDispatcher("/views/cardPosition.jsp").forward(req, resp);
    }
}

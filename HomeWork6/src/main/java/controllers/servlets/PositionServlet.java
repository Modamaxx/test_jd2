package controllers.servlets;

import model.Employer;
import model.Position;
import service.PositionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PositionServlet", urlPatterns = "/position")
public class PositionServlet extends HttpServlet {
    private final PositionService positionService;

    public PositionServlet() {
        this.positionService = PositionService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("id") != null) {
           String id =req.getParameter("id");
            List<Employer> employers=this.positionService.cardPosition(Integer.parseInt(id));
            req.setAttribute("employers",employers);
            req.getRequestDispatcher("/views/card/cardPosition.jsp").forward(req, resp);
        }

        List<Position> str = positionService.pagePosition();
        req.setAttribute("str", str);
        req.getRequestDispatcher("/views/page/pagePosition.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        positionService.generationPosition();
        req.getRequestDispatcher("/views/startApp.jsp").forward(req, resp);
    }
}

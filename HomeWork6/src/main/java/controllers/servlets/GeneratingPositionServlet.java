package controllers.servlets;

import service.GenerationDataDBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="GeneratingPositionServlet", urlPatterns = "/GeneratingPosition")
public class GeneratingPositionServlet extends HttpServlet {
    private final GenerationDataDBService generationDataDBService;

    public GeneratingPositionServlet() {
        this.generationDataDBService = GenerationDataDBService.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        generationDataDBService.generationPosition();
        req.getRequestDispatcher("/views/startApp.jsp").forward(req,resp);
    }
}

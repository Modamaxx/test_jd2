package controllers.servlets;

import service.GenerationDataDBService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GeneratingDepartmentServlet", urlPatterns = "/GeneratingDepartmentServlet")
public class GeneratingDepartmentServlet extends HttpServlet {
    private final GenerationDataDBService generationDataDBService;

    public GeneratingDepartmentServlet() {
        this.generationDataDBService = GenerationDataDBService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        generationDataDBService.generationDepartments();
    }

}

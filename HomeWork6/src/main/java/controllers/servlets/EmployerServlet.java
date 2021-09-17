package controllers.servlets;

import model.Employer;
import service.EmployerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployerServlet", urlPatterns = "/employer")
public class EmployerServlet extends HttpServlet {
    private final EmployerService employerService;

    public EmployerServlet() {
        this.employerService = EmployerService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Employer> employers = employerService.pageEmployer();
        req.setAttribute("employers", employers);
        req.getRequestDispatcher("/views/page/pageEmployer.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        employerService.generationEmployers();
    }
}

package controllers.servlets;

import model.Employer;
import service.CalculationsService;
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
    private final CalculationsService calculationsService;

    public EmployerServlet() {
        this.employerService = EmployerService.getInstance();
        this.calculationsService = CalculationsService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNumber = req.getParameter("pageNumber");
        String countEmployer= req.getParameter("countEmployer");

        String pageEnd = calculationsService.pageCount();
        List<Employer> employers = employerService.pageEmployer(pageNumber,countEmployer);
        req.setAttribute("employers", employers);
        req.setAttribute("pageNumber", pageNumber);
        req.setAttribute("countEmployer",countEmployer);
        req.setAttribute("pageEnd", pageEnd);
        req.getRequestDispatcher("/views/page/pageEmployer.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        employerService.generationEmployers();
    }
}

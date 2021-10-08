package controllers.servlets;

import model.Employer;
import model.dto.*;
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

        String name = req.getParameter("name");
        String page = req.getParameter("page");
        String size = req.getParameter("size");
        String salaryOperator = req.getParameter("salary_operator");
        String salary = req.getParameter("salary");
        List<Employer> employers;

        int page1 = page == null ? 1 : Integer.parseInt(page);
        int size1 = size == null ? 50 : Integer.parseInt(size);

        if (name!=null || salary !=null) {
            EmployerSearchFilter filter = new EmployerSearchFilter(
                    name, EPredicateOperator.AND, salary, ESalaryOperator.valueOf(salaryOperator),
                    page1,
                    size1,
                    ESortDirection.ASC
            );
            employers = employerService.pageFilter(filter);
        } else {
            PageableFilter filter = new PageableFilter(
                    page1,
                    size1,
                    ESortDirection.ASC);
            employers = employerService.page(filter);
        }

        String pageCount = calculationsService.pageCount(Double.parseDouble(size));
        req.setAttribute("employers", employers);
        req.setAttribute("page", page);
        req.setAttribute("size", size);
        req.setAttribute("pageCount", pageCount);
        req.getRequestDispatcher("/views/page/pageEmployer.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        employerService.generationEmployers();
    }
}

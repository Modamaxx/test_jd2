package controllers.servlets;

import model.Employer;
import model.dto.EmployerQuery;
import service.CalculationsService;
import service.EmployerService;

import javax.persistence.criteria.CriteriaBuilder;
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

        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        List<Employer> employers = null;

        if ( (req.getParameter("SearchName") != null) || (req.getParameter("Salary")!=null) ) {

            EmployerQuery employerQuery = new EmployerQuery();
            employerQuery.setName(req.getParameter("SearchName"));
            employerQuery.setSalary(req.getParameter("Salary"));
            employerQuery.setSort(req.getParameter("Sort"));
            employerQuery.setLimit(Integer.parseInt(req.getParameter("limit")));
            employerQuery.setNumberPage(Integer.parseInt(req.getParameter("page")));

            employers = employerService.sort(employerQuery);
        } else {
            employers = employerService.pageEmployer(page, limit);
        }

        String pageCount = calculationsService.pageCount(Double.parseDouble(limit));
        req.setAttribute("employers",employers);
        req.setAttribute("page",page);
        req.setAttribute("limit",limit);
        req.setAttribute("pageCount",pageCount);
        req.getRequestDispatcher("/views/page/pageEmployer.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        employerService.generationEmployers();
    }
}

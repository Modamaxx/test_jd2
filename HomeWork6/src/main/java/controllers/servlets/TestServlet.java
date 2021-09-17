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

@WebServlet(name = "TestServlet", urlPatterns = "/testServlet")
public class TestServlet extends HttpServlet {
    private final EmployerService employerService;

    public TestServlet() {

        this.employerService = EmployerService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // String pageNumber= req.getParameter("pageNumber");
        String pageNumber = "1";
        List<Employer> employers = employerService.employerCardTest(Integer.parseInt(pageNumber));
        req.setAttribute("employers", employers);

        req.getRequestDispatcher("/views/cardAllEmployerTest.jsp").forward(req, resp);
    }
}

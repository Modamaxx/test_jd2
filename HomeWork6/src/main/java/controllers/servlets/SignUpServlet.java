package controllers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Employer;
import service.EmployerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SignUpServlet", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {
    private final EmployerService employerService;
    private ObjectMapper mapper = new ObjectMapper();

    public SignUpServlet() {
        this.employerService = EmployerService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<String> strPositions= employerService.readFilePositions();
        ArrayList<String> strDepartments= employerService.readFileDepartments();
        req.setAttribute("strPositions",strPositions);
        req.setAttribute("strDepartments",strDepartments);

        req.getRequestDispatcher("/views/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      // Employer employer = mapper.readValue(req.getInputStream(), Employer.class);

        String name = req.getParameter("name");
        Double salary =Double.valueOf (req.getParameter("salary"));
        String departmentName= req.getParameter("departmentName");
        String positionName= req.getParameter("positionName");
        Employer employer = new Employer(name, salary,departmentName,positionName);

        int id = employerService.addEmployer(employer);
        req.setAttribute("id",String.valueOf(id));
        req.getRequestDispatcher("views/signUp.jsp").forward(req, resp);

    }
}

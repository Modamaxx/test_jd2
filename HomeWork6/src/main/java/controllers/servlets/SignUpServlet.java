package controllers.servlets;

import model.Employer;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignUpServlet", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {
    private final  UserService userService;

    public SignUpServlet() {
        this.userService=UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Double salary =Double.valueOf (req.getParameter("salary"));
        String departmentName= req.getParameter("departmentName");
        String positionName= req.getParameter("positionName");
        Employer employer = new Employer(name, salary,departmentName,positionName);
        long id = userService.addEmployer(employer);
        req.setAttribute("id",Float.toString(id));
        req.getRequestDispatcher("views/signUp.jsp").forward(req, resp);



    }
}

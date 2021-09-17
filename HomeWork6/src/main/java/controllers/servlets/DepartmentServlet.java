package controllers.servlets;

import model.Department;
import model.Employer;
import model.Position;
import service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DepartmentServlet", urlPatterns = "/department")
public class DepartmentServlet extends HttpServlet {
    private final DepartmentService departmentService;

    public DepartmentServlet() {
        this.departmentService = DepartmentService.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            String id =req.getParameter("id");
            List<Employer> employers=this.departmentService.cardDepartment(Integer.parseInt(id));
            req.setAttribute("employers",employers);
            req.getRequestDispatcher("/views/card/cardDepartment.jsp").forward(req, resp);
        }

        List<Department> str = departmentService.pageDepartment();
        req.setAttribute("str", str);
        req.getRequestDispatcher("/views/page/pageDepartment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        departmentService.generationDepartments();
    }
}

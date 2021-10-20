package controllers.servlets;


import model.Employer;
import service.EmployerService;
import service.api.IEmployerService;
import utils.ApplicationContextUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignInServlet", urlPatterns = "/signIn")
public class SignInServlet extends HttpServlet {
    private final IEmployerService employerService;

    public SignInServlet() {
        this.employerService = ApplicationContextUtil.getContext().getBean(IEmployerService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("id")!=null){
            String id = req.getParameter("id");
            Employer employer = employerService.get(Integer.parseInt(id));
            employer.setId(Integer.parseInt(id));
            req.setAttribute("employer", (employer));
            req.getRequestDispatcher("views/card/cardEmployer.jsp").forward(req, resp);
        }

        req.getRequestDispatcher("/views/signIn.jsp").forward(req, resp);
    }
}


package controllers.web.servlets;

import model.AppParam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "AboutServlet",urlPatterns = "/about")
public class AboutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String storageType = getServletContext().getInitParameter("storage");

        req.setAttribute("startAppTime",AppParam.getInstance().getStartTime().toString());
        req.setAttribute("storageType",storageType);


        req.getRequestDispatcher("/views/about.jsp").forward(req, resp);
    }
}

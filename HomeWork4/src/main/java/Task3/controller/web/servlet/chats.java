package Task3.controller.web.servlet;
import Task3.model.Person;
import Task3.service.WorkWithPerson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "chats", urlPatterns = "/chats")
public class chats extends HttpServlet {
    private final WorkWithPerson workWithPerson;
    public chats() {
        this.workWithPerson = WorkWithPerson.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/chats.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Person person = workWithPerson.givePerson(req);
        workWithPerson.addLetter(req.getParameter("recipient"), person, req);

        String path = req.getContextPath() + "/views/menu.jsp";
        resp.sendRedirect(path);
    }
}

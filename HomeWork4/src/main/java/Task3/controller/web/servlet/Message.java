package Task3.controller.web.servlet;
import Task3.model.Person;
import Task3.service.WorkWithPerson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Message", urlPatterns = "/Message")
public class Message extends HttpServlet {
    private final WorkWithPerson workWithPerson;

    public Message() {
        this.workWithPerson = WorkWithPerson.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person person = workWithPerson.givePerson(req);
        req.setAttribute("letters", person.getLetters());

        req.getRequestDispatcher("/views/message.jsp").forward(req, resp);
    }
}

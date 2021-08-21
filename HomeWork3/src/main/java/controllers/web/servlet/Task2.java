package controllers.web.servlet;
import model.Person;
import service.api.EStorageType;
import service.api.IHandleRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "Task2", urlPatterns = "/choice_storage")
public class Task2 extends HttpServlet {

    private static final String FIRST_NAME_PARAM = "firstName";
    private static final String LAST_NAME_PARAM = "lastName";
    private static final String AGE_PARAM = "age";

    private static final String STORAGE_TYPE_HEADER = "storage";

    public Task2() {

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        EStorageType storageType = EStorageType.valueOfIgnoreCase(req.getHeader(STORAGE_TYPE_HEADER));

        IHandleRequest handler = storageType.getHandler();

        Person person = handler.get(req);
        writer.write("FirstName: " + person.getFirstName());
        writer.write("LastName: " + person.getLastName());
        writer.write("Age: " + person.getAge());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter write = resp.getWriter();

        Person person = new Person();
        person.setFirstName(req.getParameter(FIRST_NAME_PARAM));
        person.setLastName(req.getParameter(LAST_NAME_PARAM));
        person.setAge(Integer.parseInt(req.getParameter(AGE_PARAM)));

        if (person.getFirstName() == null ||
                person.getLastName() == null ||
                person.getAge() == 0) {
            throw new IllegalArgumentException("Не передан один из обязательных параметров");
        }
        EStorageType storageType = EStorageType.valueOfIgnoreCase(req.getHeader(STORAGE_TYPE_HEADER));
        IHandleRequest handleRequest = storageType.getHandler();

        if (handleRequest == null) {
            throw new IllegalArgumentException("Вы не передали тип хранилища");
        }

        handleRequest.save(req, resp, person);

    }
}

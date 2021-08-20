
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;

@WebServlet(name = "NumberOneServlet", urlPatterns = "/task")
public class NumberOneServlet extends HttpServlet {
    static Map<String, Integer> number1Counting = new LinkedHashMap<>();
    static Map<String, Integer> number2Counting = new LinkedHashMap<>();
    static Map<String, String> text = new HashMap<>();

    static int index = 0;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write("<!DOCTYPE html>" +
                "<html lang='en'>\n" +
                "<head>\n" +
                "<title>Task</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form  method='POST'>\n" +
                "<input type=\"submit\" value=\"Submit\"/>\n" +
                "<p><b>Choose the best artist in your opinion</b></p>\n" +
                "<p><input name='Checked' type='radio' value='KILL'/>KILL</p>\n" +
                "<p><input name='Checked' type='radio' value='Mineral'/>Mineral</p>\n" +
                "<p><input name='Checked' type='radio' value='Bear'/>Bear</p>\n" +
                "<p><input name='Checked' type='radio' value='Marie'/>Marie</p>\n" +

                "<p><b>Choose your favorite genres (3-5 options)</b></p>" +
                "<p><input type='checkbox' name='number2' value='Emo'/>Emo</p>\n" +
                "<p><input type='checkbox' name='number2' value='Jazz'/>Jazz</p>\n" +
                "<p><input type='checkbox' name='number2' value='Rock'/>Rock</p>\n" +
                "<p><input type='checkbox' name='number2' value='Rap'/>Rap</p>\n" +
                "<p><input type='checkbox' name='number2' value='Pop'/>Pop</p>\n" +
                "<p><input type='checkbox' name='number2' value='Punk'/>Punk</p>\n" +
                "<p><input type='checkbox' name='number2' value='Indie'/>Indie</p>\n" +
                "<p><input type='checkbox' name='number2' value='Funk'/>Funk</p>\n" +
                "<p><input type='checkbox' name='number2' value='Midwest'/>Midwest</p>\n" +
                "<p><input type='checkbox' name='number2' value='Electronic'/>Electronic</p>\n" +

                "<p><b>A short text about you</b></p>\n" +
                "<textarea name='comment' cols='40' rows='3'></textarea>\n" +
                "</form>" +
                "</body>" +
                "</html>"
        );

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        Map<String, String[]> taskMap;
        if (index == 0) {
            initialization();
        }
        taskMap = req.getParameterMap();
        //Группы
        String[] checkNumber1 = taskMap.get("Checked");
        if (checkNumber1 == null) {
            return;
        }
        number1Counting.put(checkNumber1[0], number1Counting.get(checkNumber1[0]) + 1);
        sort(number1Counting);

        //Жанры
        String[] checkNumber2 = taskMap.get("number2");
        if (checkNumber2 == null || (checkNumber2.length < 3 || checkNumber2.length > 5)) {
            return;
        }
        for (String lol : checkNumber2) {
            number2Counting.put(lol, number2Counting.get(lol) + 1);
        }
        sort(number2Counting);


        //Текст
        String[] comment = taskMap.get("comment");
        if (comment == null) {
            return;
        }
        text.put(LocalDateTime.now().toString(), comment[0]);


        //вывод
        for (String s : number1Counting.keySet()) {
            writer.write("<p>" + s + " <b>" + number1Counting.get(s) + "</b> voice" + "</b></p>");
        }
        writer.write("<br/>");
        for (String s : number2Counting.keySet()) {
            writer.write("<p>" + s + " <b>" + number2Counting.get(s) + "</b> voice" + "</b></p>");
        }
        writer.write("<br/>");
        for (String time : text.keySet()) {
            writer.write("<p>" + text.get(time) + " " + time + "</p>");
        }


    }

    public void initialization() {
        number1Counting.put("KILL", 0);
        number1Counting.put("Mineral", 0);
        number1Counting.put("Bear", 0);
        number1Counting.put("Marie", 0);

        number2Counting.put("Emo", 0);
        number2Counting.put("Jazz", 0);
        number2Counting.put("Rock", 0);
        number2Counting.put("Rap", 0);
        number2Counting.put("Pop", 0);
        number2Counting.put("Punk", 0);
        number2Counting.put("Indie", 0);
        number2Counting.put("Funk", 0);
        number2Counting.put("Midwest", 0);
        number2Counting.put("Electronic", 0);
        index++;
    }

    public void sort(Map<String, Integer> map) {

    }

}

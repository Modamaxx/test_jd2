package controller.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/startApp")
public class StartAppController {

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public String get(){
        return "startApp";
    }
}

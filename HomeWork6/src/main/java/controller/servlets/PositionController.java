package controller.servlets;

import model.Employer;
import model.Position;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.PositionService;
import service.api.IEmployerService;
import service.api.IPositionService;
import utils.ApplicationContextUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/position")
public class PositionController {
    private final IPositionService positionService;

    public PositionController(@Qualifier("PositionService") IPositionService positionService) {
        this.positionService = positionService;
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = "text/html")
    public String page(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null) {
            List<Employer> employers = this.positionService.cardPosition(id);
            model.addAttribute("employers", employers);
            return "card/cardPosition";
        }
        List<Position> str = positionService.pagePosition();
        model.addAttribute("str", str);
        return "page/pagePosition";
    }

}

package controller.servlets;

import model.Employer;
import model.dto.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.api.ICalculationService;
import service.api.IEmployerService;
import utils.ApplicationContextUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    private final IEmployerService employerService;
    private final ICalculationService calculationsService;

    public EmployerController(@Qualifier("EmployerService") IEmployerService employerService,
                              @Qualifier("CalculationsService") ICalculationService calculationService) {
        this.employerService = employerService;
        this.calculationsService = calculationService;
    }

    @RequestMapping(value = "authorization", method = RequestMethod.GET, produces = "text/html")
    public String authorization(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null) {
            Employer employer = employerService.get(id);
            if (employer != null) {
                model.addAttribute("employer", (employer));
                return "card/cardEmployer";
            }
        }
        return "authorization";
    }

    @RequestMapping(value = "registration", method = RequestMethod.GET, produces = "text/html")
    public String registration(Model model) throws IOException {

        return "registration";
    }

    @RequestMapping(value = "page", method = RequestMethod.GET, produces = "text/html")
    public String page(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "page", required = false) String page,
                               @RequestParam(value = "size", required = false) String size,
                               @RequestParam(value = "salary_operator", required = false) String salaryOperator,
                               @RequestParam(value = "salary", required = false) String salary,Model model) throws IOException {
        List<Employer> employers;
        int page1 = page == null ? 1 : Integer.parseInt(page);
        int size1 = size == null ? 50 : Integer.parseInt(size);

        if (name!=null || salary !=null) {
            EmployerSearchFilter filter = new EmployerSearchFilter(
                    name, EPredicateOperator.AND, salary, ESalaryOperator.valueOf(salaryOperator),
                    page1,
                    size1,
                    ESortDirection.ASC
            );
            employers = employerService.pageFilter(filter);
        } else {
            PageableFilter filter = new PageableFilter(
                    page1,
                    size1,
                    ESortDirection.ASC);
            employers = employerService.page(filter);
        }
        String pageCount = calculationsService.pageCount(Double.parseDouble(size));
        model.addAttribute("employers", employers);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("pageCount", pageCount);
        return "page/pageEmployer";
    }




//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String name = req.getParameter("name");
//        String page = req.getParameter("page");
//        String size = req.getParameter("size");
//        String salaryOperator = req.getParameter("salary_operator");
//        String salary = req.getParameter("salary");
//        List<Employer> employers;
//
//        int page1 = page == null ? 1 : Integer.parseInt(page);
//        int size1 = size == null ? 50 : Integer.parseInt(size);
//
//        if (name!=null || salary !=null) {
//            EmployerSearchFilter filter = new EmployerSearchFilter(
//                    name, EPredicateOperator.AND, salary, ESalaryOperator.valueOf(salaryOperator),
//                    page1,
//                    size1,
//                    ESortDirection.ASC
//            );
//            employers = employerService.pageFilter(filter);
//        } else {
//            PageableFilter filter = new PageableFilter(
//                    page1,
//                    size1,
//                    ESortDirection.ASC);
//            employers = employerService.page(filter);
//        }
//
//        String pageCount = calculationsService.pageCount(Double.parseDouble(size));
//        req.setAttribute("employers", employers);
//        req.setAttribute("page", page);
//        req.setAttribute("size", size);
//        req.setAttribute("pageCount", pageCount);
//        req.getRequestDispatcher("/views/page/pageEmployer.jsp").forward(req, resp);
//
//    }
}
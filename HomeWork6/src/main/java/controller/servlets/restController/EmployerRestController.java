package controller.servlets.restController;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Employer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.api.IEmployerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/employer")
public class EmployerRestController {

    private final IEmployerService employerService;
    private final ObjectMapper mapper = new ObjectMapper();

    public EmployerRestController(@Qualifier("EmployerService") IEmployerService employerService) {
        this.employerService = employerService;
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public void getPage(HttpServletRequest req, HttpServletResponse resp, Model model) throws IOException {
        Employer employer = mapper.readValue(req.getInputStream(), Employer.class);
        int id = employerService.addEmployer(employer);
        model.addAttribute("id", String.valueOf(id));
        ArrayList<String> strPositions = employerService.readFilePositions();
        ArrayList<String> strDepartments = employerService.readFileDepartments();
        model.addAttribute("strPositions", strPositions);
        model.addAttribute("strDepartments", strDepartments);
        resp.sendRedirect("registration");
    }

    @RequestMapping(value = "/generation", method = RequestMethod.POST)
    public void generation() throws IOException {
        employerService.generationEmployers();
    }
}

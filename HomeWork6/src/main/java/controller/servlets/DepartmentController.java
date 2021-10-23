package controller.servlets;

import model.Department;
import model.Employer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.api.IDepartmentService;
import service.api.IPositionService;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private final IDepartmentService departmentService;

    public DepartmentController(@Qualifier("DepartmentService") IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = "text/html")
    public String page(@RequestParam(value = "id", required = false) Integer id, Model model) {

        if (id != null) {
            List<Employer> employers = this.departmentService.cardDepartment(id);
            model.addAttribute("employers", employers);
            return "card/cardDepartment";
        }

        List<Department> str = departmentService.pageDepartment();
        model.addAttribute("str", str);
        return "page/pageDepartment";
    }

}

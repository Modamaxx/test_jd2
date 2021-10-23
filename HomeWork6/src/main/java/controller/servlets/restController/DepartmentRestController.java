package controller.servlets.restController;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import service.api.IDepartmentService;

import java.io.IOException;

@RestController
@RequestMapping("/api/department")
public class DepartmentRestController {
    private final IDepartmentService departmentService;

    public DepartmentRestController(@Qualifier("DepartmentService") IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/generation",method = RequestMethod.POST)
    public void generation() throws IOException {
        departmentService.generationDepartments();
    }
}

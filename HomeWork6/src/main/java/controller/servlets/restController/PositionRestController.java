package controller.servlets.restController;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.api.IDepartmentService;
import service.api.IPositionService;

import java.io.IOException;

@RestController
@RequestMapping("/api/position")
public class PositionRestController {

    private final IPositionService positionService;

    public PositionRestController(@Qualifier("PositionService") IPositionService positionService) {
        this.positionService = positionService;
    }

    @RequestMapping(value = "/generation",method = RequestMethod.POST)
    public void generation() throws IOException {
      positionService.generationPosition();
    }
}

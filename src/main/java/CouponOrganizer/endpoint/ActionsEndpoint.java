package CouponOrganizer.endpoint;

import CouponOrganizer.model.ApiResponse;
import CouponOrganizer.service.ActionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionsEndpoint {

    @Autowired
    private ActionsServiceImpl actionsService;

    @GetMapping("/updateCronofy")
    public ApiResponse updateCronofy() {
        return actionsService.updateCronofy();
    }
}

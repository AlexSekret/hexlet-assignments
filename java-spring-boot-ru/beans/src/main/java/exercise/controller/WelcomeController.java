package exercise.controller;

import exercise.daytime.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN
@RestController
public class WelcomeController {
    @Autowired
    private Daytime dayTimeBean;

    @GetMapping(path = "/welcome")
    public String welcome() {
        return "It is " + dayTimeBean.getName() + " now! Welcome to Spring!";
    }
}
// END

package its.progetto.Forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class PageController {
    @GetMapping("/home")
    public String landingPage( ) {
        return "Landing_page";
    }
}

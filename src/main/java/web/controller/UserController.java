package web.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.User;

@Controller
@RequestMapping("/")
public class UserController {
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String printUser(Authentication auth, Model model) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("role", auth.getAuthorities());
        model.addAttribute("user", user);
        return "user";
    }

}

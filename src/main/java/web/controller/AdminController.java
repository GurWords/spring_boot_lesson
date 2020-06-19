package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Role;
import web.model.User;
import web.service.userservice.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String printListUser(Model model) {
        model.addAttribute("userlist", service.getAllUsers());
        return "admin";
    }

    @RequestMapping(value = "/admin/insert", method = RequestMethod.POST)
    public String insertUser(@RequestParam String name, String password, int age, String role) {
        String[] roleArrays = role.split(",");
        User user = new User.Builder()
                .withName(name)
                .withPassword(password)
                .withAge(age)
                .withRole(roleArrays)
                .build();
        service.insertUser(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    String deleteUsers(@RequestParam int idDelete, HttpServletRequest request) {
        service.deleteUser(idDelete);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.GET)
    String updateUser(Model model, @RequestParam int idUpdate) {
        User user = service.getUserById(idUpdate);
        model.addAttribute("user", user);
        model.addAttribute("roleList", user.getRoleSet());
        for (Role role : user.getRoleSet()) {
            System.out.println(role.getRole());
        }
        return "update";
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    String updateUserPost(@RequestParam int id, String name, String password, String role, int age) {
        String[] role_array = role.split(",");
        User user = new User.Builder()
                .withAge(age)
                .withName(name)
                .withPassword(password)
                .withId(id)
                .withRole(role_array)
                .build();
        service.updateUser(user);
        return "redirect:/admin";
    }
}

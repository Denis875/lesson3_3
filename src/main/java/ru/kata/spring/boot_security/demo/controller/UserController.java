package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
public class UserController {
    private final UserService us;
    private final RoleService rs;

    public UserController(UserService us, RoleService rs) {
        this.us = us;
        this.rs = rs;
    }

    @GetMapping(value = "/admin")
    public String startPageForAdmin(ModelMap model, @AuthenticationPrincipal UserDetails userDetail) {
        User user = us.findByUsername(userDetail.getUsername());
        model.addAttribute("curUser", user);
        model.addAttribute("users", us.getAll());
        model.addAttribute("roles", rs.getAllRoles());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @GetMapping("/user")
    public String showUserInfo(Model model, Principal principal) {
        User user = us.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/admin/saveUser")
    public String addUser(@ModelAttribute("newUser") User user) {
        us.add(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/deleteUser")
    public String deleteUser(@RequestParam Long id) {
        us.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/updateUser")
    public String updateUserInfo(@ModelAttribute("user") User user) {
        us.update(user);
        return "redirect:/admin";
    }
}

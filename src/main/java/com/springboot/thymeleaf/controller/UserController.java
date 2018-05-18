package com.springboot.thymeleaf.controller;


import com.springboot.thymeleaf.model.User;
import com.springboot.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView index(Model model, @RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "phone", required = false) String phone, Pageable pageable) {
        //Page<User> page = userService.index(name, phone, pageable);
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return new ModelAndView("user/list");
    }

    @GetMapping(value = "toAdd")
    public ModelAndView toAdd() {
        return new ModelAndView("user/add");
    }

    @PostMapping(value = "add")
    public String add(User user) {
        userService.save(user);
        return "redirect:/user";
    }

    @GetMapping(value = "/{id}/toEdit")
    public ModelAndView toEdit(@PathVariable(value = "id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return new ModelAndView("user/edit");
    }

    @PutMapping(value = "edit")
    public String edit(User user) {
        userService.edit(user);
        return "redirect:/user";
    }

    @DeleteMapping(value = "/{id}/delete")
    public String delete(@PathVariable(value = "id") Long id) {
        userService.delete(id);
        return "redirect:/user";
    }
}

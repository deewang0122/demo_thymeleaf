package com.springboot.thymeleaf.controller;

import com.springboot.thymeleaf.dto.LoginDto;
import com.springboot.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    public ModelAndView index(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return new ModelAndView("login");
    }

    @PostMapping(value = "/login")
    public ModelAndView login(LoginDto loginDto) {
        if (userService.isExist(loginDto.getPhone(), loginDto.getPassword())) {
            return new ModelAndView("redirect:/user");
        } else {
            return new ModelAndView("/login", "errors", "手机号码或者密码错误！");
        }
    }
}

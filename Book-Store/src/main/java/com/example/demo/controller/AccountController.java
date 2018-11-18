package com.example.demo.controller;

import com.example.demo.entites.Account;
import com.example.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "account/index";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(ModelMap modelMap) {
        modelMap.put("account", new Account());
        return "account/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(
            ModelMap modelMap,
            @ModelAttribute("account") Account account) {
        account.setPassword(account.getPassword());
        accountService.create(account);
        return "redirect:../account";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(
            HttpServletRequest request,
            HttpSession session,
            ModelMap modelMap) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = accountService.login(username, password);
        if(account == null){
            modelMap.put("error" , "Invalid Account");
            return "account/index";
        }else {
            session.setAttribute("username" , username);
            return "redirect:../cart";

        }
    }

}

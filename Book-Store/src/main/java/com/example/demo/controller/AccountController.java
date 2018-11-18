package com.example.demo.controller;

import com.example.demo.entites.Account;
import com.example.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
    public String register(ModelMap modelMap, @ModelAttribute("account") Account account) {
        account.setPassword(account.getPassword());
        accountService.create(account);
        return "redirect:../account";
    }
}

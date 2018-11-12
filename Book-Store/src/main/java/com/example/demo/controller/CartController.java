package com.example.demo.controller;

import com.example.demo.entites.Item;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "shopping-cart";
    }

    @RequestMapping(value="buy/{id}",  method = RequestMethod.GET)
    public String buy(@PathVariable("id") int id, ModelMap modelMap , HttpSession session) {
        if(session.getAttribute("cart") ==null) {
            List<Item> cart  = new ArrayList<Item>();
            cart.add(new Item(bookService.find(id) ,1));
            session.setAttribute("cart", cart);
        }else {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = isExist(id, cart);
            if(index == -1) {
                cart.add(new Item(bookService.find(id) ,1));
            }else {
                int quantity = cart.get(index).getQuantity() +1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        return "redirect:../../cart";
    }

    private int isExist(int id, List<Item> cart) {
        for (int i =0; i < cart.size(); i++) {
            if(cart.get(i).getBook().getBookId() == id) {
                return i;
            }
        }
        return -1;
    }
}

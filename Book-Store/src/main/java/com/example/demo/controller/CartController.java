package com.example.demo.controller;

import com.example.demo.entites.Item;
import com.example.demo.entites.Orders;
import com.example.demo.entites.Ordersdetail;
import com.example.demo.entites.OrdersdetailId;
import com.example.demo.services.AccountService;
import com.example.demo.services.BookService;
import com.example.demo.services.OrdersDetailService;
import com.example.demo.services.OrdersService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrdersDetailService ordersDetailService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap modelMap, HttpSession session) {
        modelMap.put("total", total(session));
        return "shopping-cart";
    }

    @RequestMapping(value = "checkout", method = RequestMethod.GET)
    public String checkout(ModelMap modelMap, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:../account";
        } else {
            // add new order
            Orders orders = new Orders();
            orders.setAccount(accountService.find(
                    session.getAttribute("username").toString()));
            orders.setCreatedDate(System.currentTimeMillis());
            orders.setName("New Order");
            orders.setStatus(1);
            int orderId = ordersService.create(orders).getId();

            //add order detail
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            for (Item item : cart
            ) {
                Ordersdetail ordersdetail = new Ordersdetail();
                ordersdetail.setId(new OrdersdetailId(item.getBook().getBookId(),orderId));
                ordersdetail.setPrice(item.getBook().getPrice());
                ordersdetail.setQuantity(item.getQuantity());
                ordersDetailService.create(ordersdetail);
            }
            //delete order
            // 0 là trạng thái đã thanh toán ( remove / payment ).
            orders.setStatus(0);
            session.removeAttribute("cart");

            return "orders/thanks";
        }
    }

    @RequestMapping(value = "buy/{id}", method = RequestMethod.GET)
    public String buy(@PathVariable("id") int id, HttpSession session) {
        if (session.getAttribute("cart") == null) {
            List<Item> cart = new ArrayList<Item>();
            cart.add(new Item(bookService.find(id), 1));
            session.setAttribute("cart", cart);
        } else {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = isExist(id, cart);
            if (index == -1) {
                cart.add(new Item(bookService.find(id), 1));
            } else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        return "redirect:../../cart";
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, HttpSession session) {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = isExist(id, cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
        return "redirect:../../cart";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(HttpServletRequest request,
                         HttpSession session) {
        String[] quantities = request.getParameterValues("quantity");
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        for (int i = 0; i < cart.size(); i++) {
            cart.get(i).setQuantity(Integer.parseInt(quantities[i]));
        }
        session.setAttribute("cart", cart);
        return "redirect:../cart";
    }

    private int isExist(int id, List<Item> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getBook().getBookId() == id) {
                return i;
            }
        }
        return -1;
    }

    private double total(HttpSession session) {
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        double s = 0;
        if (cart != null) {
            for (Item item : cart) {
                s += item.getQuantity()
                        * item.getBook().getPrice().doubleValue();
            }
        }
        return s;
    }
}

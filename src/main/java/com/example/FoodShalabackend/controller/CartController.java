package com.example.FoodShalabackend.controller;

import com.example.FoodShalabackend.modal.Cart;
import com.example.FoodShalabackend.modal.ViewOrder;
import com.example.FoodShalabackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping(path = "/showcart")
    public ArrayList<Cart> getCart(Principal principal) {
        return cartService.getEmail(principal);
    }

    @GetMapping(path = "/addItem/{productId}")
    public String addItemToCart(@PathVariable("productId") int id, Principal principal) {
        return cartService.addItemToCart(principal, id);
    }

    @GetMapping(path = "/deleteItem/{productId}")
    public String deleteItemFromCart(@PathVariable("productId") int id, Principal principal) {
        return cartService.deleteItemFromCart(id, principal);
    }

    @GetMapping(path = "/increment/{value}/{productId}")
    public String increment(@PathVariable("value") int value,
                            @PathVariable("productId") int id,
                            Principal principal) {
        return cartService.increment(value, id, principal);
    }

    @GetMapping(path = "/decrement/{value}/{productId}")
    public String decrement(@PathVariable("value") int value,
                            @PathVariable("productId") int id,
                            Principal principal) {
        return cartService.decrement(value, id, principal);
    }

    @GetMapping(value = "/checkout", produces = "application/json")
    public List<ViewOrder> checkoutFromCart(Principal principal)
    {
        return cartService.checkout(principal);
    }

}

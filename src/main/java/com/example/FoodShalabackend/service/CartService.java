package com.example.FoodShalabackend.service;

import com.example.FoodShalabackend.modal.Cart;
import com.example.FoodShalabackend.modal.Items;
import com.example.FoodShalabackend.modal.Users;
import com.example.FoodShalabackend.modal.ViewOrder;
import com.example.FoodShalabackend.repository.CartRepo;
import com.example.FoodShalabackend.repository.ItemRepo;
import com.example.FoodShalabackend.repository.UsersRepo;
import com.example.FoodShalabackend.repository.ViewOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepo cartRepo;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    ItemRepo itemRepo;

    @Autowired
    ViewOrderRepo viewOrderRepo;

    private ArrayList<Cart> getCartFromCurrentUser(Principal principal) {
        Optional<Users> users = usersRepo.findByEmail(principal.getName());
        ArrayList<Cart> cart = cartRepo.findAllByUsers(users);
        return cart;
    }

    public ArrayList<Cart> getEmail(Principal principal) {
        String email = principal.getName();
        Optional<Users> users = usersRepo.findByEmail(email);
        return cartRepo.findAllByUsers(users);
    }

    public String addItemToCart(Principal principal, Integer id) {
        Optional<Items> items = itemRepo.findById(id);
        Optional<Users> users = usersRepo.findByEmail(principal.getName());
        ArrayList<Cart> carts = getCartFromCurrentUser(principal);
        for (int i=0;i<carts.size();i++) {
            Cart cartObj = carts.get(i);
            if(cartObj.getItems()==items.get()) {
                cartObj.setQuantity(cartObj.getQuantity()+1);
                cartRepo.saveAndFlush(cartObj);
                return "\"Quantity increased\"";
            }
        }

        Cart cartObject = new Cart();
        cartObject.setQuantity(1);
        cartObject.setItems(items.get());
        cartObject.setUsers(users.get());

        cartRepo.save(cartObject);
        return "\"Item added to cart\"";
    }

    @Transactional
    public String deleteItemFromCart(Integer itemId, Principal principal) {
        Optional<Items> items = itemRepo.findById(itemId);
        Optional<Users> users = usersRepo.findByEmail(principal.getName());
        cartRepo.deleteByUsersAndItems(users, items);
        return "\"deletion completed\"";
    }

    public String increment(int value, Integer itemId, Principal principal) {
        ArrayList<Cart> carts = getCartFromCurrentUser(principal);
        Optional<Items> items = itemRepo.findById(itemId);
        for (int i=0;i<carts.size();i++) {
            Cart cartObj = carts.get(i);
            if(cartObj.getItems()==items.get()) {
                int x = cartObj.getQuantity()+value;
                if(x>=1)
                {
                    cartObj.setQuantity(x);
                    cartRepo.save(cartObj);
                    return "\"Successful\"";
                }
            }
        }
        return "\"Unsuccessful\"";
    }

    public String decrement(int value, Integer itemId, Principal principal) {
        ArrayList<Cart> carts = getCartFromCurrentUser(principal);
        Optional<Items> items = itemRepo.findById(itemId);
        for (int i=0;i<carts.size();i++) {
            Cart cartObj = carts.get(i);
            if(cartObj.getItems()==items.get()) {
                int x = cartObj.getQuantity()-value;
                if(x==1)
                {
                    cartObj.setQuantity(1);
                    cartRepo.save(cartObj);
                    return "\"Successful\"";
                } else if(x>1)
                {
                    cartObj.setQuantity(x);
                    cartRepo.save(cartObj);
                    return "\"Successful\"";
                }
            }
        }
        return "\"Unsuccessful\"";
    }

    public List<ViewOrder> checkout(Principal principal) {
        Optional<Users> users = usersRepo.findByEmail(principal.getName());
        ArrayList<Cart> cartList = cartRepo.findAllByUsers(users);
        for (Cart cart: cartList)
        {
            ViewOrder viewOrder = new ViewOrder();
            viewOrder.setUsers(cart.getUsers());
            viewOrder.setQuantity(cart.getQuantity());
            viewOrder.setItems(cart.getItems());
            viewOrder.setDate(new Date());
            cartRepo.delete(cart);
            viewOrderRepo.saveAndFlush(viewOrder);
        }
        return viewOrderRepo.findAllByUsers(users.get());
    }

}

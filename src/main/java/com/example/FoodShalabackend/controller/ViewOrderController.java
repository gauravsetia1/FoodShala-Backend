package com.example.FoodShalabackend.controller;

import com.example.FoodShalabackend.modal.ViewOrder;
import com.example.FoodShalabackend.repository.ViewOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "https://foodshalaa.herokuapp.com",allowedHeaders = "*")
public class ViewOrderController {

    @Autowired
    ViewOrderRepo viewOrderRepo;

    @GetMapping(value = "/vieworder", produces = "application/json")
    public List<ViewOrder> orderHistoryOfCart()
    {
        return viewOrderRepo.findAll();
    }
}

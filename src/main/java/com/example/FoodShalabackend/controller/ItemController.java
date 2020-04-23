package com.example.FoodShalabackend.controller;

import com.example.FoodShalabackend.modal.Items;
import com.example.FoodShalabackend.repository.ItemRepo;
import com.example.FoodShalabackend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ItemController {

    @Autowired
    ItemRepo itemRepo;

    @Autowired
    ItemService itemService;

    @PostMapping("/addItems")
    public String addItems(@Valid @RequestBody Items items,Principal principal)
    {
        return itemService.addDetails(items, principal);
    }

    @GetMapping("/getItems")
    public List<Items> getItems()
    {
        return itemRepo.findAll();
    }

    @GetMapping("getMenu")
    public List<Items> getMenu(Principal principal)
    {
        return itemService.getItemsFromUsers(principal);
    }
}

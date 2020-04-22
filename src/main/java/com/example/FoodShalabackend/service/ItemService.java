package com.example.FoodShalabackend.service;

import com.example.FoodShalabackend.modal.Items;
import com.example.FoodShalabackend.modal.Users;
import com.example.FoodShalabackend.repository.ItemRepo;
import com.example.FoodShalabackend.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepo itemRepo;

    @Autowired
    UsersRepo usersRepo;

    public String addDetails(Items items, Principal principal) {
        String email = principal.getName();
        Optional<Users> users = usersRepo.findByEmail(email);
        items.setUsers(users.get());
        itemRepo.save(items);
        return "\"" + "item added/ updated " + "\"";
    }

    public List<Items> getItemsFromUsers(Principal principal) {
        String email = principal.getName();
        Optional<Users> users = usersRepo.findByEmail(email);
        return itemRepo.findAllByUsers(users.get());
    }
}

package com.example.FoodShalabackend.repository;

import com.example.FoodShalabackend.modal.Cart;
import com.example.FoodShalabackend.modal.Items;
import com.example.FoodShalabackend.modal.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {

    ArrayList<Cart> findAllByUsers(Optional<Users> users);

    void deleteByUsersAndItems(Optional<Users> users, Optional<Items> items);
}

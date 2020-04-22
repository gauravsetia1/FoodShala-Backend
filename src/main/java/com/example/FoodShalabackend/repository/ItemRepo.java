package com.example.FoodShalabackend.repository;

import com.example.FoodShalabackend.modal.Items;
import com.example.FoodShalabackend.modal.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Items, Integer> {

    List<Items> findAllByUsers(Users users);
}

package com.example.FoodShalabackend.repository;

import com.example.FoodShalabackend.modal.Users;
import com.example.FoodShalabackend.modal.ViewOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewOrderRepo extends JpaRepository<ViewOrder, Integer> {

    List<ViewOrder> findAllByUsers(Users users);
}

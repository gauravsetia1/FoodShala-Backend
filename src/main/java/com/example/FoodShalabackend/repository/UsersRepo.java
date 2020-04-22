package com.example.FoodShalabackend.repository;

import com.example.FoodShalabackend.modal.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {


    public Optional<Users> findByEmail(String name);
}

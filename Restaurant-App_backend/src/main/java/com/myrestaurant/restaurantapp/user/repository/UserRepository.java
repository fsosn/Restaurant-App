package com.myrestaurant.restaurantapp.user.repository;

import com.myrestaurant.restaurantapp.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}


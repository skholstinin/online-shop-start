package com.example.repository;


import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);

    Collection<User> findAllByRole(String role);

}

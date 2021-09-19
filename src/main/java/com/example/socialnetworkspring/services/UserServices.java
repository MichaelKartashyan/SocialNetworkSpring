package com.example.socialnetworkspring.services;

import com.example.socialnetworkspring.models.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserServices extends UserDetailsService {
    Users updateUser(Users user);
    List<Users> getAllUsers();
    Users getUser(String email);
    Users addUser(Users user);
    Users getUserById(Long id);
    Boolean isFreeEmail(String email);
    List<Users> getSearchUsers(String name, String surname);
    List<Users> getSearchUsersByName(String name);
}

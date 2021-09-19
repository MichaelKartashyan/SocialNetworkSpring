package com.example.socialnetworkspring.services.impl;

import com.example.socialnetworkspring.Repositories.UsersRepository;
import com.example.socialnetworkspring.models.Users;
import com.example.socialnetworkspring.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(s);
        if(user!=null) return user;
        else throw new UsernameNotFoundException("User Not Found");
    }

    @Override
    public Users updateUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Users addUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public Boolean isFreeEmail(String email) {
        Users u = userRepository.findByEmail(email);
        if(u!=null){
            return false;
        }
        return true;
    }

    @Override
    public List<Users> getSearchUsers(String name, String surname) {
        return userRepository.findAllByNameAndSurname(name,surname);
    }

    @Override
    public List<Users> getSearchUsersByName(String name) {
        return userRepository.findAllByNameOrSurname(name);
    }
}

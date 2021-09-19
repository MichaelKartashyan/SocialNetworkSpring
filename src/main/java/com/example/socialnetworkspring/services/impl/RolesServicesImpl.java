package com.example.socialnetworkspring.services.impl;

import com.example.socialnetworkspring.Repositories.RolesRepository;
import com.example.socialnetworkspring.models.Roles;
import com.example.socialnetworkspring.services.RolesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class RolesServicesImpl implements RolesServices {

    @Autowired
    RolesRepository rolesRepository;

    @Override
    public Set<Roles> getRoleByName(String role) {
        return rolesRepository.findByRole(role);
    }
}

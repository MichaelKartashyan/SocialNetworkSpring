package com.example.socialnetworkspring.services;

import com.example.socialnetworkspring.models.Roles;

import java.util.Set;

public interface RolesServices {
    Set<Roles> getRoleByName(String role);
}

package com.example.socialnetworkspring.Repositories;

import com.example.socialnetworkspring.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
@Transactional
public interface RolesRepository extends JpaRepository<Roles,Long> {
    Set<Roles> findByRole(String role);
}
package com.example.socialnetworkspring.Repositories;

import com.example.socialnetworkspring.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByEmail(String email);
    List<Users> findAllByNameAndSurname(String name, String surname);
    @Query(value = "SELECT *  from users " +
            "WHERE name = ?1 or surname = ?1 or email = ?1",
            nativeQuery = true)
    List<Users> findAllByNameOrSurname(String name);
}

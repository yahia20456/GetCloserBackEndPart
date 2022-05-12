package com.esprit.pidevbackend.Repository;

import com.esprit.pidevbackend.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username ) ;
    User findByEmail(String email);
    User findByToken(String token);

    @Query("select c.username from User  c where c.username=:username")
    String findUserByUsername(@Param("username") String username);





}

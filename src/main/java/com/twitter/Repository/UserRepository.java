package com.twitter.Repository;

import com.twitter.Model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.username = :username, u.password = :password WHERE u.id = :id")
    void update(String username, String password, Long id);
}
package com.example.znanya.repos;

import com.example.znanya.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
    String findByFilename(String file);
}

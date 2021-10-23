package com.game.worm.service.repository;

import com.game.worm.service.dao.UserDAO;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "UserRepository")
public interface UserRepository extends JpaRepository<UserDAO, Long> {
    UserDAO getByUserId(String userId);
}

package com.game.worm.service.repository;

import com.game.worm.service.dao.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDAO, Long> {
    Optional<UserDAO> findById(Long aLong);
}

package com.game.worm.service.repository;

import com.game.worm.service.user.dao.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "UserRepository")
public interface UserRepository extends JpaRepository<UserDAO, Long> {
    UserDAO getByUserId(String userId);
    void deleteByUserId(String userId);
}

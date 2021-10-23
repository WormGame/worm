package com.game.worm.service.dao;

import lombok.Getter;

import javax.persistence.*;

@Entity(name = "USER")
@Getter
public class UserDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private long userNo;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_passwd")
    private String userPasswd;

    public UserDAO(String userId, String userPasswd) {
        this.userId = userId;
        this.userPasswd = userPasswd;
    }
}

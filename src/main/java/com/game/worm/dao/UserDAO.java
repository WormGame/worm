package com.game.worm.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "USER_INFO")
@Getter
@NoArgsConstructor
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

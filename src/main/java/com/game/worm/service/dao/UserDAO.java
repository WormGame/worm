package com.game.worm.service.dao;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class UserDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long userNo;
    @Column
    public String userId;
    @Column
    public String userPasswd;
}

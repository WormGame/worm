package com.game.worm.service;

import com.game.worm.service.dao.UserDAO;
import com.game.worm.service.repository.UserRepository;
import com.game.worm.service.security.UserDetailsImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    private boolean checkParam(UserDAO userDAO){
        if(userDAO == null){
            return false;
        }
        return true;
    }

    public void signup(@NonNull String userId, @NonNull String userPasswd){
        userRepo.save(new UserDAO(userId, userPasswd));
    }
    private UserDetails login(@NonNull String userId) throws UsernameNotFoundException{
        UserDAO userDAO = userRepo.getByUserId(userId);
        if(!checkParam(userDAO)){
            throw new UsernameNotFoundException(userId);
        }

        return new UserDetailsImpl(userDAO.getUserNo(), userDAO.getUserId(), userDAO.getUserPasswd());
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return login(username);
    }
}

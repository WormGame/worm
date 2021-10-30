package com.game.worm.service;

import com.game.worm.service.dao.UserDAO;
import com.game.worm.service.repository.UserRepository;
import com.game.worm.service.security.UserDetailsImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    private boolean checkParam(final UserDAO userDAO){
        if(userDAO == null){
            return false;
        }
        return true;
    }

    public void signup(@NonNull final String userId, @NonNull final String userPasswd){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePasswd = bCryptPasswordEncoder.encode(userPasswd);
        userRepo.save(new UserDAO(userId, encodePasswd));
    }

    private UserDetails login(@NonNull final String userId) throws UsernameNotFoundException{
        UserDAO userDAO = userRepo.getByUserId(userId);
        if(!checkParam(userDAO)){
            throw new UsernameNotFoundException(userId);
        }

        return new UserDetailsImpl(userDAO.getUserNo(), userDAO.getUserId(), userDAO.getUserPasswd());
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull final String username) throws UsernameNotFoundException {
        return login(username);
    }
}

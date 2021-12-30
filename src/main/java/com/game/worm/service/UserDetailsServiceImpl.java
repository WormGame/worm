package com.game.worm.service;

import com.game.worm.service.dao.UserDAO;
import com.game.worm.service.repository.UserRepository;
import com.game.worm.service.security.UserDetailsImpl;
import com.game.worm.utils.BCryptPasswordEncoderEx;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepo;
    private final BCryptPasswordEncoderEx bCryptPasswordEncoderEx;

    public void signup(@NonNull final String userId, @NonNull final String userPasswd){
        String encodePasswd = bCryptPasswordEncoderEx.encode(userPasswd);
        userRepo.save(new UserDAO(userId, encodePasswd));
    }

    private UserDetails login(@NonNull final String userId) throws UsernameNotFoundException{
        UserDAO userDAO = userRepo.getByUserId(userId);
        if(userDAO == null){
            throw new UsernameNotFoundException(userId);
        }
        final String userIdInDB = userDAO.getUserId();
        final String userPasswdInDB = userDAO.getUserPasswd();
        return new UserDetailsImpl(userIdInDB, userPasswdInDB);
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull final String username) throws UsernameNotFoundException {
        return login(username);
    }
}

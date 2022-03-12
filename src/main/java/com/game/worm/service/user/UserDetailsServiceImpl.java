package com.game.worm.service.user;

import com.game.worm.dao.UserDAO;
import com.game.worm.repository.UserRepository;
import com.game.worm.service.security.UserDetailsImpl;
import com.game.worm.vo.UserSignupVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepo;

    public void signup(final UserSignupVO userSignupVO){
        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        final String encodePasswd = bCryptPasswordEncoder.encode(userSignupVO.getUserPasswd());
        final String userId = userSignupVO.getUserId();
        UserDAO userDAO = new UserDAO(userId, encodePasswd);
        userRepo.save(userDAO);
    }

    private UserDetails login(final String userId) throws UsernameNotFoundException{
        UserDAO userDAO = userRepo.getByUserId(userId);
        if(userDAO == null){
            throw new AccessDeniedException("dd");
        }
        final String userIdInDB = userDAO.getUserId();
        final String userPasswdInDB = userDAO.getUserPasswd();
        return new UserDetailsImpl(userIdInDB, userPasswdInDB);
    }

    @Override
    public UserDetails loadUserByUsername(final String userId) throws UsernameNotFoundException {
        return login(userId);
    }
}

package com.game.worm.service.user;

import com.game.worm.service.repository.UserRepository;
import com.game.worm.service.security.UserDetailsImpl;
import com.game.worm.service.user.dao.UserDAO;
import com.game.worm.service.user.vo.UserSignupVO;
import com.game.worm.utils.BCryptPasswordEncoderEx;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepo;
    private final BCryptPasswordEncoderEx bCryptPasswordEncoderEx;

    public void signup(final UserSignupVO userSignupVO){
        final String encodePasswd = bCryptPasswordEncoderEx.encode(userSignupVO.getUserId());
        final String userId = userSignupVO.getUserId();
        UserDAO userDAO = new UserDAO(userId, encodePasswd);
        userRepo.save(userDAO);
    }

    private UserDetails login(final String userId) throws UsernameNotFoundException{
        UserDAO userDAO = userRepo.getByUserId(userId);
        if(userDAO == null){
            throw new UsernameNotFoundException(userId);
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

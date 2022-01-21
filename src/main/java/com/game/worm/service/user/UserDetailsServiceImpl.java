package com.game.worm.service.user;

import com.game.worm.service.user.dao.UserDAO;
import com.game.worm.service.repository.UserRepository;
import com.game.worm.service.security.UserDetailsImpl;
import com.game.worm.service.user.vo.UserSignupVO;
import com.game.worm.utils.BCryptPasswordEncoderEx;
import com.game.worm.utils.Messages;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Service
@Validated
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepo;
    private final BCryptPasswordEncoderEx bCryptPasswordEncoderEx;

    public void signup(@NotNull(message = Messages.BAD_PARAM) @Valid final UserSignupVO userSignupVO){
        String encodePasswd = bCryptPasswordEncoderEx.encode(userSignupVO.getUserId());
        userRepo.save(new UserDAO(userSignupVO.getUserId(), encodePasswd));
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

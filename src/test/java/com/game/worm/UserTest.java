package com.game.worm;

import com.game.worm.dao.UserDAO;
import com.game.worm.etc.define.ParameterName;
import com.game.worm.repository.UserRepository;
import com.game.worm.etc.define.Urls;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserTest {
	final String password = "1234";
	final String userId = "mose111";

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private UserRepository userRepository;

	@Test
	void login() throws Exception {
		MultiValueMap<String, String> userInfo = new LinkedMultiValueMap<>();

		userInfo.add(ParameterName.USER_ID, userId);
		userInfo.add(ParameterName.USER_PASSWD, password);

		mockMvc.perform(post(Urls.login)
						.params(userInfo))
				.andExpect(header().string("Location", "/"))
				.andExpect(status().is3xxRedirection())
				.andDo(print());
	}


	@Test
	void signup() throws Exception {
		MultiValueMap<String, String> userInfo = new LinkedMultiValueMap<>();

		userInfo.add(ParameterName.USER_ID, userId);
		userInfo.add(ParameterName.USER_PASSWD, password);

		mockMvc.perform(post(Urls.signup)
						.params(userInfo))
				.andExpect(status().isOk())
				.andDo(print());
	}

	@Transactional
	@BeforeEach
	void init() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		final String encodePasswd = bCryptPasswordEncoder.encode(password);
		final UserDAO user = new UserDAO(userId, encodePasswd);

		try {
			userRepository.deleteByUserId(userId);
			userRepository.save(user);

		}catch (Exception e){
			e.printStackTrace();
		}
	}

}

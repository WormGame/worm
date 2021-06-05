package com.game.worm;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EncryptablePropertySource(name = "EncryptedProperties", value = "classpath:encrypted.properties")
public class WormApplication {

	public static void main(String[] args) {
		SpringApplication.run(WormApplication.class, args);
	}

}

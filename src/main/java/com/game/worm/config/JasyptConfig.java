package com.game.worm.config;


import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {
    @Bean
    public StringEncryptor jasyptStringEncryptor() {
        String key = System.getProperty("jasypt.encryptor.password");
        String algorithm = System.getProperty("jasypt.encryptor.algorithm");
        if (key == null | algorithm == null) {
            throw new RuntimeException();
        }
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(key);
        config.setAlgorithm(algorithm);
        config.setKeyObtentionIterations("1000");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setPoolSize("1");
        encryptor.setConfig(config);
        return encryptor;
    }
}
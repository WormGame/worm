package com.game.worm.config;


import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class JasyptConfig {
    @Bean
    public StringEncryptor stringEncryptor() {
        String key = System.getProperty("jasypt.encryptor.password");
        String algorithm = System.getProperty("jasypt.encryptor.algorithm");
        if (key == null | algorithm == null) {
            throw new RuntimeException();
        }
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(key); //암호화에 사용할 키 -> 중요
        config.setAlgorithm(algorithm); //사용할 알고리즘
        config.setKeyObtentionIterations("1000");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setPoolSize("1");
        encryptor.setConfig(config);

        System.out.println("URL:" + encryptor.encrypt("jdbc:mysql://localhost:3308/worm?serverTimezone=Asia/Seoul&characterEncoding=UTF-8")
        +"passswd:"+encryptor.encrypt("1234")
        +"ID:" +encryptor.encrypt("root"));
        return encryptor;
    }
}
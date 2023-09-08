package com.test.chat.config;

import org.assertj.core.api.Assertions;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class JasyptConfigTest {
    @InjectMocks
    private JasyptConfig jasyptConfig;

    @Test
    @DisplayName("암복호화 테스트 및 암호화 얻기")
    public void encryptTest() {
        ReflectionTestUtils.setField(jasyptConfig, "key", "test");
        PooledPBEStringEncryptor encryptor = (PooledPBEStringEncryptor) jasyptConfig.stringEncryptor();

        String plainText = "test";
        String encryptText = "";
        String decryptText = "";

        encryptText = encryptor.encrypt(plainText);
        System.out.println("encryptText = " + encryptText);

        decryptText = encryptor.decrypt(encryptText);
        System.out.println("decryptText = " + decryptText);

        Assertions.assertThat(plainText).isNotEqualTo(encryptText);
        Assertions.assertThat(plainText).isEqualTo(decryptText);
    }
}
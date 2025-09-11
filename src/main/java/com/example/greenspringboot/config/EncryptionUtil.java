package com.example.greenspringboot.config;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


/*
AES 알고리즘 기반 암호화 기능 클래스 (이메일 쿠키)
암호화하고 다시 복호화해야하기 때문에 안전한 AES 대칭키 알고리즘을 사용
*/

@Component
public class EncryptionUtil {

    private static final String ALGORITHM = "AES";

    private final String key;

    // 생성자 주입으로 프로퍼티 값 받기
    public EncryptionUtil(@Value("${encryption.key}") String key) {
        this.key = key;
    }

    public String encrypt(String data) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public String decrypt(String encryptedData) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }
}
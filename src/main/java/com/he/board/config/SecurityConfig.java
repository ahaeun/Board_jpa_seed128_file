package com.he.board.config;

import java.security.SecureRandom;

import javax.crypto.spec.IvParameterSpec;

public class SecurityConfig {
    public static final String PRIVATE_KEY = "AES_PRIVATE_KEY_THIS_TEST";

    // public static String encrypt(String plainText) throws Exception {

    //     Seed12

	// 	Cipher cipher = Cipher.getInstance(specName);
	// 	cipher.init(Cipher.ENCRYPT_MODE, PRIVATE_KEY, getIv());
	// 	byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
	// 	return new String(Base64.getEncoder().encode(encrypted));
	// }

    public static IvParameterSpec getIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

}

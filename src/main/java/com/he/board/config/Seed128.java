package com.he.board.config;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import java.util.Base64;
import java.util.function.Predicate;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class Seed128 {

    // private static String pbUserKey = "2b,7e,15,16,28,ae,d2,a6,ab,f7,15,88,09,cf,4f,3c";

    private static final Charset ENCODING_TYPE = StandardCharsets.UTF_8;

    private byte[] key;

    // private static byte userKey[] = pbUserKey.getBytes();
    private static byte IV[] = "123456789abcdefe".getBytes();
    // private static byte Cipher[] = null;

    public void seedKey(String k) {
        validation(k);
        this.key = k.getBytes(ENCODING_TYPE);
    }

    public String encrypt(String str){
        byte[] strBytes = str.getBytes(ENCODING_TYPE);
        byte[] enc = KisaSeedCbc.SEED_CBC_Encrypt(this.key, IV, strBytes ,0, strBytes.length);
        return new String(Base64.getEncoder().encode(enc), ENCODING_TYPE);
    }

    public String decrypt(String str){
        byte[] decoded = Base64.getDecoder().decode(str.getBytes(ENCODING_TYPE));
        byte[] dec = KisaSeedCbc.SEED_CBC_Decrypt(this.key, IV, decoded ,0, decoded.length);
        return new String(dec, ENCODING_TYPE);
    }

    private void validation(String key){
        Optional.ofNullable(key)
            .filter(Predicate.not(String::isBlank))
            .filter(Predicate.not(s -> s.length() != 16))
            .orElseThrow(IllegalArgumentException::new);
    }

}

package com.aathavan.dbinstall.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class Secutity {

    private static final Logger logger = LogManager.getLogger(Secutity.class);
    private static final String ALGO = "AES";
    private static final String secretKey = "Aat@di@!t06e$@43";

    public static String encrypter(String name) {
        try {
            if (name == null)
                throw new Exception("Null Case String");
            String secret = encodeKey();
            Key key = generateKey(secret);
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(name.trim().getBytes());
            return Base64.getEncoder().encodeToString(encVal);
        } catch (Exception e) {
            logger.info("Error while encrypting: " + e);
        }
        return null;
    }


    public static String decrypter(String name) {
        try {

            String secret = encodeKey();

            Key key = generateKey(secret);
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(name.trim())));
        } catch (Exception e) {
            logger.info("Error while encrypting: " + e);
        }
        return null;
    }

    private static String encodeKey() {
        byte[] encoded = Base64.getEncoder().encode(Secutity.secretKey.getBytes());
        return new String(encoded);
    }

    private static Key generateKey(String secret) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(secret.getBytes());
        return new SecretKeySpec(decoded, ALGO);
    }
}

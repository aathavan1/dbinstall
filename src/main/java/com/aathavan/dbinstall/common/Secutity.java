package com.aathavan.dbinstall.common;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class Secutity {

    private static String secretKey = "DES";

    public static String encrypter(String name) throws Exception {

        SecretKey key = KeyGenerator.getInstance(secretKey).generateKey();

        // Creating object of Cipher
        Cipher desCipher = Cipher.getInstance(secretKey);

        // Creating byte array to store string
        byte[] text = name.getBytes(StandardCharsets.UTF_8);

        // Encrypting text
        desCipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] textEncrypted = desCipher.doFinal(text);

        // Converting encrypted byte array to string
        return new String(textEncrypted);

    }


    public static String decrypter(String name) throws Exception {
        // Decrypting text

        SecretKey key = KeyGenerator.getInstance(secretKey).generateKey();

        Cipher desCipher = Cipher.getInstance(secretKey);
        desCipher.init(Cipher.DECRYPT_MODE, key);


        byte[] textDecrypted = desCipher.doFinal(name.getBytes(StandardCharsets.UTF_8));

        // Converting decrypted byte array to string
        return new String(textDecrypted);
    }
}

package dev.simmons.utilities.hashing;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class HashUtil {
    public static String hashSaltedString(String message, byte[] salt) {
        try {
            KeySpec spec = new PBEKeySpec(message.toCharArray(), salt, 10, 32);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = keyFactory.generateSecret(spec).getEncoded();
            message = Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // Logging here, something went funky with the hashing algorithm
            e.printStackTrace();
        }

        return message;
    }
}

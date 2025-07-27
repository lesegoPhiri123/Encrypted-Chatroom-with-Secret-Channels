package za.co.project;
//Encryption logic
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptoUtils {
    private static final String key = "1234567890123456"; // 16 chars = 128-bit AES

    public static String encrypt(String str) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(str.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String str) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decoded = Base64.getDecoder().decode(str);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }
}


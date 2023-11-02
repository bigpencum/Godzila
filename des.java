import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;
class DesExample {
    public static void main(String[] args) {
        try {
            String originalText = "Hello, DES!";
            String secretKey = "MySecretKey"; // Your 8-character secret key

            // Create a DES key from the secret key
            KeySpec keySpec = new DESKeySpec(secretKey.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);

            // Initialize the DES cipher for encryption
            Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            desCipher.init(Cipher.ENCRYPT_MODE, key);

            // Encrypt the original text
            byte[] encryptedBytes = desCipher.doFinal(originalText.getBytes());
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
            System.out.println("Encrypted: " + encryptedText);

            // Initialize the DES cipher for decryption
            desCipher.init(Cipher.DECRYPT_MODE, key);

            // Decrypt the encrypted text
            byte[] decryptedBytes = desCipher.doFinal(Base64.getDecoder().decode(encryptedText));
            String decryptedText = new String(decryptedBytes);
            System.out.println("Decrypted: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


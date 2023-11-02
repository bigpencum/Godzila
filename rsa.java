import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

 class RSAExample {
    public static void main(String[] args) {
        try {
            // Generate an RSA key pair
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // Key size (2048 bits is recommended for security)
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Encode the public key and private key to Base64 for storage or transmission
            String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKey.getEncoded());

            System.out.println("Public Key (Base64):");
            System.out.println(publicKeyBase64);

            System.out.println("Private Key (Base64):");
            System.out.println(privateKeyBase64);

            // Encrypt a message using the public key
            String plaintext = "Hello, RSA!";
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
            String encryptedMessage = Base64.getEncoder().encodeToString(encryptedBytes);

            System.out.println("Encrypted Message (Base64):");
            System.out.println(encryptedMessage);

            // Decrypt the message using the private key
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
            String decryptedMessage = new String(decryptedBytes);

            System.out.println("Decrypted Message:");
            System.out.println(decryptedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


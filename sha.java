import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 class SHA1Example {
    public static void main(String[] args) {
        String input = "Hello, SHA-1!";

        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            byte[] hashBytes = sha1.digest(input.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xFF & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            String sha1Hash = hexString.toString();
            System.out.println("SHA-1 Hash: " + sha1Hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

 class MD5Example {
    public static void main(String[] args) {
        String input = "Hello, MD5!";

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md5.digest(input.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xFF & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            String md5Hash = hexString.toString();
            System.out.println("MD5 Hash: " + md5Hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}


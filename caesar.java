class CaesarCipher {
    public static String encrypt(String plainText, int shift) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) (base + (c - base + shift) % 26);
            }
            encryptedText.append(c);
        }
        return encryptedText.toString();
    }

    public static String decrypt(String encryptedText, int shift) {
        return encrypt(encryptedText, 26 - shift);
    }

    public static void main(String[] args) {
        String plainText = "Hello, World!";
        int shift = 3;

        String encryptedText = encrypt(plainText, shift);
        System.out.println("Encrypted: " + encryptedText);

        String decryptedText = decrypt(encryptedText, shift);
        System.out.println("Decrypted: " + decryptedText);
    }
}


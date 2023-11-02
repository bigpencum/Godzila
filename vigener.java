 class VigenereCipher {
    public static void main(String[] args) {
        String plaintext = "HELLO";
        String keyword = "KEY";

        String encryptedText = encrypt(plaintext, keyword);
        System.out.println("Encrypted: " + encryptedText);

        String decryptedText = decrypt(encryptedText, keyword);
        System.out.println("Decrypted: " + decryptedText);
    }

    public static String encrypt(String plaintext, String keyword) {
        plaintext = plaintext.toUpperCase();
        keyword = keyword.toUpperCase();

        StringBuilder encryptedText = new StringBuilder();
        int keywordLength = keyword.length();

        for (int i = 0, j = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            if (Character.isLetter(c)) {
                int shift = keyword.charAt(j % keywordLength) - 'A';
                char encryptedChar = (char) ('A' + (c - 'A' + shift) % 26);
                encryptedText.append(encryptedChar);
                j++;
            } else {
                encryptedText.append(c);
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String encryptedText, String keyword) {
        encryptedText = encryptedText.toUpperCase();
        keyword = keyword.toUpperCase();

        StringBuilder decryptedText = new StringBuilder();
        int keywordLength = keyword.length();

        for (int i = 0, j = 0; i < encryptedText.length(); i++) {
            char c = encryptedText.charAt(i);
            if (Character.isLetter(c)) {
                int shift = keyword.charAt(j % keywordLength) - 'A';
                char decryptedChar = (char) ('A' + (c - 'A' - shift + 26) % 26);
                decryptedText.append(decryptedChar);
                j++;
            } else {
                decryptedText.append(c);
            }
        }

        return decryptedText.toString();
    }
}


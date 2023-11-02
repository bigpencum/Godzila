 class PlayfairCipher {
   private String key;
    private char[][] keyTable;

    public PlayfairCipher(String key) {
        this.key = key;
        initializeKeyTable();
    }

    private void initializeKeyTable() {
        key = key.replaceAll("J", "I").toUpperCase();
        key = key.replaceAll("[^A-Z]", ""); // Remove non-alphabet characters
        key = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";

        keyTable = new char[5][5];
        boolean[] used = new boolean[26];

        int index = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                while (used[key.charAt(index) - 'A']) {
                    index++;
                }
                keyTable[i][j] = key.charAt(index);
                used[key.charAt(index) - 'A'] = true;
            }
        }
    }

    public String encrypt(String plaintext) {
        plaintext = plaintext.toUpperCase().replaceAll("J", "I").replaceAll("[^A-Z]", "");

        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += 2) {
            char c1 = plaintext.charAt(i);
            char c2 = (i + 1 < plaintext.length()) ? plaintext.charAt(i + 1) : 'X';

            int r1 = -1, r2 = -1, c1 = -1, c2 = -1;

            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    if (keyTable[row][col] == c1) {
                        r1 = row;
                        c1 = col;
                    }
                    if (keyTable[row][col] == c2) {
                        r2 = row;
                        c2 = col;
                    }
                }
            }

            if (r1 == r2) {
                c1 = (c1 + 1) % 5;
                c2 = (c2 + 1) % 5;
            } else if (c1 == c2) {
                r1 = (r1 + 1) % 5;
                r2 = (r2 + 1) % 5;
            } else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }

            ciphertext.append(keyTable[r1][c1]);
            ciphertext.append(keyTable[r2][c2]);
        }

        return ciphertext.toString();
    }

    public String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            char c1 = ciphertext.charAt(i);
            char c2 = ciphertext.charAt(i + 1);

            int r1 = -1, r2 = -1, c1 = -1, c2 = -1;

            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    if (keyTable[row][col] == c1) {
                        r1 = row;
                        c1 = col;
                    }
                    if (keyTable[row][col] == c2) {
                        r2 = row;
                        c2 = col;
                    }
                }
            }

            if (r1 == r2) {
                c1 = (c1 - 1 + 5) % 5;
                c2 = (c2 - 1 + 5) % 5;
            } else if (c1 == c2) {
                r1 = (r1 - 1 + 5) % 5;
                r2 = (r2 - 1 + 5) % 5;
            } else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }

            plaintext.append(keyTable[r1][c1]);
            plaintext.append(keyTable[r2][c2]);
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        PlayfairCipher playfair = new PlayfairCipher("KEYWORD");
        String plaintext = "HELLOWORLD";
        String encrypted = playfair.encrypt(plaintext);
        String decrypted = playfair.decrypt(encrypted);

        System.out.println("Original: " + plaintext);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}



    private final char[][] keyTable;
    private int[] row;
    private int[] col;

    public PlayfairCipher(String key) {
        keyTable = new char[5][5];
        row = new int[26];
        col = new int[26];

        // Initialize key table and row/column arrays
        initializeKeyTable(key);
    }

    private void initializeKeyTable(String key) {
        // Remove spaces and convert to uppercase
        key = key.replaceAll(" ", "").toUpperCase();

        // Initialize the table with the key
        int k = 0;
        boolean[] used = new boolean[26];

        for (int i = 0; i < key.length(); i++) {
            char letter = key.charAt(i);
            if (!used[letter - 'A']) {
                keyTable[k / 5][k % 5] = letter;
                row[letter - 'A'] = k / 5;
                col[letter - 'A'] = k % 5;
                used[letter - 'A'] = true;
                k++;
            }
        }

        // Fill the remaining cells with the alphabet
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c != 'J' && !used[c - 'A']) {
                keyTable[k / 5][k % 5] = c;
                row[c - 'A'] = k / 5;
                col[c - 'A'] = k % 5;
                k++;
            }
        }
    }

    public String encrypt(String plaintext) {
        StringBuilder encryptedText = new StringBuilder();
        plaintext = plaintext.toUpperCase().replaceAll("J", "I");

        for (int i = 0; i < plaintext.length(); i += 2) {
            char c1 = plaintext.charAt(i);
            char c2 = (i + 1 < plaintext.length()) ? plaintext.charAt(i + 1) : 'X';

            int r1 = row[c1 - 'A'];
            int c1 = col[c1 - 'A'];
            int r2 = row[c2 - 'A'];
            int c2 = col[c2 - 'A'];

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

            encryptedText.append(keyTable[r1][c1]);
            encryptedText.append(keyTable[r2][c2]);
        }

        return encryptedText.toString();
    }

    public String decrypt(String ciphertext) {
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            char c1 = ciphertext.charAt(i);
            char c2 = ciphertext.charAt(i + 1);

            int r1 = row[c1 - 'A'];
            int c1 = col[c1 - 'A'];
            int r2 = row[c2 - 'A'];
            int c2 = col[c2 - 'A'];

            if (r1 == r2) {
                c1 = (c1 - 1 + 5) % 5;
                c2 = (c2 - 1 + 5) % 5;
            } else if (c1 == c2) {
                r1 = (r1 - 1 + 5) % 5;
                r2 = (r2 - 1 + 5) % 5;
            } else {
                int temp 
	c1 = c2;
                c2 = temp;
            }

            decryptedText.append(keyTable[r1][c1]);
            decryptedText.append(keyTable[r2][c2]);
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        PlayfairCipher playfair = new PlayfairCipher("KEYW

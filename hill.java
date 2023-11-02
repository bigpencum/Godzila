import java.util.Scanner;
class HillCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the key matrix
        System.out.println("Enter the key matrix (3x3):");
        int[][] keyMatrix = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                keyMatrix[i][j] = scanner.nextInt();
            }
        }

        // Get the plaintext
        System.out.println("Enter the plaintext (in uppercase):");
        String plaintext = scanner.next();

        // Ensure the plaintext length is a multiple of 3
        int padding = 0;
        if (plaintext.length() % 3 != 0) {
            padding = 3 - (plaintext.length() % 3);
            for (int i = 0; i < padding; i++) {
                plaintext += "X";
            }
        }

        // Encrypt the plaintext
        String ciphertext = encrypt(plaintext, keyMatrix);

        // Decrypt the ciphertext
        String decryptedText = decrypt(ciphertext, keyMatrix);

        // Display the results
        System.out.println("Encrypted: " + ciphertext);
        System.out.println("Decrypted: " + decryptedText);

        scanner.close();
    }

    public static String encrypt(String plaintext, int[][] keyMatrix) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += 3) {
            int[] block = new int[3];
            for (int j = 0; j < 3; j++) {
                block[j] = plaintext.charAt(i + j) - 'A';
            }

            int[] result = multiplyMatrix(keyMatrix, block);

            for (int j = 0; j < 3; j++) {
                ciphertext.append((char) (result[j] + 'A'));
            }
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, int[][] keyMatrix) {
        StringBuilder decryptedText = new StringBuilder();

        int[][] inverseMatrix = matrixInverse(keyMatrix);

        for (int i = 0; i < ciphertext.length(); i += 3) {
            int[] block = new int[3];
            for (int j = 0; j < 3; j++) {
                block[j] = ciphertext.charAt(i + j) - 'A';
            }

            int[] result = multiplyMatrix(inverseMatrix, block);

            for (int j = 0; j < 3; j++) {
                decryptedText.append((char) (result[j] + 'A'));
            }
        }

        return decryptedText.toString();
    }

    public static int[] multiplyMatrix(int[][] matrix, int[] vector) {
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
            result[i] %= 26;
        }
        return result;
    }

    public static int[][] matrixInverse(int[][] matrix) {
        int determinant = (matrix[0][0] * matrix[1][1] * matrix[2][2] +
                           matrix[0][1] * matrix[1][2] * matrix[2][0] +
                           matrix[0][2] * matrix[1][0] * matrix[2][1]) -
                          (matrix[0][2] * matrix[1][1] * matrix[2][0] +
                           matrix[0][0] * matrix[1][2] * matrix[2][1] +
                           matrix[0][1] * matrix[1][0] * matrix[2][2]);

        int inverseDeterminant = modInverse(determinant, 26);

        int[][] inverseMatrix = new int[3][3];

        inverseMatrix[0][0] = ((matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1]) * inverseDeterminant) % 26;
        inverseMatrix[0][1] = ((matrix[0][2] * matrix[2][1] - matrix[0][1] * matrix[2][2]) * inverseDeterminant) % 26;
        inverseMatrix[0][2] = ((matrix[0][1] * matrix[1][2] - matrix[0][2] * matrix[1][1]) * inverseDeterminant) % 26;

        inverseMatrix[1][0] = ((matrix[1][2] * matrix[2][0] - matrix[1][0] * matrix[2][2]) * inverseDeterminant) % 26;
        inverseMatrix[1][1] = ((matrix[0][0] * matrix[2][2] - matrix[0][2] * matrix[2][0]) * inverseDeterminant) % 26;
        inverseMatrix[1][2] = ((matrix[0][2] * matrix[1][0] - matrix[0][0] * matrix[1][2]) * inverseDeterminant) % 26;

        inverseMatrix[2][0] = ((matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0]) * inverseDeterminant) % 26;
        inverseMatrix[2][1] = ((matrix[0][1] * matrix[2][0] - matrix[0][0] * matrix[2][1]) * inverseDeterminant) % 26;
        inverseMatrix[2][2] = ((matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]) * inverseDeterminant) % 26;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (inverseMatrix[i][j] < 0) {
                    inverseMatrix[i][j] += 26;
                }
            }
        }

        return inverseMatrix;
    }

    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1;
    }
}


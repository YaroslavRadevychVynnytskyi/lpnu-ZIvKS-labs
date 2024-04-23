package com.application.encryptor;

public class HillCipher {
    private static final int KEY_SIZE = 3;
    private static final int[][] KEY =
            {{5,6,3},
             {5,3,2},
             {7,5,3}};

    public static String encrypt(String message) {
        message = message.toUpperCase();
        // check if det = 0
        validateDeterminant(KEY, KEY_SIZE);

        int[][] messageVector = new int[KEY_SIZE][1];
        String cipherText = "";
        int[][] cipherMatrix = new int[KEY_SIZE][1];
        int j = 0;
        while (j < message.length()) {
            for (int i = 0; i < KEY_SIZE; i++) {
                if (j >= message.length()) {
                    messageVector[i][0] = 23;
                } else {
                    messageVector[i][0] = (message.charAt(j)) % 65;
                }
                j++;
            }
            int x, i;
            for (i = 0; i < KEY_SIZE; i++) {
                cipherMatrix[i][0] = 0;

                for (x = 0; x < KEY_SIZE; x++) {
                    cipherMatrix[i][0] += KEY[i][x] * messageVector[x][0];
                }
                cipherMatrix[i][0] = cipherMatrix[i][0] % 26;
            }
            for (i = 0; i < KEY_SIZE; i++) {
                cipherText += (char) (cipherMatrix[i][0] + 65);
            }
        }
        return cipherText;
    }

    // Following function decrypts a message
    public static String decrypt(String message) {
        message = message.toUpperCase();
        validateDeterminant(KEY, KEY_SIZE);

        // Calculate the inverse of the key matrix
        int[][] inverseKeyMatrix = calculateInverse(KEY, KEY_SIZE);

        // solving for the required plaintext message
        int[][] messageVector = new int[KEY_SIZE][1];
        StringBuilder plainText = new StringBuilder();
        int[][] plainMatrix = new int[KEY_SIZE][1];
        int j = 0;
        while (j < message.length()) {
            for (int i = 0; i < KEY_SIZE; i++) {
                if (j >= message.length()) {
                    messageVector[i][0] = 23;
                } else {
                    messageVector[i][0] = (message.charAt(j)) % 65;
                }
                j++;
            }
            int x, i;
            for (i = 0; i < KEY_SIZE; i++) {
                plainMatrix[i][0] = 0;

                for (x = 0; x < KEY_SIZE; x++) {
                    plainMatrix[i][0] += inverseKeyMatrix[i][x] * messageVector[x][0];
                }

                plainMatrix[i][0] = plainMatrix[i][0] % 26;
            }
            for (i = 0; i < KEY_SIZE; i++) {
                plainText.append((char) (plainMatrix[i][0] + 65));
            }
        }
        return plainText.toString();
    }

    // Determinant calculator
    private static int determinant(int[][] a, int n) {
        int det = 0, sign = 1, p = 0, q = 0;

        if (n == 1) {
            det = a[0][0];
        } else {
            int[][] b = new int[n - 1][n - 1];
            for (int x = 0; x < n; x++) {
                p = 0;
                q = 0;
                for (int i = 1; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (j != x) {
                            b[p][q++] = a[i][j];
                            if (q % (n - 1) == 0) {
                                p++;
                                q = 0;
                            }
                        }
                    }
                }
                det = det + a[0][x] * determinant(b, n - 1) * sign;
                sign = -sign;
            }
        }
        return det;
    }

    // Function to calculate the inverse of the key matrix
    private static int[][] calculateInverse(int[][] keyMatrix, int n) {
        int det = determinant(keyMatrix, n);
        int[][] adjugate = new int[n][n];
        int[][] inverse = new int[n][n];

        // Calculate adjugate matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int[][] minor = new int[n - 1][n - 1];
                int p = 0, q = 0;
                for (int x = 0; x < n; x++) {
                    if (x == i) continue;
                    q = 0;
                    for (int y = 0; y < n; y++) {
                        if (y == j) continue;
                        minor[p][q++] = keyMatrix[x][y];
                    }
                    p++;
                }
                adjugate[j][i] = (int) Math.pow(-1, i + j) * determinant(minor, n - 1);
            }
        }

        // Calculate inverse matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverse[i][j] = (adjugate[i][j] % 26 + 26) % 26;
            }
        }

        return inverse;
    }

    // Function to validate determinant
    private static void validateDeterminant(int[][] keyMatrix, int n) {
        int det = determinant(keyMatrix, n);
        if (det == 0) {
            System.exit(0);
        }
    }
}
       
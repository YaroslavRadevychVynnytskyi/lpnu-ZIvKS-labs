package application.encryptor;

public class TranspositionCipher {
    private static final int LAYER = 2;
    private static final int FIRST_BUILDER = 0;
    private static final int SECOND_BUILDER = 1;

    public String encrypt(String message) {
        StringBuilder[] stringBuilders = new StringBuilder[LAYER];
        for (int i = 0; i < LAYER; i++) {
            stringBuilders[i] = new StringBuilder();
        }
        char[] messageCharArray = message.replaceAll("\\s", "").toCharArray();
        for (int i = 0; i < messageCharArray.length; i++) {
            if (i % 2 == 0) {
                stringBuilders[FIRST_BUILDER].append(messageCharArray[i]);
            } else {
                stringBuilders[SECOND_BUILDER].append(messageCharArray[i]);
            }
        }
        return stringBuilders[FIRST_BUILDER].append(stringBuilders[SECOND_BUILDER]).toString();
    }
}

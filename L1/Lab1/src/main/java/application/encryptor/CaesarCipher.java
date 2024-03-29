package application.encryptor;

public class CaesarCipher {
    private static final int ALPHABET_LENGTH = 26;
    private static final int UPPERCASE_ASCII_START = 65;
    private static final int LOWERCASE_ASCII_START = 97;

    public String encrypt(String message, int offset) {
        StringBuilder builder = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLowerCase(c)) {
                encryptCharConsideringCase(builder, c, offset, LOWERCASE_ASCII_START);
            } else {
                encryptCharConsideringCase(builder, c, offset, UPPERCASE_ASCII_START);
            }
        }
        return builder.toString();
    }

    private void encryptCharConsideringCase(StringBuilder builder,
                                            char character,
                                            int offset,
                                            int caseAsciiStart) {
        if (character != ' ') {
            int newCharPosition = (character + offset - caseAsciiStart)
                    % ALPHABET_LENGTH + caseAsciiStart;
            builder.append((char) newCharPosition);
        } else {
            builder.append(" ");
        }
    }
}

package decryptor;

import java.util.HashMap;
import java.util.Map;

public class FrequencyAnalysisDecryptor {
    private static final int ALPHABET_LENGTH = 26;

    private Map<Character, Double> getLettersFrequency(String text) {
        Map<Character, Double> freq = new HashMap<>();
        int total = 0;
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                c = Character.toLowerCase(c);
                freq.put(c, freq.getOrDefault(c, 0.0) + 1);
                total++;
            }
        }
        if (total == 0) return freq;
        for (char c : freq.keySet()) {
            freq.put(c, freq.get(c) / total);
        }
        return freq;
    }

    private String decryptCaesarCipher(String ciphertext, int offset) {
        StringBuilder decryptedText = new StringBuilder();
        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                decryptedText.append((char) (((c - base - offset + ALPHABET_LENGTH)
                        % ALPHABET_LENGTH) + base));
            } else {
                decryptedText.append(c);
            }
        }
        return decryptedText.toString();
    }

    public String frequencyAnalysis(String ciphertext) {
        Map<Character, Double> charactersFrequencyMap = getLettersFrequency(ciphertext);
        char mostCommonLetter = ' ';
        double maxFreq = -1;
        for (char c : charactersFrequencyMap.keySet()) {
            if (charactersFrequencyMap.get(c) > maxFreq) {
                maxFreq = charactersFrequencyMap.get(c);
                mostCommonLetter = c;
            }
        }
        int offset = (mostCommonLetter - 'e' + ALPHABET_LENGTH) % ALPHABET_LENGTH;
        return decryptCaesarCipher(ciphertext, offset);
    }
}

package main.java.company.ciphers;

public class CaesarBreaker {

    char mostCommon;
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public CaesarBreaker() {
        mostCommon = 'e';
    }

    public CaesarBreaker(char c) {
        mostCommon = c;
    }

    /**
     * Calculates the frequency of all the letters
     *
     * @param words is the input string
     * @return int[] contains frequency of letters in words
     */
    private int[] countLetters(String words) {
        int[] letterFrequency = new int[26];

        for (int index = 0; index < words.length(); index++) {

            char currChar = Character.toLowerCase(words.charAt(index));
            int alphabeticIndex = alphabet.indexOf(currChar);

            if (alphabeticIndex != -1) {
                letterFrequency[alphabeticIndex] += 1;
            }
        }
        return letterFrequency;
    }

    /**
     * Computes the index of the letter with the largest frequency
     *
     * @param freqs list of frequency mapped to the alphabetic index of each letter
     * @return maxIndexValue  the index of the letter with the largest frequency
     */
    private int maxIndex(int[] freqs) {
        int highestFrequencySoFar = 0;
        int maxIndexValue = 0;

        for (int index = 1; index < freqs.length; index++) {
            int currentFrequency = freqs[index];
            if (currentFrequency > highestFrequencySoFar) {
                highestFrequencySoFar = currentFrequency;
                maxIndexValue = index;
            }

        }
        return maxIndexValue;
    }


    public int getKey(String encrypted) {

        //  Calculate the frequency of all the letters using countLetters
        int[] freqs = countLetters(encrypted);

        //  compute the index of the largest frequency using maxIndex.
        int maxDex = maxIndex(freqs);
        // Use those values to determine the key,
        int mostCommonPos = mostCommon - 'a';
        int dkey = maxDex - mostCommonPos;
        if (maxDex < mostCommonPos) {
            dkey = 26 - (mostCommonPos - maxDex);
        }
        return dkey;
    }

    private String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();

        for (int index = start; index < message.length(); index += 2) {
            sb.append(message.charAt(index));
        }
        return sb.toString();
    }


    public String breakCaesarCipher(String encrypted) {
        int dkey = 26 - getKey(encrypted);
        //  create a CaesarCipher with the keys
        CaesarCipher cc = new CaesarCipher(dkey);

        // call decrypt on the encrypted string.
        return cc.decrypt(encrypted);
    }

    public String breakTwoCaesarCipher(String input) {
        int dkey1 = getKey(halfOfString(input, 0));
        int dkey2 = getKey(halfOfString(input, 1));

        System.out.println("keys " + dkey1 + " " + dkey2);

        //  create a CaesarCipher with the keys
        CaesarCipherTwo cc2 = new CaesarCipherTwo(26 - dkey1, 26 - dkey2);

        // call decrypt on the encrypted string.

        return cc2.encrypt(input);
    }
}

package main.java.company.ciphers;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private final int theKey;

    public CaesarCipher(int key) {
        theKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) +
                alphabet.substring(0, key);
        alphabet = alphabet + alphabet.toLowerCase();
        shiftedAlphabet = shiftedAlphabet + shiftedAlphabet.toLowerCase();
    }

    /**
     * @param c    character to shift
     * @param from originalAlphabet
     * @param to   shiftedAlphabet
     * @return the shifted character is alphabetic otherwise returns the original character
     */
    private char transformLetter(char c, String from, String to) {
        int idx = from.indexOf(c);
        // if currChar is in the alphabet
        if (idx != -1) {
            // find  the character in the shifted alphabet and return it
            return to.charAt(idx);
        }
        // otherwise return the original character
        return c;
    }

    /**
     * @param c a single character to encrypt to encrypt
     * @return the encrypted character
     */
    public char encryptLetter(char c) {
        return transformLetter(c, alphabet, shiftedAlphabet);
    }

    /**
     * @param c a single character to encrypt to decrypt
     * @return the decrypted character
     */
    public char decryptLetter(char c) {
        return transformLetter(c, shiftedAlphabet, alphabet);
    }

    /**
     * @param input is the string to transform
     * @param from  the original alphabet
     * @param to    the shifted alphabet
     * @return a string shifted using the to alphabet
     */
    private String transform(String input, String from, String to) {
        StringBuilder sb = new StringBuilder(input);

        for (int i = 0; i < sb.length(); i++) {
            // look at the character at ith character and call it currChar
            char currChar = sb.charAt(i);
            // look up the new shifted character
            currChar = transformLetter(currChar, from, to);
            // replace the original character with the shifted character
            sb.setCharAt(i, currChar);
        }
        return sb.toString();
    }

    /**
     * This method returns a String that is the input encrypted using
     * shiftedAlphabet.
     *
     * @param input to encypt
     * @return encrypted string
     */
    public String encrypt(String input) {
        return transform(input, alphabet, shiftedAlphabet);
    }

    /**
     * This method returns a String that is the encrypted String decrypted using the
     * key associated with this CaesarCipher object.
     *
     * @param input string to decrypt
     * @return decrypted message
     */
    public String decrypt(String input) {
        return transform(input, shiftedAlphabet, alphabet);
    }

    /**
     * @return the key used in the cipher
     */
    public String toString() {
        return "" + theKey;
    }

}



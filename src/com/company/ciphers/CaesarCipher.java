package com.company.ciphers;
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    private CaesarCipher() {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
    }
    public CaesarCipher(int key) {
        this();
        mainKey = key;
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }

    /**
     * This method returns a String that is the input encrypted using
     * shiftedAlphabet.
     *
     * @param input to encypt
     * @return encrypted string
     */
    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);

        for (int i = 0; i < sb.length(); i++) {
            // look at the character at ith character and call it currChar
            char currChar = sb.charAt(i);


            // if currChar is in the alphabet
            if (Character.isLetter(currChar)) {
                // find index of currChar in alphabet and call it alphabetIndex
                int alphabetIndex = alphabet.indexOf(Character.toLowerCase(currChar));

                // assign the character in the shifted alphabet that matches the alphabetIndex
                char newChar = shiftedAlphabet.charAt(alphabetIndex);

                if (Character.isUpperCase(currChar)) {
                    newChar = Character.toUpperCase(newChar);
                }

                // Replace the ith character in string with new Char
                sb.setCharAt(i, newChar);
            } // otherwise do nothing

        }
        return sb.toString();
    }

    /**
     * This method returns a String that is the encrypted String decrypted using the
     * key associated with this CaesarCipher object.
     *
     * @param input
     * @return decrypted message
     */
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }

}

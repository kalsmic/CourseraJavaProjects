package com.company.ciphers;

import java.util.Arrays;

public class VigenereCipher {
    CaesarCipher[] ciphers;

    /**
     * @param key is an array of integers
     */
    public VigenereCipher(int[] key) {
        ciphers = new CaesarCipher[key.length];
        // initializes the field ciphers, which is an array of CaesarCipher objects.
        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new CaesarCipher(key[i]);
        }
    }

    /**
     * This method encrypts the String passed in and returns the encrypted message
     *
     * @param input is a string to encrypt
     * @return encrypted message
     */
    public String encrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.encryptLetter(c));
            i++;
        }
        return answer.toString();
    }

    /**
     * This method decrypts the String passed in and returns the decrypted message.
     *
     * @param input is a string to decrypt
     * @return decrypted message
     */
    public String decrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.decryptLetter(c));
            i++;
        }
        return answer.toString();
    }

    /**
     * @return a String representing the key for this cipher.
     */
    public String toString() {
        return Arrays.toString(ciphers);
    }
}

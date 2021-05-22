package com.company.ciphers;

public class VigenereBreaker {
    /**
     * @param message     is the encrypted message,
     * @param whichSlice  is  the index the slice should start from
     * @param totalSlices is the length of the key
     * @return a String consisting of every totalSlices-th character from message, starting at the
     * whichSlice-th character
     */
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    /**
     * @param encrypted  is a string whose key we want to find
     * @param klength    the key length
     * @param mostCommon of the language used
     * @return the vigenere key for the encrypted message
     */
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarBreaker cb = new CaesarBreaker(mostCommon);
        int dkey;
        // look at each key index
        for (int i = 0; i < klength; i++) {
            // find the shift for current index in the key
            String encryptedSlice = sliceString(encrypted, i, klength);

            // find key for current encryptedSlice
            dkey = cb.getKey(encryptedSlice);
            // add the key to the key array
            key[i] = dkey;
        }
        return key;
    }

    public String breakVigenere(String encrypted, int klength) {
        // find the key for the message
        char mostCommon = 'e';
        return breakVigenere(encrypted, klength, mostCommon);
    }

    /**
     * @param encrypted  is a string  to decrypt
     * @param klength    the key length
     * @param mostCommon of the language used
     * @return the decrypted messagee
     */
    public String breakVigenere(String encrypted, int klength, char mostCommon) {
        // find the key for the message
        int[] key = tryKeyLength(encrypted, klength, mostCommon);

        // create a new VigenereCipher object with the found key
        VigenereCipher vc = new VigenereCipher(key);
        // Decrypt the message

        return vc.decrypt(encrypted);
    }

}
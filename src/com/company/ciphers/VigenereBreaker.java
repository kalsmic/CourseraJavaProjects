package com.company.ciphers;

import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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

    /**
     *
     * @param fr a FileResource which contains exactly one word per line
     * @return  the HashSet representing the words in a dictionary
     */
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();

        for(String word: fr.lines()){
            dictionary.add(word.toLowerCase());
        }
        return dictionary;
    }

    /**
     *
     * @param message a string to analyze
     * @param dictionary is a HashSet of Strings
     * @return  the integer count of how many valid words it found.
     */
    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        // split the message into an array of string words
        ArrayList<String> messageArray = new ArrayList<String>(List.of(message.toLowerCase().split("\\W+")));

        for(String word : messageArray){
            if(dictionary.contains(word)){
                count += 1;
            }
        }
        return count;
    }

    /**
     *
     * @param encrypted string
     * @param dictionary is a HashSet of Strings dictionary
     * @return  the decrypted String
     */
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int highestMatch = 0;
        String decrypted = null;
        char mostCommon = mostCommonCharIn(dictionary);
        // try key length for range 1 to 100
        for(int klength = 1; klength < 100; klength++){
            // find the key for the message
            int[] key = tryKeyLength(encrypted, klength, mostCommon);

            // create a new VigenereCipher object with the found key
            VigenereCipher vc = new VigenereCipher(key);

            // use calculated key to decrypt the message
            String currMessage = vc.decrypt(encrypted);

            int numValidWords = countWords(currMessage.trim(),dictionary);

            if(numValidWords > highestMatch){
                highestMatch = numValidWords;
                decrypted = currMessage;
            }

        }

        return decrypted;
    }

    /**
     * @param dictionary is a HashSet of Strings dictionary
     * @return the most commonly occurring character which appears most often in the words in dictionary
     */
    public char mostCommonCharIn(HashSet<String> dictionary) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] frequency = new int[27];

        for (String word : dictionary) {
            for (char c : word.toCharArray()) {
                int letterIndex = alphabet.indexOf(c);

                if (letterIndex != -1) {
                    frequency[letterIndex] += 1;
                }
            }
        }

        int highestSoFar = 0;
        int highestIndex = 0;
        for(int i =0; i<frequency.length; i++){
            if(frequency[i] > highestSoFar){
                highestIndex = i;
                highestSoFar = frequency[i];
            }
        }
        return alphabet.charAt(highestIndex);
    }

    /**
     *
     * @param encrypted is a String encrypted
     * @param languages is s HashMap,mapping a String representing the name of a language to a HashSet of Strings
     *                 containing the words in that language.
     * @return the decrypted message
     */
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        String decrypted = null;
        int mostWords =0;
        String messageLanguage = null;
        // look at each language
        for(String language:languages.keySet()){

            HashSet<String> dictionary = languages.get(language);

            // Try breaking the encryption for each language
            String currMessage = breakForLanguage(encrypted,dictionary);

            // count how many words match for given language
            int currCount = countWords(currMessage, dictionary);
            // if the current words match more than the mostSoFar
            if(currCount > mostWords){
                // set most words to current words
                mostWords = currCount;
                // set decrypted message to current message
                decrypted = currMessage;
                // set language to current language
                messageLanguage = language;
            }
        }

    System.out.println("Language is " + messageLanguage);
    System.out.println(mostWords +" words matched");

        return decrypted;
    }
}
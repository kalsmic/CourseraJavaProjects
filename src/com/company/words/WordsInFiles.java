package com.company.words;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordFileMap;

    public WordsInFiles() {
        wordFileMap = new HashMap<String, ArrayList<String>>();
    }

    public void addWordsFromFile(File f) {
        FileResource resource = new FileResource(f);
        for (String word : resource.words()) {
            word = word.toLowerCase();
            // if word already in the dictionary
            if (wordFileMap.containsKey(word)) {
                // add file in which it appears to the Array list
                wordFileMap.get(word).add(f.getName());
            } else {
                // create a new arraylist of Strings
                ArrayList<String> fileList = new ArrayList<String>(List.of(f.getName()));
                // add word and array list to wordFileMap dictionary
                wordFileMap.put(word, fileList);
            }
        }
    }

    public void buildWordFileMap() {
        wordFileMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    /**
     * @return the maximum number of files any word appears in
     */
    public int maxNumber() {
        int highestSoFar = 0;
        for (String key : wordFileMap.keySet()) {
            int currentValue = wordFileMap.get(key).size();
            if (currentValue > highestSoFar) {
                highestSoFar = currentValue;
            }
        }
        return highestSoFar;
    }

    /**
     * @param number of files word appears in
     * @return an ArrayList of words that appear in exactly number file
     */
    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<String>();

        for (String word : wordFileMap.keySet()) {
            int numberOfFiles = wordFileMap.get(word).size();
            if (numberOfFiles == number) {
                words.add(word);
            }
        }

        return words;
    }

    /**
     * This method prints the names of the files this word appears in
     *
     * @param word to search for
     */
    public void printFilesIn(String word) {
        if (wordFileMap.containsKey(word)) {
            ArrayList<String> fileList = wordFileMap.get(word);
            for (String s : fileList) {
                System.out.println(s);
            }

        }

    }

    public void similarWordsNum() {
        int mainCount = 0;
        for (String s : wordFileMap.keySet()) {
            ArrayList<String> values = wordFileMap.get(s);
            int count = 0;
            if (values.contains("caesar.txt")) count += 1;
            if (values.contains("hamlet.txt")) count += 1;
            if (values.contains("likeit.txt")) count += 1;
            if (values.contains("macbeth.txt")) count += 1;
            if (values.contains("romeo.txt")) count += 1;

            if (count == 4) mainCount += 1;

        }
        System.out.println(mainCount + "words appear in five files");
        System.out.println(wordFileMap.size() + "words appear in five files");
    }
}

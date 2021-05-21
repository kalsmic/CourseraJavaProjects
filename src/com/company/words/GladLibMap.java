package com.company.words;

import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GladLibMap {
    private static final String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static final String dataSourceDirectory = "com/data";
    private final HashMap<String, ArrayList<String>> myMap;
    private final HashMap<String, String> myLabelSource = new HashMap<String, String>();
    private final Random myRandom;
    private ArrayList<String> wordsConsideredList;

    public GladLibMap() {

        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }


    public void initializeFromSource(String source) {
        String[] labels = {
                "country", "noun", "animal", "adjective", "name", "fruit", "color", "timeframe", "time", "verb"
        };
        for (String s : labels) {
            ArrayList<String> list = readIt(source + "/" + s + ".txt");
            myMap.put(s, list);
        }
        // TODO load sources using a .property file
//        for (String s : myLabelSource.keySet()) {
//            ArrayList<String> list = readIt(myLabelSource.get(s));
//            myMap.put(s, list);
//        }
    }

    private String getSubstitute(String label) {
        if (label.equals("number")) {
            return "" + myRandom.nextInt(50) + 5;
        }
        return randomFrom(myMap.get(label));
    }

    /**
     * @return the total number of words in all the ArrayLists in the HashMap
     */
    public int totalWordsInMap() {
        // declare and initialize total num of words to zero
        int totalNumWords = 0;

        // for each category
        for (String category : myMap.keySet()) {
            // increase the totalNumberWords by number of words in the Array list of words for category
            totalNumWords += myMap.get(category).size();
        }
        return totalNumWords;
    }

    /**
     * @return the total number of words in the ArrayLists of the categories that were used for a particular GladLib
     */
    public int totalWordsConsidered() {
        return wordsConsideredList.size();
    }

    /**
     * @param source to the file or urlResource containing words
     * @return Arraylist of words in file
     */
    private ArrayList<String> readIt(String source) {
        ArrayList<String> wordList = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines()) {
                wordList.add(line);
            }
        } else {
            FileResource fr = new FileResource(source);
            for (String word : fr.words()) {
                wordList.add(word);
            }
        }
        return wordList;
    }


    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String sub = getSubstitute(w.substring(first + 1, last));
        while (true) {
            if (!wordsConsideredList.contains(sub)) {
                wordsConsideredList.add(sub);
                break;
            }
            sub = getSubstitute(w.substring(first + 1, last));
        }

        return prefix + sub + suffix;
    }

    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source) {
        StringBuilder story = new StringBuilder();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String word : resource.words()) {
                story.append(processWord(word)).append(" ");
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String word : resource.words()) {
                story.append(processWord(word)).append(" ");
            }
        }
        return story.toString();
    }


    public void makeStory() {
        wordsConsideredList.clear();
        System.out.println("\n");
        String story = fromTemplate("com/data/madtemplate3.txt");
        printOut(story, 60);
        System.out.println("\n");
        System.out.println("Total number of words replaced is " + wordsConsideredList.size());
        System.out.println("Total number of words that were possible to pick from " + totalWordsInMap());

    }

}
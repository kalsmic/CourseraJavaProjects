package main.java.company.words;

import edu.duke.FileResource;

import java.util.HashMap;

public class WordFrequencyMap {
    private final HashMap<String, Integer> wordFrequencies;

    public WordFrequencyMap() {
        wordFrequencies = new HashMap<String, Integer>();
    }

    public void findUnique(FileResource resource) {
        for (String s : resource.words()) {
            s = s.toLowerCase();

            if (wordFrequencies.containsKey(s)) {
                wordFrequencies.put(s, wordFrequencies.get(s) + 1);
            } else {
                wordFrequencies.put(s, 1);
            }

        }
        System.out.println("num of unique words = " + wordFrequencies.size());
    }

    /**
     * @return an int that is the index location of the largest value in myFreqs.
     */
    public String findKeyOfMax() {
        int highestSoFar = 0;
        String indexOfMax = null;

        for (String s : wordFrequencies.keySet()) {
            // get value at current index
            int currValue = wordFrequencies.get(s);

            //  compare value against highestSoFar
            if (currValue > highestSoFar) {
                highestSoFar = currValue;
                indexOfMax = s;

            }
        }
        System.out.println(indexOfMax + " occurs " + wordFrequencies.get(indexOfMax));
        return indexOfMax;
    }

}

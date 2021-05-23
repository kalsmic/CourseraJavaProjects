package main.company.words;

import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequency {
    private final ArrayList<String> myWords;
    private final ArrayList<Integer> myFreqs;

    public WordFrequency() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique(FileResource resource) {
        // clear
        myFreqs.clear();
        myWords.clear();
        for (String s : resource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            // check if word has never been seen
            if (index == -1) {
                // if so add it to myWords
                myWords.add(s);
                myFreqs.add(1);
            } else {
                // get frequency value at index
                int value = myFreqs.get(index);
                //  increase it by one
                myFreqs.set(index, value + 1);

            }
        }
        System.out.println("num of unique words = " + myWords.size());
    }

    /**
     * @return an int that is the index location of the largest value in myFreqs.
     */
    public int findIndexOfMax() {
        int highestSoFar = 0;
        int indexOfMax = 0;

        for (int i = 0; i < myFreqs.size(); i++) {
            // get value at current index
            int currValue = myFreqs.get(i);

            //  compare value against highestSoFar
            if (currValue > highestSoFar) {
                highestSoFar = currValue;
                indexOfMax = i;

            }
        }
        System.out.println(myWords.get(indexOfMax) + " occurs " + myFreqs.get(indexOfMax) + " most often with index of " + indexOfMax);
        return indexOfMax;
    }

}

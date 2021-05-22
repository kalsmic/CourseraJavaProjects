package com.company.words;

import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> characterNames;
    private ArrayList<Integer> numSpeakingParts;

    public CharactersInPlay() {
        characterNames = new ArrayList<String>();
        numSpeakingParts = new ArrayList<Integer>();
    }

    /**
     * update the characterNames and numSpeakingParts if name is not in characterNames
     * and add one speaking part
     *
     * @param person characters name
     */
    private void update(String person) {
        int index = characterNames.indexOf(person);
        if (index == -1) {
            characterNames.add(person);
            numSpeakingParts.add(1);
        } else {
            int value = numSpeakingParts.get(index);
            numSpeakingParts.set(index, value + 1);
        }
    }

    /**
     * counts the possible speakers and counts speaking parts of the person
     */
    public void findAllCharacters(FileResource resource) {
        // open file
//        FileResource resource = new FileResource();

        // read file line by line
        for (String line : resource.lines()) {
            // if there is a period on the line
            if (line.contains(".")) {
                //extract the possible name of the speaking part
                int periodIndex = line.indexOf(".");
                String speakerName = line.substring(0, periodIndex);
                //  count it as an occurrence for this person
                update(speakerName);
            }

        }
    }

    /**
     * This method prints out the names of all those characters that have exactly number speaking parts,
     * where number is greater than or equal to num1 and less than or equal to num2.
     *
     * @param num1 least number of speaking parts
     * @param num2 greatest number of speaking parts
     */
    public void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < numSpeakingParts.size(); i++) {
            int speakingParts = numSpeakingParts.get(i);
            if (speakingParts >= num1 && speakingParts <= num2) {
                System.out.println(characterNames.get(i) + " " + speakingParts);
            }
        }


    }
}

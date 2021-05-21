package com.company.words;

import java.util.HashMap;

public class DNACodon {
    private HashMap<String, Integer> codonCount;

    public DNACodon() {
        codonCount = new HashMap<String, Integer>();
    }

    /**
     * This method will build a new map of codons mapped to their counts from the string dna with the reading frame with the position start (a value of 0, 1, or 2).
     *
     * @param start position to read dna codons
     * @param dna   a string containing a strand of dna
     */
    public void buildCodonMap(int start, String dna) {
        System.out.print("Start " + start);
        dna = dna.trim();
        codonCount.clear();
        while (dna.length() - start >= 3) {
            String codon = dna.substring(start, start + 3);
            if (codonCount.containsKey(codon)) {
                codonCount.put(codon, codonCount.get(codon) + 1);
            } else {
                codonCount.put(codon, 1);
            }
            start += 3;
        }
        System.out.println(" has " + codonCount.size());
    }

    /**
     * This method assumes the HashMap of codons to counts has already been built.
     *
     * @return String, the codon in a reading frame that has the largest count.
     */
    public String getMostCommonCodon() {
        String commonCodon = "";
        int highestSoFar = 0;

        for (String s : codonCount.keySet()) {
            int currentValue = codonCount.get(s);
            if (currentValue > highestSoFar) {
                commonCodon = s;
                highestSoFar = currentValue;
            }
        }
        System.out.println("Most common codon is " + commonCodon + " and occurs " + highestSoFar + " times");
        return commonCodon;
    }

    /**
     * This method prints all the codons
     * in the HashMap along with their counts if their count is between start and end, inclusive.
     *
     * @param start minimum number of codos
     * @param end   maximum number of codons
     */
    public void printCodonCounts(int start, int end) {
        for (String codon : codonCount.keySet()) {
            int value = codonCount.get(codon);
            if (value >= start && value <= end) {
                System.out.println(codon + " : " + value);
            }

        }

    }

}

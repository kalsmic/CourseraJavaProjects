package main.tests.src.words;

import main.company.words.DNACodon;

import org.junit.jupiter.api.Test;



class DNACodonTest {
    String dna = "CGTTCAAGTTCAA";
    DNACodon dnaCodon = new DNACodon();

    @Test
    void buildCodonMap() {
    }

    @Test
    void getMostCommonCodon() {
        dnaCodon.buildCodonMap(0, dna);
        String expected = "TCA";
        assertEquals(expected, dnaCodon.getMostCommonCodon());

        dnaCodon.buildCodonMap(1, dna);
        expected = "CAA";
        assertEquals(expected, dnaCodon.getMostCommonCodon());

        dnaCodon.buildCodonMap(2, dna);
        expected = "TTC";
        assertEquals(expected, dnaCodon.getMostCommonCodon());

    }

    @Test
    void getMostCommonCodon2() {
        FileResource fr = new FileResource("main/data/dnaMystery2.txt");
        dna = fr.asString();
        dnaCodon.buildCodonMap(1, dna);

        String expected = "AAA";
        assertEquals(expected, dnaCodon.getMostCommonCodon());
    }

    @Test
    void printCodonCounts() {
    }
}
package main.tests.src.words;

import main.company.words.DNACodon;
import edu.duke.FileResource;
import org.junit.Assert;
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
        Assert.assertEquals(expected, dnaCodon.getMostCommonCodon());

        dnaCodon.buildCodonMap(1, dna);
        expected = "CAA";
        Assert.assertEquals(expected, dnaCodon.getMostCommonCodon());

        dnaCodon.buildCodonMap(2, dna);
        expected = "TTC";
        Assert.assertEquals(expected, dnaCodon.getMostCommonCodon());

    }

    @Test
    void getMostCommonCodon2() {
        FileResource fr = new FileResource("com/data/dnaMystery2.txt");
        dna = fr.asString();
        dnaCodon.buildCodonMap(1, dna);

        String expected = "AAA";
        Assert.assertEquals(expected, dnaCodon.getMostCommonCodon());
    }

    @Test
    void printCodonCounts() {
    }
}
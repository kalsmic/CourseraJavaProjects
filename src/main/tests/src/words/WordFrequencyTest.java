package main.tests.src.words;

import main.company.words.WordFrequency;
import edu.duke.FileResource;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WordFrequencyTest {
    WordFrequency wf;


    @BeforeEach
    void setUp() {
        wf = new WordFrequency();

    }

    @AfterEach
    void tearDown() {
        wf = null;
    }


    @Test
    void findIndexOfMax() {
        FileResource resource = new FileResource("main/data/testwordfreqs.txt");
        wf.findUnique(resource);
        int maxIndex = wf.findIndexOfMax();
        Assert.assertEquals(2, maxIndex);


    }

    @Test
    void findIndexOfMax2() {
        FileResource resource = new FileResource("main/data/likeit.txt");
        wf.findUnique(resource);
        int maxIndex = wf.findIndexOfMax();
//        Assert.assertEquals(2, maxIndex);

    }

    @Test
    void findIndexOfMax3() {
        FileResource resource = new FileResource("main/data/errors.txt");
        wf.findUnique(resource);
        wf.findIndexOfMax();
//        int maxIndex = wf.findUnique();
//        Assert.assertEquals(2, maxIndex);
//
    }

}
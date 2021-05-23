package test.java.company.words;

import main.java.company.words.WordFrequency;
import edu.duke.FileResource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        FileResource resource = new FileResource("src/main/resources/data/testwordfreqs.txt");
        wf.findUnique(resource);
        int maxIndex = wf.findIndexOfMax();
        assertEquals(2, maxIndex);


    }

    @Test
    void findIndexOfMax2() {
        FileResource resource = new FileResource("src/main/resources/data/likeit.txt");
        wf.findUnique(resource);
        int maxIndex = wf.findIndexOfMax();
//        Assert.assertEquals(2, maxIndex);

    }

    @Test
    void findIndexOfMax3() {
        FileResource resource = new FileResource("src/main/resources/data/errors.txt");
        wf.findUnique(resource);
        wf.findIndexOfMax();
//        int maxIndex = wf.findUnique();
//        Assert.assertEquals(2, maxIndex);
//
    }

}
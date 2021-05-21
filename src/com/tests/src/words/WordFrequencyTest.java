package com.tests.src.words;

import com.company.words.WordFrequency;
import edu.duke.FileResource;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
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
        FileResource resource = new FileResource("com/data/testwordfreqs.txt");
        wf.findUnique(resource);
        int maxIndex = wf.findIndexOfMax();
        Assert.assertEquals(2, maxIndex);


    }

    @Test
    void findIndexOfMax2() {
        FileResource resource = new FileResource("com/data/likeit.txt");
        wf.findUnique(resource);
        int maxIndex = wf.findIndexOfMax();
//        Assert.assertEquals(2, maxIndex);

    }

    @Test
    void findIndexOfMax3() {
        FileResource resource = new FileResource("com/data/errors.txt");
        wf.findUnique(resource);
        wf.findIndexOfMax();
//        int maxIndex = wf.findUnique();
//        Assert.assertEquals(2, maxIndex);
//
    }

    class Mine {
        public Mine() {

        }

        public int mineHead() {
            return 1;
        }

    }
}
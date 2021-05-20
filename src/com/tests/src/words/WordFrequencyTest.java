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
        FileResource resource = new FileResource("com/tests/src/data/testwordfreqs.txt");
        wf.findUnique(resource);
    }

    @AfterEach
    void tearDown() {
        wf = null;
    }


    @Test
    void findIndexOfMax() {
        int maxIndex = wf.findIndexOfMax();
        Assert.assertEquals(2, maxIndex);
    }
}
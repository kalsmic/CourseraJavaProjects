package com.tests.src.ciphers;

import com.company.ciphers.VigenereBreaker;
import edu.duke.FileResource;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class VigenereBreakerTest {
    VigenereBreaker vb;

    @BeforeEach
    void setUp() {
        vb = new VigenereBreaker();
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Test slice string")
    @ParameterizedTest(name = "{index}=> message={0},whichSlice={1},totalSlices={2}, expected={3}")
    @CsvSource({
            "abcdefghijklm,0,3,adgjm ",
            "abcdefghijklm,1,3,behk ",
            "abcdefghijklm, 2, 3,cfil",
            "abcdefghijklm, 0, 4,aeim",
            "abcdefghijklm, 1, 4,bfj",
            "abcdefghijklm, 2, 4,cgk",
            "abcdefghijklm, 3, 4,dhl",
            "abcdefghijklm, 0, 5,afk",
            "abcdefghijklm, 1, 5,bgl",
            "abcdefghijklm, 2, 5,chm",
            "abcdefghijklm, 3, 5,di",
            "abcdefghijklm, 4, 5,ej",
    })
    void sliceString(String message, int whichSlice, int totalSlices, String expected) {
        Assert.assertEquals(expected, vb.sliceString(message, whichSlice, totalSlices));
    }

    @Test
    void tryKeyLength() {
        int[] expected = {5, 11, 20, 19, 4};
        String encrypted = new FileResource("src/com/data/athens_keyflute.txt").asString();
        int[] key = vb.tryKeyLength(encrypted, 5, 'e');
        Assert.assertArrayEquals(expected, key);
    }

    @Test
    void breakVigenere() {
        String encrypted = new FileResource("src/com/data/athens_keyflute.txt").asString();
        String expected = new FileResource("src/com/data/athens.txt").asString();
        String decrypted = vb.breakVigenere(encrypted, 5);
        Assert.assertEquals(expected, decrypted);

    }
}
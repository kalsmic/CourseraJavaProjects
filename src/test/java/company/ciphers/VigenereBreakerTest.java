package test.java.company.ciphers;

import edu.duke.FileResource;
import main.java.company.ciphers.VigenereBreaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class VigenereBreakerTest {
    private VigenereBreaker vb;

    @BeforeEach
    void setUp() {
        vb = new VigenereBreaker();
    }


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
        assertEquals(expected, vb.sliceString(message, whichSlice, totalSlices));
    }

    @Test
    void tryKeyLength() {
        int[] expected = {5, 11, 20, 19, 4};
        String encrypted = new FileResource("src/main/resources/data/athens_keyflute.txt").asString();
        int[] key = vb.tryKeyLength(encrypted, 5, 'e');
        assertArrayEquals(expected, key);
    }

    @Test
    void tryKeyLength2() {
        int[] expected = {3, 20, 10, 4};
        String encrypted = new FileResource("src/main/resources/data/secretmessage1.txt").asString();

        int[] key = vb.tryKeyLength(encrypted, 4, 'e');
        assertArrayEquals(expected, key);
    }

    @Test
    void breakVigenere() {
        String encrypted = new FileResource("src/main/resources/data/athens_keyflute.txt").asString();
        String expected = new FileResource("src/main/resources/data/athens.txt").asString();
        String decrypted = vb.breakVigenere(encrypted, 5);
        assertEquals(expected, decrypted);

    }

    @Test
    void breakVigenere2() {
        String encrypted = new FileResource("src/main/resources/data/secretmessage1.txt").asString();
        String expected = "Enter BRUTUS and CASSIUS, and a throng of Citizens";
        String decrypted = vb.breakVigenere(encrypted, 4);
        assertEquals(expected, decrypted.substring(0, expected.length()));

    }

    @Test
    void readDictionary() {
        FileResource fr = new FileResource("src/main/resources/data/dictionaries/English");
        HashSet<String> dictionary = vb.readDictionary(fr);
        assertEquals(72053, dictionary.size());
        assertTrue(dictionary.contains("genesis"));
        assertFalse(dictionary.contains("bonjour"));
    }

    @Test
    void countWords() {
        String message = "my God country bonjour";
        FileResource fr = new FileResource("src/main/resources/data/dictionaries/English");
        HashSet<String> englishDictionary = vb.readDictionary(fr);
        int words = vb.countWords(message, englishDictionary);
        assertEquals(3, words);
    }

    @Test
    void breakForLanguage() {
        FileResource fr = new FileResource("src/main/resources/data/dictionaries/English");
        HashSet<String> dictionary = vb.readDictionary(fr);
        String expected = new FileResource("src/main/resources/data/athens.txt").asString();
        String encrypted = new FileResource("src/main/resources/data/athens_keyflute.txt").asString();
        String decrypted = vb.breakForLanguage(encrypted, dictionary);
        assertEquals(expected, decrypted);
    }

    @Test
    void breakForLanguage2() {
        FileResource fr = new FileResource("src/main/resources/data/dictionaries/English");
        HashSet<String> dictionary = vb.readDictionary(fr);
        String expected = "The Tragedy of Hamlet, Prince of Denmark";
        String encrypted = new FileResource("src/main/resources/data/secretmessage2.txt").asString();
        String decrypted = vb.breakForLanguage(encrypted, dictionary);
        assertEquals(expected, decrypted.substring(0, expected.length()));
    }


    @DisplayName("Get most common letter for language")
    @ParameterizedTest(name = "{index}=> filename={0},expected={1}")
    @CsvSource({
            "English,e ",
            "Danish,e",
            "Dutch, e",
            "French, e",
            "German, e",
            "Italian, a",
            "Portuguese, a",
            "Spanish, a",
    })
    void mostCommonCharIn(String filename, char expected) {
        FileResource fr = new FileResource("src/main/resources/data/dictionaries/" + filename);
        HashSet<String> dictionary = vb.readDictionary(fr);
        char mostCommon = vb.mostCommonCharIn(dictionary);
        System.out.println(mostCommon);
        Character c1 = expected;
        Character c2 = mostCommon;
        assertEquals(c1, c2);
    }

    @Test
    void breakForAllLangs() {
        String expected = new FileResource("src/main/resources/data/athens.txt").asString();
        String encrypted = new FileResource("src/main/resources/data/athens_keyflute.txt").asString();

        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();

        languages.put("English", vb.readDictionary(new FileResource("src/main/resources/data/dictionaries/English")));
        languages.put("Danish", vb.readDictionary(new FileResource("src/main/resources/data/dictionaries/Danish")));
        languages.put("Dutch", vb.readDictionary(new FileResource("src/main/resources/data/dictionaries/Dutch")));
        languages.put("French", vb.readDictionary(new FileResource("src/main/resources/data/dictionaries/French")));
        languages.put("German", vb.readDictionary(new FileResource("src/main/resources/data/dictionaries/German")));
        languages.put("Italian", vb.readDictionary(new FileResource("src/main/resources/data/dictionaries/Italian")));
        languages.put("Portuguese", vb.readDictionary(new FileResource("src/main/resources/data/dictionaries/Portuguese")));
        languages.put("Spanish", vb.readDictionary(new FileResource("src/main/resources/data/dictionaries/Spanish")));
        String decrypted = vb.breakForAllLangs(encrypted, languages);
        assertEquals(expected, decrypted);

    }
}

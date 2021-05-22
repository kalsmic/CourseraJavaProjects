package com.tests.src.ciphers;

import com.company.ciphers.CaesarBreaker;
import edu.duke.FileResource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaesarBreakerTest {
    CaesarBreaker cb = new CaesarBreaker();


    @Test
    void breakCaesarCipher() {

    }

    @Test
    void breakTwoCaesarCipher() {
        String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        String decrypted = cb.breakTwoCaesarCipher(message);
        String expected = "The name of the Java Mascot is Duke. Woeoeee!";
        assertEquals(decrypted, expected);

        FileResource fr = new FileResource("com/data/mysteryTwoKeysQuiz.txt");
        message = fr.asString();
        decrypted = cb.breakTwoCaesarCipher(message);
        expected = "Duke Computer Science Department Overview";
        assertEquals(decrypted.substring(0,41), expected);


    }
}
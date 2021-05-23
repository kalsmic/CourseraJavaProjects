package test.java.company.ciphers;

import main.java.company.ciphers.CaesarBreaker;
import edu.duke.FileResource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaesarBreakerTest {
    private CaesarBreaker cb = new CaesarBreaker();


    @Test
    void breakTwoCaesarCipher() {
        String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        String decrypted = cb.breakTwoCaesarCipher(message);
        String expected = "The name of the Java Mascot is Duke. Woeoeee!";
        assertEquals(decrypted, expected);

        FileResource fr = new FileResource("src/main/resources/data/mysteryTwoKeysQuiz.txt");
        message = fr.asString();
        decrypted = cb.breakTwoCaesarCipher(message);
        expected = "Duke Computer Science Department Overview";
        assertEquals(decrypted.substring(0, 41), expected);


    }
}
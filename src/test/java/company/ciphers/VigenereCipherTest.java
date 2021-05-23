package test.java.company.ciphers;

import main.java.company.ciphers.VigenereCipher;
import edu.duke.FileResource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VigenereCipherTest {
    private int[] key = {17, 14, 12, 4};
    private VigenereCipher vc;
    private String testMessage = new FileResource("src/main/resources/data/titus-small.txt").asString();
    private String testEncryptedMessage = new FileResource("src/main/resources/data/titus-small-key17-14-12-4.txt").asString();


    @AfterEach
    void tearDown() {
        vc = null;
    }

    @Test
    void encrypt() {
        VigenereCipher vc = new VigenereCipher(new int[]{17, 14, 12, 4});
        String encrypted = vc.encrypt(testMessage);
        assertEquals(testEncryptedMessage, encrypted);
    }

    @Test
    void decrypt() {
        VigenereCipher vc = new VigenereCipher(new int[]{17, 14, 12, 4});
        String decrypted = vc.decrypt(testEncryptedMessage);
        assertEquals(testMessage, decrypted);

    }


    @Test
    void testToString() {
        VigenereCipher vc = new VigenereCipher(new int[]{17, 14, 12, 4});
        assertEquals("[17, 14, 12, 4]", vc.toString());
    }
}
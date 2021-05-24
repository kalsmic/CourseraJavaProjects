package test.java.company.ciphers;

import edu.duke.FileResource;
import main.java.company.ciphers.VigenereCipher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VigenereCipherTest {
    private int[] key = new int[]{17, 14, 12, 4};
    private VigenereCipher vc;
    private String testMessage = new FileResource("src/main/resources/data/titus-small.txt").asString();
    private String testEncryptedMessage = new FileResource("src/main/resources/data/titus-small-key17-14-12-4.txt").asString();


    @BeforeEach
    void setUp() {
        vc = new VigenereCipher(key);
    }

    @AfterEach
    void tearDown() {
        vc = null;
    }

    @Test
    void encrypt() {
        String encrypted = vc.encrypt(testMessage);
        assertEquals(testEncryptedMessage, encrypted);
    }

    @Test
    void decrypt() {
        String decrypted = vc.decrypt(testEncryptedMessage);
        assertEquals(testMessage, decrypted);

    }

    @Test
    void testToString() {
        assertEquals("[17, 14, 12, 4]", vc.toString());
    }
}
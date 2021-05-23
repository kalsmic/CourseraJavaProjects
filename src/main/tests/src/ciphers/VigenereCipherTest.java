package main.tests.src.ciphers;

import main.company.ciphers.VigenereCipher;
import edu.duke.FileResource;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class VigenereCipherTest {
    int[] key = {17, 14, 12, 4};
    VigenereCipher vc;
    String testMessage = new FileResource("src/com/data/titus-small.txt").asString();
    String testEncryptedMessage = new FileResource("src/com/data/titus-small-key17-14-12-4.txt").asString();


    @AfterEach
    void tearDown() {
        vc = null;
    }

    @Test
    void encrypt() {
        VigenereCipher vc = new VigenereCipher(new int[]{17, 14, 12, 4});
        String encrypted = vc.encrypt(testMessage);
        Assert.assertEquals(testEncryptedMessage, encrypted);
    }

    @Test
    void decrypt() {
        VigenereCipher vc = new VigenereCipher(new int[]{17, 14, 12, 4});
        String decrypted = vc.decrypt(testEncryptedMessage);
        Assert.assertEquals(testMessage, decrypted);

    }


    @Test
    void testToString() {
        VigenereCipher vc = new VigenereCipher(new int[]{17, 14, 12, 4});
        Assert.assertEquals("[17, 14, 12, 4]", vc.toString());
    }
}
package main.tests.src.ciphers;

import main.company.ciphers.CaesarCipherTwo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaesarCipherTwoTest {
    String message1 = "Aran ajd Eiihy hwva avel earee gnean ewro";
    String message2 = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
    String message3 = "Hfs cpwewloj loks cd Hoto kyg Cyy.";

    @Test
    void encrypt() {
        String expected = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        CaesarCipherTwo cc2 = new CaesarCipherTwo(0, 19);
        String encrypted = cc2.encrypt(message1);

        String decrypted = cc2.decrypt(encrypted);
        assertEquals(encrypted, expected);
        assertEquals(decrypted, message1);

        CaesarCipherTwo cc3 = new CaesarCipherTwo(21, 8);
        encrypted = cc3.encrypt(message2);
        expected = "Xii twp duvodvz gqam EDBCWPB bcm qibzzimo VVY xwhxpbzzn dv gjcm kwxszb?";

        decrypted = cc3.decrypt(encrypted);
        assertEquals(encrypted, expected);
        assertEquals(decrypted, message2);
    }


    @Test
    void decrypt() {
        String expected = "Aran ajd Eiihy hwva avel earee gnean ewro";
        String message = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        CaesarCipherTwo cc2 = new CaesarCipherTwo(0, 19);

        String decrypted = cc2.decrypt(message);
        assertEquals(decrypted, expected);
        CaesarCipherTwo cc3 = new CaesarCipherTwo(14, 24);
        decrypted = cc3.decrypt(message3);
        expected = "The original name of Java was Oak.";
        assertEquals(decrypted, expected);

    }

}
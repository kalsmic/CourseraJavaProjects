package com.tests.src.ciphers;

import com.company.ciphers.CaesarCipher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaesarCipherTest {
    @Test
    void encrypt() {
        String  message = "FIRST LEGION ATTACK EAST FLANK!";
        String expected =  "CFOPQ IBDFLK XQQXZH BXPQ CIXKH!";
        CaesarCipher cc = new CaesarCipher(23);
        String encrypted = cc.encrypt(message);
        assertEquals(encrypted, expected);
        message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipher cc2 = new CaesarCipher(15);

        encrypted = cc2.encrypt(message);
        expected = "Rpc ndj xbpvxct axut LXIWDJI iwt xcitgcti PCS rdbejitgh xc ndjg edrzti?";
        assertEquals(encrypted, expected);


    }

    @Test
    void decrypt() {
        String  expected = "FIRST LEGION ATTACK EAST FLANK!";
        String message =  "CFOPQ IBDFLK XQQXZH BXPQ CIXKH!";
        CaesarCipher cc = new CaesarCipher(23);
        String decrypted = cc.decrypt(message);
        assertEquals(decrypted, expected);
    }


}
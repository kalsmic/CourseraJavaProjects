package main.company.ciphers;

public class CaesarCipherTwo {
    private final String alphabet;

    private final String shiftedAlphabet1;
    private final String shiftedAlphabet2;
    private final int key1;
    private final int key2;

    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        this.key1 = key1;
        this.key2 = key2;
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }

    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);

        for (int index = 0; index < sb.length(); index++) {
            char currentCharacter = sb.charAt(index);
            int alphabeticIndex = alphabet.indexOf(Character.toLowerCase(currentCharacter));


            if (alphabeticIndex != -1) {

                // if start is 0 use alphabet shifted with key1 else use key2
                String shiftedAlphabet = (index % 2 == 0) ? shiftedAlphabet1 : shiftedAlphabet2;

                // if character is lower case use lowercase shifted alphabet otherwise use uppercase shifted alphabet
                shiftedAlphabet = Character.isLowerCase(currentCharacter) ? shiftedAlphabet : shiftedAlphabet.toUpperCase();

                // get shifted characted
                currentCharacter = shiftedAlphabet.charAt(alphabeticIndex);

                // set shifted characted in string
                sb.setCharAt(index, currentCharacter);

            }

        }
        return sb.toString();

    }

    /**
     * This method returns a String that is the encrypted String decrypted using the
     * key associated with this CaesarCipher object.
     *
     * @param input
     * @return decrypted message
     */
    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - key1, 26 - key2);
        return cc.encrypt(input);
    }
}


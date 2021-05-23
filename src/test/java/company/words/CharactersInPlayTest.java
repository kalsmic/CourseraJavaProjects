package test.java.company.words;

import main.java.company.words.CharactersInPlay;
import edu.duke.FileResource;
import org.junit.jupiter.api.Test;

class CharactersInPlayTest {

    @Test
    void charactersWithNumParts() {
        FileResource resource = new FileResource("src/main/resources/data/likeit.txt");
        CharactersInPlay cip = new CharactersInPlay();
        cip.findAllCharacters(resource);
        cip.charactersWithNumParts(2, 3);
    }


    @Test
    void charactersWithNumParts2() {
        FileResource resource = new FileResource("src/main/resources/data/errors.txt");
        CharactersInPlay cip = new CharactersInPlay();
        cip.findAllCharacters(resource);
        cip.charactersWithNumParts(70, 300);
    }


    @Test
    void charactersWithNumParts3() {
        FileResource resource = new FileResource("src/main/resources/data/likeit.txt");
        CharactersInPlay cip = new CharactersInPlay();
        cip.findAllCharacters(resource);
        cip.charactersWithNumParts(10, 15);
    }
}
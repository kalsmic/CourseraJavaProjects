package main.tests.src.words;

import main.company.words.CharactersInPlay;
import edu.duke.FileResource;
import org.junit.jupiter.api.Test;

class CharactersInPlayTest {

    @Test
    void charactersWithNumParts() {
        FileResource resource = new FileResource("main/data/likeit.txt");
        CharactersInPlay cip = new CharactersInPlay();
        cip.findAllCharacters(resource);
        cip.charactersWithNumParts(2, 3);
    }


    @Test
    void charactersWithNumParts2() {
        FileResource resource = new FileResource("main/data/errors.txt");
        CharactersInPlay cip = new CharactersInPlay();
        cip.findAllCharacters(resource);
        cip.charactersWithNumParts(70, 300);
    }


    @Test
    void charactersWithNumParts3() {
        FileResource resource = new FileResource("main/data/likeit.txt");
        CharactersInPlay cip = new CharactersInPlay();
        cip.findAllCharacters(resource);
        cip.charactersWithNumParts(10, 15);
    }
}
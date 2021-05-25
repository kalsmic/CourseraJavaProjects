package test.java.company.quakes;

import main.java.company.quakes.Filter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FilterTest
{

    @Test
    void filterClassIsInterface()
    {
        assertTrue( Filter.class.isInterface() );
    }
}
package test.java.company.quakes;

import main.java.company.quakes.EarthQuakeParser;
import main.java.company.quakes.LargestQuakes;
import main.java.company.quakes.QuakeEntry;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
class LargestQuakesTest
{
    private EarthQuakeParser earthQuakeParser;
    private LargestQuakes largestQuakes;
    private ArrayList<QuakeEntry> quakeData;

    @BeforeAll
    void setUp()
    {
        earthQuakeParser = new EarthQuakeParser();
        quakeData = earthQuakeParser.read( "src/main/resources/data/nov20quakedatasmall.atom" );
        largestQuakes = new LargestQuakes();
    }

    @AfterAll
    void tearDown()
    {
        largestQuakes = null;
        quakeData = null;
        earthQuakeParser = null;
    }


    @Test
    @DisplayName( "Finds the index of the largest Quakes" )
    void indexOfLargest()
    {
        int maxIndex = largestQuakes.indexOfLargest( quakeData );
        assertEquals( 3, maxIndex );
        assertEquals( 5.50, quakeData.get( maxIndex ).getMagnitude() );
    }

    @Test
    @DisplayName( "Get largest QuakeEntries in descending order" )
    void getLargest()
    {
        int howMany = 5;
        ArrayList<QuakeEntry> largestQuakes = this.largestQuakes.getLargest( quakeData, howMany );
        assertEquals( 5.50, largestQuakes.get( 0 ).getMagnitude() );
        assertEquals( 5.10, largestQuakes.get( 1 ).getMagnitude() );
        assertEquals( 5.10, largestQuakes.get( 2 ).getMagnitude() );
        assertEquals( 5.00, largestQuakes.get( 3 ).getMagnitude() );
        assertEquals( 4.90, largestQuakes.get( 4 ).getMagnitude() );
    }


}
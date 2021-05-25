package test.java.company.quakes;

import main.java.company.quakes.EarthQuakeParser;
import main.java.company.quakes.LargestQuakes;
import main.java.company.quakes.QuakeEntry;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
class LargestQuakesTest
{
    private EarthQuakeParser earthQuakeParser;
    private LargestQuakes largestQuakes;
    private ArrayList<QuakeEntry> quakeDataSmall;
    private ArrayList<QuakeEntry> quakeDataBig;

    @BeforeAll
    void setUp()
    {
        earthQuakeParser = new EarthQuakeParser();
        quakeDataSmall = earthQuakeParser.read( "src/main/resources/data/nov20quakedatasmall.atom" );
        quakeDataBig = earthQuakeParser.read( "src/main/resources/data/nov20quakedata.atom" );
        largestQuakes = new LargestQuakes();
    }

    @AfterAll
    void tearDown()
    {
        largestQuakes = null;
        quakeDataSmall = null;
        quakeDataBig = null;
        earthQuakeParser = null;
    }


    @Test
    @DisplayName( "Finds the index of the largest Quakes" )
    void indexOfLargest()
    {
        int maxIndex = largestQuakes.indexOfLargest( quakeDataSmall );
        assertEquals( 3, maxIndex );
        assertEquals( 5.50, quakeDataSmall.get( maxIndex ).getMagnitude() );
        assertEquals( -1, largestQuakes.indexOfLargest( new ArrayList<>() ) );
    }

    @Test
    @DisplayName( "Get largest QuakeEntries in descending order -- small data" )
    void getLargest()
    {
        int howMany = 5;
        ArrayList<QuakeEntry> largestQuakesList = largestQuakes.getLargest( quakeDataSmall, howMany );
        assertEquals( 5.50, largestQuakesList.get( 0 ).getMagnitude() );
        assertEquals( 5.10, largestQuakesList.get( 1 ).getMagnitude() );
        assertEquals( 5.10, largestQuakesList.get( 2 ).getMagnitude() );
        assertEquals( 5.00, largestQuakesList.get( 3 ).getMagnitude() );
        assertEquals( 4.90, largestQuakesList.get( 4 ).getMagnitude() );

        // howMany > quakeDataSmall
        howMany = 90;
        largestQuakesList = largestQuakes.getLargest( quakeDataSmall, howMany );
        assertEquals( largestQuakesList.size(), largestQuakesList.size() );

    }

    @Test
    @DisplayName( "Get largest QuakeEntries in descending order--Big data" )
    void getLargest2()
    {
        int howMany = 100;
        ArrayList<QuakeEntry> largestQuakesList = largestQuakes.getLargest( quakeDataBig, howMany );

        assertEquals( 7.00, largestQuakesList.get( 0 ).getMagnitude() );
        assertEquals( 6.50, largestQuakesList.get( 1 ).getMagnitude() );
        assertEquals( 5.90, largestQuakesList.get( 2 ).getMagnitude() );
        assertEquals( 5.80, largestQuakesList.get( 3 ).getMagnitude() );
        assertEquals( 5.70, largestQuakesList.get( 4 ).getMagnitude() );
        assertEquals( "128km WSW of Kushikino, Japan", largestQuakesList.get( 4 ).getInfo() );
        assertEquals( 5.10, largestQuakesList.get( 19 ).getMagnitude() );
        assertEquals( "79km SSW of Fukue, Japan", largestQuakesList.get( 49 ).getInfo() );

    }


}
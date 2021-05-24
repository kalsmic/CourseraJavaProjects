package test.java.company.quakes;

import main.java.company.quakes.EarthQuakeClient;
import main.java.company.quakes.EarthQuakeParser;
import main.java.company.quakes.Location;
import main.java.company.quakes.QuakeEntry;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
class EarthQuakeClientTest
{
    private ArrayList<QuakeEntry> quakeDataSmall;
    private ArrayList<QuakeEntry> quakeDataBig;
    private EarthQuakeClient earthQuakeClient;
    private EarthQuakeParser earthQuakeParser;

    @BeforeAll
    void setUp()
    {
        earthQuakeParser = new EarthQuakeParser();
        quakeDataSmall = earthQuakeParser.read( "src/main/resources/data/nov20quakedatasmall.atom" );
        quakeDataBig = earthQuakeParser.read( "src/main/resources/data/nov20quakedata.atom" );
        earthQuakeClient = new EarthQuakeClient();

    }

    @AfterAll
    void tearDown()
    {
        earthQuakeClient = null;
        quakeDataSmall = null;
        quakeDataBig = null;
        earthQuakeParser = null;
    }

    @Test
    void filterByMagnitude()
    {
        double magMin = 5.0;
        ArrayList<QuakeEntry> quakesFilteredByMagnitude = earthQuakeClient.filterByMagnitude( quakeDataSmall, magMin );
        assertEquals( 3, quakesFilteredByMagnitude.size() );
        for ( QuakeEntry qe : quakesFilteredByMagnitude )
        {
            assertTrue( qe.getMagnitude() > magMin );
        }
    }

    @Test
    void filteredByDistanceFrom()
    {
        double distMax = 1000 * 1000;

        // Durham, NC (35.988, -78.907
        Location from = new Location( 35.988, -78.907 );
        ArrayList<QuakeEntry> quakesFilteredByDistanceFrom = earthQuakeClient.filterByDistance( quakeDataSmall, distMax,
                from );
        assertEquals( 0, quakesFilteredByDistanceFrom.size() );

        //  Bridgeport, CA (38.17, -118.82)
        from = new Location( 38.17, -118.82 );
        ;

        quakesFilteredByDistanceFrom = earthQuakeClient.filterByDistance( quakeDataSmall, distMax, from );
        assertEquals( 7, quakesFilteredByDistanceFrom.size() );

    }

    @Test
    void filterByDepth()
    {
        double minDepth = -10000.0;
        double maxDepth = -5000.0;

        ArrayList<QuakeEntry> quakeFilterByDepth = earthQuakeClient.filterByDepth( quakeDataSmall, minDepth, maxDepth );
        assertEquals( 5, quakeFilterByDepth.size() );

    }

    @Test
    void filterByDepth2()
    {
        double minDepth = -10000.0;
        double maxDepth = -8000.0;

        ArrayList<QuakeEntry> quakeFilterByDepth = earthQuakeClient.filterByDepth( quakeDataBig, minDepth, maxDepth );
        assertEquals( 172, quakeFilterByDepth.size() );

    }

    @ParameterizedTest( name = "{index} => where ={0},phrase={1}, expected={2}" )
    @CsvSource( {"start,Explosion,2", "end, California, 4", "any, Can,3"} )
    void filterByPhrase( String where, String phrase, int expected )
    {
        quakeDataSmall = earthQuakeParser.read( "src/main/resources/data/nov20quakedata.atom" );
        ArrayList<QuakeEntry> quakesFilteredByPhrase = earthQuakeClient.filterByPhrase( quakeDataSmall, where, phrase );
        assertEquals( expected, quakesFilteredByPhrase.size() );
    }

    @ParameterizedTest( name = "{index} => where ={0},phrase={1}, expected={2}" )
    @CsvSource( {"start,Explosion,7", "end, California, 538", "any, Creek,9"} )
    void filterByPhrase2( String where, String phrase, int expected )
    {
        ArrayList<QuakeEntry> quakesFilteredByPhrase = earthQuakeClient.filterByPhrase( quakeDataBig, where, phrase );
        assertEquals( expected, quakesFilteredByPhrase.size() );
    }
}
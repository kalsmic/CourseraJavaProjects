package test.java.company.quakes;

import main.java.company.quakes.DepthFilter;
import main.java.company.quakes.DistanceFilter;
import main.java.company.quakes.EarthQuakeClient;
import main.java.company.quakes.EarthQuakeParser;
import main.java.company.quakes.Location;
import main.java.company.quakes.MagMaxFilter;
import main.java.company.quakes.MagnitudeFilter;
import main.java.company.quakes.MatchAllFilter;
import main.java.company.quakes.PhraseFilter;
import main.java.company.quakes.QuakeEntry;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class MatchAllFilterTest
{
    private QuakeEntry qe;
    private ArrayList<QuakeEntry> quakeDataSmall;
    private ArrayList<QuakeEntry> quakeDataBig;
    private EarthQuakeParser earthQuakeParser;
    private EarthQuakeClient earthQuakeClient;

    @BeforeAll
    void setUp()
    {
        qe = new QuakeEntry( 8.53, -71.34, 5.50, "5km ENE of Lagunillas, Venezuela", -25160.00 );
        earthQuakeParser = new EarthQuakeParser();
        quakeDataSmall = earthQuakeParser.read( "src/main/resources/data/nov20quakedatasmall.atom" );
        quakeDataBig = earthQuakeParser.read( "src/main/resources/data/nov20quakedata.atom" );
        earthQuakeClient = new EarthQuakeClient();

    }

    @AfterAll
    void tearDown()
    {
        qe = null;
        quakeDataSmall = null;
        quakeDataBig = null;
        earthQuakeParser = null;
    }

    private void checkRange( double value, double minValue, double maxValue )
    {
        assertFalse( value < minValue );
        assertTrue( value >= minValue );
        assertFalse( value > maxValue );
        assertTrue( value <= maxValue );
    }

    @Test
    void satisfies()
    {
        MatchAllFilter matchAllFilter = new MatchAllFilter();
        matchAllFilter.addFilter( new MagMaxFilter( 10 ) );
        matchAllFilter.addFilter( new PhraseFilter( "end", "Venezuela" ) );
        matchAllFilter.addFilter( new MagnitudeFilter( 4.0, 6.0 ) );
        assertTrue( matchAllFilter.satisfies( qe ) );

        matchAllFilter.addFilter( new DepthFilter( -7000.00, -5000.00 ) );
        assertFalse( matchAllFilter.satisfies( qe ) );
    }

    @Test
    void testMultipleFilters1()
    {
        double magMin = 0.0;
        double magMax = 2.0;
        double minDepth = -100000.0;
        double maxDepth = -10000.0;
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter( new MagnitudeFilter( magMin, magMax ) );
        maf.addFilter( new DepthFilter( minDepth, maxDepth ) );
        maf.addFilter( new PhraseFilter( "any", "a" ) );
        ArrayList<String> titles = new ArrayList<>(
                List.of( "2km SE of Anza, California", "75km WSW of Cantwell, Alaska" ) );

        ArrayList<QuakeEntry> quakes = earthQuakeClient.filter( quakeDataSmall, maf );
        assertEquals( 2, quakes.size() );
        for ( int idx = 0; idx < quakes.size(); idx++ )
        {
            assertEquals( titles.get( idx ), quakes.get( idx ).getInfo() );
            checkRange( quakes.get( idx ).getDepth(), minDepth, maxDepth );
            checkRange( quakes.get( idx ).getMagnitude(), magMin, magMax );
        }
    }

    @Test
    void testMultipleFilters2()
    {
        double magMin = 0.0;
        double magMax = 3.0;
        double distance = 10000000;
        String phrase = "Ca";

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter( new MagnitudeFilter( magMin, magMax ) );

        Location location = new Location( 36.1314, -95.9372 );
        maf.addFilter( new DistanceFilter( location, distance ) );
        maf.addFilter( new PhraseFilter( "any", phrase ) );

        ArrayList<QuakeEntry> quakes = earthQuakeClient.filter( quakeDataSmall, maf );
        assertEquals( 7, quakes.size() );
        ArrayList<String> titles = new ArrayList<>( List.of( "2km SE of Anza, California", "66km E of Cantwell, Alaska",
                "28km SSE of Carmel Valley Village, California", "75km WSW of Cantwell, Alaska",
                "Quarry Blast - 7km SSW of Mojave, California", "Explosion - 8km SSE of Princeton, Canada",
                "Quarry Blast - 4km WNW of Grand Terrace, California" ) );

        for ( int idx = 0; idx < quakes.size(); idx++ )
        {
            assertEquals( titles.get( idx ), quakes.get( idx ).getInfo() );
            checkRange( quakes.get( idx ).getMagnitude(), magMin, magMax );
            assertTrue( quakes.get( idx ).getLocation().distanceTo( location ) < distance );
            assertTrue( quakes.get( idx ).getInfo().contains( phrase ) );
        }
    }

    @Test
    void testMultipleFiltersBiGData1()
    {
        double distance = 10000000;
        String phrase = "Japan";
        Location location = new Location( 35.42, 139.43 );

        MatchAllFilter matchAllFilter = new MatchAllFilter();
        matchAllFilter.addFilter( new DistanceFilter( location, distance ) );
        matchAllFilter.addFilter( new PhraseFilter( "end", phrase ) );

        ArrayList<QuakeEntry> quakes = earthQuakeClient.filter( quakeDataBig, matchAllFilter );
        assertEquals( 20, quakes.size() );
        for ( int idx = 0; idx < quakes.size(); idx++ )
        {
            assertTrue( quakes.get( idx ).getLocation().distanceTo( location ) < distance );
            assertTrue( quakes.get( idx ).getInfo().endsWith( phrase ) );
        }
    }

    @Test
    void testMultipleFiltersBiGData2()
    {
        double magMin = 4.0;
        double magMax = 5.0;
        double minDepth = -35000.0;
        double maxDepth = -12000.0;
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter( new MagnitudeFilter( magMin, magMax ) );
        maf.addFilter( new DepthFilter( minDepth, maxDepth ) );
        ArrayList<QuakeEntry> quakes = earthQuakeClient.filter( quakeDataBig, maf );

        assertEquals( 24, quakes.size() );
        for ( int idx = 0; idx < quakes.size(); idx++ )
        {
            checkRange( quakes.get( idx ).getDepth(), minDepth, maxDepth );
            checkRange( quakes.get( idx ).getMagnitude(), magMin, magMax );
        }
    }


    @Test
    void testMultipleFilterBiGData3()
    {
        double magMin = 0.0;
        double magMax = 2.0;
        double minDepth = -100000.0;
        double maxDepth = -10000.0;
        String phrase = "a";
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter( new MagnitudeFilter( magMin, magMax ) );
        maf.addFilter( new DepthFilter( minDepth, maxDepth ) );
        maf.addFilter( new PhraseFilter( "any", phrase ) );
        ArrayList<QuakeEntry> quakes = earthQuakeClient.filter( quakeDataBig, maf );

        assertEquals( 358, quakes.size() );
        for ( int idx = 0; idx < quakes.size(); idx++ )
        {
            checkRange( quakes.get( idx ).getDepth(), minDepth, maxDepth );
            checkRange( quakes.get( idx ).getMagnitude(), magMin, magMax );
            assertTrue( quakes.get( idx ).getInfo().contains( phrase ) );
        }
    }

    @Test
    void testMultipleFilterBiGData4()
    {
        double magMin = 0.0;
        double magMax = 3.0;
        double distance = 10000;
        String phrase = "Ca";

        Location location = new Location( 36.1314, -95.9372 );

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter( new MagnitudeFilter( magMin, magMax ) );
        maf.addFilter( new DistanceFilter( location, distance ) );
        maf.addFilter( new PhraseFilter( "any", phrase ) );
        ArrayList<QuakeEntry> quakes = earthQuakeClient.filter( quakeDataBig, maf );
        assertEquals( 616, quakes.size() );
        for ( int idx = 0; idx < quakes.size(); idx++ )
        {
            assertTrue( quakes.get( idx ).getLocation().distanceTo( location ) < distance * 1000 );
            checkRange( quakes.get( idx ).getMagnitude(), magMin, magMax );
            assertTrue( quakes.get( idx ).getInfo().contains( phrase ) );
        }
    }


}

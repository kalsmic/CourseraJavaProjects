package test.java.company.quakes;

import main.java.company.quakes.DepthFilter;
import main.java.company.quakes.DistanceFilter;
import main.java.company.quakes.EarthQuakeClient;
import main.java.company.quakes.EarthQuakeParser;
import main.java.company.quakes.Location;
import main.java.company.quakes.MagnitudeFilter;
import main.java.company.quakes.MatchAllFilter;
import main.java.company.quakes.PhraseFilter;
import main.java.company.quakes.QuakeEntry;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class MatchAllFilterTest
{
    private QuakeEntry qe;
    private ArrayList<QuakeEntry> quakeDataSmall;
    private ArrayList<QuakeEntry> quakeDataBig;
    private EarthQuakeParser earthQuakeParser;
    private EarthQuakeClient earthQuakeClient;
    private HashMap<String, ArrayList<QuakeEntry>> sampleData;

    @BeforeAll
    void setUp()
    {
        qe = new QuakeEntry( 8.53, -71.34, 5.50, "5km ENE of Lagunillas, Venezuela", -25160.00 );
        earthQuakeParser = new EarthQuakeParser();
        quakeDataSmall = earthQuakeParser.read( "src/main/resources/data/nov20quakedatasmall.atom" );
        quakeDataBig = earthQuakeParser.read( "src/main/resources/data/nov20quakedata.atom" );
        earthQuakeClient = new EarthQuakeClient();
        sampleData = new HashMap<>();
        sampleData.put( "small", quakeDataSmall );
        sampleData.put( "big", quakeDataBig );

    }

    @AfterAll
    void tearDown()
    {
        qe = null;
        quakeDataSmall = null;
        quakeDataBig = null;
        earthQuakeParser = null;
        sampleData = null;
    }

    private void checkRange( double value, double minValue, double maxValue )
    {
        assertFalse( value < minValue );
        assertTrue( value >= minValue );
        assertFalse( value > maxValue );
        assertTrue( value <= maxValue );
    }

    private void checkPhrase( String title, String where, String phrase )
    {
        switch ( where )
        {
            case "start":
                assertTrue( title.startsWith( phrase ) );
                break;
            case "end":
                assertTrue( title.endsWith( phrase ) );
                break;
            case "any":
                assertTrue( title.contains( phrase ) );
                break;
        }
    }

    @ParameterizedTest( name = "{index} => latitude={0}, longitude={1}, distance={2}, where={3},phrase={4},num={5},sample={6}" )
    @CsvSource( {
                        "35.42, 139.43,10000,Japan,end,2,small",
                        "35.42, 139.43,10000000,Japan,end,20,big",
                        "39.7392,-104.9903,1000,a,end,74,big"} )
    @DisplayName( "Filters used are: Distance Phrase" )
    void testMultipleFiltersDistancePhrase( double latitude, double longitude, double distance, String phrase, String where, int num, String sample )
    {
        Location location = new Location( latitude, longitude );

        MatchAllFilter matchAllFilter = new MatchAllFilter();
        matchAllFilter.addFilter( new DistanceFilter( location, distance ) );
        matchAllFilter.addFilter( new PhraseFilter( where, phrase ) );

        ArrayList<QuakeEntry> quakes = earthQuakeClient.filter( sampleData.get( sample ), matchAllFilter );
        assertEquals( num, quakes.size() );
        for ( QuakeEntry quake : quakes )
        {
            assertTrue( quake.getLocation().distanceTo( location ) < distance * 1000 );
            checkPhrase( quake.getInfo(), where, phrase );

        }
    }

    @ParameterizedTest( name = "{index} => magMin={0}, magMax={1},minDepth={2}, maxDepth={3},num={4},sample={5}" )
    @CsvSource( {"4.0,5.0,-35000.0,-12000.0,24,big", "3.5,4.5,-55000.0,-20000.0,15,big", "0.0,2.0,-100000.0,-10000.0,2,small"} )
    @DisplayName( "Filters used are: Magnitude Depth" )
    void testMultipleFiltersMagnitudeDepth( double magMin, double magMax, double minDepth, double maxDepth, int num, String sample )
    {
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter( new MagnitudeFilter( magMin, magMax ) );
        maf.addFilter( new DepthFilter( minDepth, maxDepth ) );
        ArrayList<QuakeEntry> quakes = earthQuakeClient.filter( sampleData.get( sample ), maf );

        assertEquals( num, quakes.size() );
        for ( QuakeEntry quake : quakes )
        {
            checkRange( quake.getDepth(), minDepth, maxDepth );
            checkRange( quake.getMagnitude(), magMin, magMax );
        }
    }


    @ParameterizedTest( name = "{index} => magMin={0}, magMax={1},minDepth={2}, maxDepth={3}, where={4}phrase={5},num={6},sample={7}" )
    @CsvSource( {"0.0,2.0,-100000.0,-10000.0,a,any,358,big", "1.0,4.0,-180000.0,-30000.0,o,any,187,big"} )
    @DisplayName( "Filters used are: Magnitude Depth Phrase" )
    void testMultipleFilterDistancePhrase( double magMin, double magMax, double minDepth, double maxDepth, String phrase, String where, int num, String sample )
    {
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter( new MagnitudeFilter( magMin, magMax ) );
        maf.addFilter( new DepthFilter( minDepth, maxDepth ) );
        maf.addFilter( new PhraseFilter( where, phrase ) );
        ArrayList<QuakeEntry> quakes = earthQuakeClient.filter( sampleData.get( sample ), maf );

        assertEquals( num, quakes.size() );
        for ( QuakeEntry quake : quakes )
        {
            checkRange( quake.getDepth(), minDepth, maxDepth );
            checkRange( quake.getMagnitude(), magMin, magMax );
            checkPhrase( quake.getInfo(), where, phrase );
        }
    }

    @ParameterizedTest( name = "{index} => magMin={0}, magMax={1},latitude={2}, longitude={3}, distance={4}, where={5},phrase={6},num={7},sample={8}" )
    @CsvSource( {
                        "0.0,3.0,36.1314, -95.9372,10000,Ca,any,7,small",
                        "0.0,3.0,36.1314,-95.9372,10000,Ca,any,616,big", "0.0,5.0,55.7308, 9.1153,3000,e,any,17,big"} )
    @DisplayName( "Filters used are: Magnitude Distance Phrase" )
    void testMultipleFilterMagnitudeDistancePhrase( double magMin, double magMax, double latitude, double longitude, double distance, String phrase, String where, int num, String sample )
    {

        Location location = new Location( latitude, longitude );

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter( new MagnitudeFilter( magMin, magMax ) );
        maf.addFilter( new DistanceFilter( location, distance ) );
        maf.addFilter( new PhraseFilter( where, phrase ) );
        ArrayList<QuakeEntry> quakes = earthQuakeClient.filter( sampleData.get( sample ), maf );
        assertEquals( num, quakes.size() );
        for ( QuakeEntry quake : quakes )
        {
            assertTrue( quake.getLocation().distanceTo( location ) < distance * 1000 );
            checkRange( quake.getMagnitude(), magMin, magMax );
            checkPhrase( quake.getInfo(), where, phrase );

        }
    }
}

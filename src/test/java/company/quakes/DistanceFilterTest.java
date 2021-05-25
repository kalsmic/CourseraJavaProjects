package test.java.company.quakes;

import main.java.company.quakes.DistanceFilter;
import main.java.company.quakes.Filter;
import main.java.company.quakes.Location;
import main.java.company.quakes.QuakeEntry;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class DistanceFilterTest
{
    private QuakeEntry qe;

    @BeforeAll
    void setUp()
    {
        qe = new QuakeEntry( 38.27, 142.53, 4.60, "109km E of Ishinomaki, Japan", -30500.00 );
    }

    @AfterAll
    void tearDown()
    {
        qe = null;
    }

    @ParameterizedTest( name = "{index} => latitude={0},longitude={1},distance={2},expected={3}" )
    @CsvSource( {"35.988,-78.907,1000,false", "38.17,-118.82,10000,true"} )
    void satisfies( double latitude, double longitude, double distance, boolean expected )
    {
        Location location = new Location( latitude, longitude );
        Filter f = new DistanceFilter( location, distance );
        assertEquals( expected, f.satisfies( qe ) );
    }
}

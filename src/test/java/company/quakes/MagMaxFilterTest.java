package test.java.company.quakes;

import main.java.company.quakes.Filter;
import main.java.company.quakes.MagMaxFilter;
import main.java.company.quakes.QuakeEntry;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class MagMaxFilterTest
{
    private QuakeEntry qe;

    @BeforeAll
    void setUp()
    {
        qe = new QuakeEntry( 8.53, -71.34, 5.50, "5km ENE of Lagunillas, Venezuela", -25160.00 );
    }

    @AfterAll
    void tearDown()
    {
        qe = null;
    }

    @ParameterizedTest( name = "{index} => magMin={0}, expected={1}" )
    @CsvSource( {"4.0, false", "10,true"} )
    void satisfies( double magMax, boolean expected )
    {
        Filter f = new MagMaxFilter( magMax );
        assertEquals( expected, f.satisfies( qe ) );
    }


}

package test.java.company.quakes;

import main.java.company.quakes.Filter;
import main.java.company.quakes.MagnitudeFilter;
import main.java.company.quakes.QuakeEntry;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class MagnitudeFilterTest
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

    @Test
    void magnitudeFilterReturnsTrue()
    {
        double magMin = 4.0;
        double magMax = 6.0;
        Filter f = new MagnitudeFilter( magMin, magMax );
        assertTrue( f.satisfies( qe ) );
    }

    @Test
    void magnitudeFilterReturnsFalse()
    {
        double magMin = 3.0;
        double magMax = 4.0;

        Filter f = new MagnitudeFilter( magMin, magMax );
        assertFalse( f.satisfies( qe ) );
    }
}

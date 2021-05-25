package test.java.company.quakes;

import main.java.company.quakes.DepthFilter;
import main.java.company.quakes.Filter;
import main.java.company.quakes.QuakeEntry;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class DepthFilterTest
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
    void satisfiesReturnsTrue()
    {
        double maxDepth = -6000;
        double minDepth = -25160.00;
        Filter f = new DepthFilter( minDepth, maxDepth );
        assertTrue( f.satisfies( qe ) );
    }

    @Test
    void satisfiesReturnsFalse()
    {
        double maxDepth = -7000.00;
        double minDepth = -5000.00;
        Filter f = new DepthFilter( minDepth, maxDepth );
        assertFalse( f.satisfies( qe ) );
    }
}

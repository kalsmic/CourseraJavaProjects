package test.java.company.quakes;

import main.java.company.quakes.DepthFilter;
import main.java.company.quakes.MagMaxFilter;
import main.java.company.quakes.MagnitudeFilter;
import main.java.company.quakes.MatchAllFilter;
import main.java.company.quakes.PhraseFilter;
import main.java.company.quakes.QuakeEntry;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class MatchAllFilterTest
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

}

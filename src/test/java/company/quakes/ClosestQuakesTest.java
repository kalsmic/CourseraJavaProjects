package test.java.company.quakes;

import main.java.company.quakes.ClosestQuakes;
import main.java.company.quakes.EarthQuakeParser;
import main.java.company.quakes.Location;
import main.java.company.quakes.QuakeEntry;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
class ClosestQuakesTest
{
    ArrayList<QuakeEntry> quakeData;
    private ClosestQuakes closestQuakes;

    @BeforeAll
    void setUp()
    {
        EarthQuakeParser eqp = new EarthQuakeParser();
        quakeData = eqp.read( "src/main/resources/data/nov20quakedatasmall.atom" );
        closestQuakes = new ClosestQuakes();
    }

    @AfterAll
    void tearDown()
    {
        closestQuakes = null;
        quakeData = null;
    }

    @Test
    void getClosest()
    {
        //        Jakarta (-6.211,106.845)
        Location current = new Location( -6.211, 106.845 );
        int howMany = 3;
        ArrayList<QuakeEntry> closest = closestQuakes.getClosest( quakeData, current, howMany );
        assertEquals( 3, closest.size() );
        assertEquals( "15km NNW of Kota Ternate, Indonesia", closest.get( 0 ).getInfo() );
        assertEquals( "55km S of Pondaguitan, Philippines", closest.get( 1 ).getInfo() );
        assertEquals( "91km SSE of Chichi-shima, Japan", closest.get( 2 ).getInfo() );

        // empty quake data
        closest = closestQuakes.getClosest( new ArrayList<QuakeEntry>(), current, howMany );
        assertTrue( closest.isEmpty() );
    }

}
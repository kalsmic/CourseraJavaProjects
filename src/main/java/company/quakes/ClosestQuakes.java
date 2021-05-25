package main.java.company.quakes;


import java.util.ArrayList;

public class ClosestQuakes
{
    /**
     * This method finds the closest number of howMany earthquakes to the current Location. The earthquakes in the
     * returned ArrayList are in order with the closest earthquake in index position 0.  If there are fewer then howMany
     * earthquakes in quakeData, then the ArrayList returned is the same size as quakeData.
     *
     * @param quakeData an ArrayList of type QuakeEntry
     * @param current   a Location Object
     * @param howMany   an int, the number of close location to return
     * @return an ArrayList of type QuakeEntry
     */
    public ArrayList<QuakeEntry> getClosest( ArrayList<QuakeEntry> quakeData, Location current, int howMany )
    {
        if ( quakeData.isEmpty() )
        {
            return quakeData;
        }
        // make a copy of quakeData
        ArrayList<QuakeEntry> copy = new ArrayList<>( quakeData );
        // create a container in which to rearrange the QuakeEntries
        ArrayList<QuakeEntry> closestQuakes = new ArrayList<>();

        // look through the copied quake data ArrayList times howMany
        for ( int times = 0; times < howMany; times++ )
        {
            // set index of the closest quake to the first QuakeEntry
            int minIndex = 0;

            // each time look for the closest location.
            for ( int i = 1; i < copy.size(); i++ )
            {
                // get current location
                Location currLocation = copy.get( i ).getLocation();

                // compare the distance of currLocation from current with
                // distance of closestQuake from current
                if ( currLocation.distanceTo( current ) < copy.get( minIndex ).getLocation().distanceTo( current ) )
                {
                    // set the minIndex to current index
                    minIndex = i;
                }

            }
            // remove the closest from the copy ArrayList and add it to closestQuakes
            closestQuakes.add( copy.get( minIndex ) );
            // remove it from copy of to avoid duplication
            copy.remove( minIndex );

        }
        return closestQuakes;
    }


}
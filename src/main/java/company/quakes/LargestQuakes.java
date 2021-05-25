package main.java.company.quakes;


import java.util.ArrayList;

public class LargestQuakes
{
    public int indexOfLargest( ArrayList<QuakeEntry> quakeData )
    {
        if ( quakeData.isEmpty() )
        {
            return -1;
        }

        int maxIndex = 0;

        // look at each QuakeEntry beginning from 1
        for ( int i = 1; i < quakeData.size(); i++ )
        {
            // check if magnitude of current QuakeEntry is bigger than the QuakeEntry at index maxIndex
            if ( quakeData.get( i ).getMagnitude() > quakeData.get( maxIndex ).getMagnitude() )
            {
                // if so, set maxIndex to current index of i
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public ArrayList<QuakeEntry> getLargest( ArrayList<QuakeEntry> quakeData, int howMany )
    {
        // make a copy of quakeData
        ArrayList<QuakeEntry> copy = new ArrayList<>( quakeData );

        // create a container to store Largest earthquakes
        ArrayList<QuakeEntry> largestQuakes = new ArrayList<>();

        // look through the QuakeData times howMany
        for ( int i = 0; i < howMany; i++ )
        {
            // find the index of the largest quake
            int largestQuakeIndex = indexOfLargest( copy );

            // stop if quakes are finished before running all the howMany times
            if ( largestQuakeIndex == -1 )
            {
                break;
            }
            //  add it to largestQuakes
            largestQuakes.add( copy.get( largestQuakeIndex ) );

            // remove it from the quakeData copy to avoid duplication
            copy.remove( largestQuakeIndex );

        }
        return largestQuakes;


    }

}

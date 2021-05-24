package main.java.company.quakes;

import java.util.ArrayList;

public class EarthQuakeClient
{

    public void dumpCSV( ArrayList<QuakeEntry> list )
    {
        System.out.println( "Latitude,Longitude,Magnitude,Info" );
        for ( QuakeEntry qe : list )
        {
            System.out.printf( "%4.2f,%4.2f,%4.2f,%s\n", qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(), qe.getMagnitude(), qe.getInfo() );
        }

    }

    public void createCSV()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/main/resources/data/nov20quakedata.atom";
        //        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read( source );
        dumpCSV( list );
        System.out.println( "# quakes read: " + list.size() );
    }

//    public void closeToMe()
//    {
//        EarthQuakeParser parser = new EarthQuakeParser();
//        String source = "src/main/resources/data/nov20quakedatasmall.atom";
//
//        //        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
//        ArrayList<QuakeEntry> list = parser.read( source );
//        System.out.println( "# quakes read: " + list.size() );
//
//        //Durham, NC
////        Location city = new Location(35.988, -78.907);
//        //Bridgeport, CA
//        Location city = new Location( 38.17, -118.82 );
//        ArrayList<QuakeEntry> close = filterByDistanceFrom( list, 1000 * 1000, city );
//        for ( int k = 0; k < close.size(); k++ )
//        {
//            QuakeEntry entry = close.get( k );
//            double distanceInMeters = city.distanceTo( entry.getLocation() );
//            System.out.println( distanceInMeters / 1000 + " " + entry.getInfo() );
//        }
//
//    }

    /**
     *
     * @param quakeData  an ArrayList of type QuakeEntry
     * @param magMin a double represeting the minimum magnitude of the earthquake
     * @return an ArrayList of type QuakeEntry of all the earthquakes from quakeData
     *          that have a magnitude larger than magMin
     */
    public ArrayList<QuakeEntry> filterByMagnitude( ArrayList<QuakeEntry> quakeData, double magMin )
    {
        // create a container to store the filtered QuakeEntries
        ArrayList<QuakeEntry> quakesFilteredByMagnitude = new ArrayList<>();

        // look at each QuakeEntry
        for(QuakeEntry qe : quakeData){
            // check if current magnitude is greater than magMin
            if(qe.getMagnitude() > magMin){
                // if so, add it to quakesFilteredByMagnitude
                quakesFilteredByMagnitude.add( qe );
            }
        }
        return quakesFilteredByMagnitude;
    }

    /**
     *
     * @param quakeData  an ArrayList of type QuakeEntry
     * @param distMax a double representing the maximum distance
     * @param from  is a Location object
     * @return an ArrayList of type QuakeEntry of all the earthquakes from quakeData that are less than
     *         distMax from the location from
     */
    public ArrayList<QuakeEntry> filterByDistance( ArrayList<QuakeEntry> quakeData, double distMax, Location from )
    {
        // create a container to store the filtered QuakeEntries
        ArrayList<QuakeEntry> quakesFilteredByDistance = new ArrayList<>();

        // look at each QuakeEntry
        for(QuakeEntry qe : quakeData){

            // get current Location
            Location currLocation = qe.getLocation();
            // check if distance from to currLocation is less than distMax
            if(currLocation.distanceTo( from ) < distMax){
                // if so, add it to quakesFilteredByDistance
                quakesFilteredByDistance.add( qe );
            }
        }
        return quakesFilteredByDistance;
    }

    /**
     *
     * @param quakeData an ArrayList of type QuakeEntry
     * @param minDepth a double representing the minimum depth of earthquake in ocean
     * @param maxDepth a double representing the maximum depth of earthquake in the ocean
     * @return an ArrayList of type QuakeEntry of all the earthquakes from quakeData whose depth is between
     *        minDepth and maxDepth, exclusive
     */
    public ArrayList<QuakeEntry> filterByDepth( ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth )
    {   // create an empty array list to store the QuakeEntries that have depth between minDepth and maxDepth
        ArrayList<QuakeEntry> quakesFilteredByDepth = new ArrayList<>();
        // look at each QuakeEntry
        for(QuakeEntry qe : quakeData){
            // store the current depth in currDepth
            double currDepth = qe.getDepth();
            // check if currDepth is between minDepth and maxDepth
            if(currDepth > minDepth && currDepth < maxDepth){
                // if so, add it to the quakesFilteredByDepth ArrayList
                quakesFilteredByDepth.add( qe );
            }
        }
        return quakesFilteredByDepth;
    }

    /**
     *
     * @param quakeData an ArrayList of type QuakeEntry
     * @param where indicates where to search in the title and has one of three values: (“start”, ”end”, or “any”)
     * @param phrase indicates the phrase to search for in the title of the earthquake
     * @return an ArrayList of type QuakeEntry of all the earthquakes from quakeData whose titles have the given phrase
     *          found at location where (“start” means the phrase must start the title, “end” means the phrase must end
     *          the title and “any” means the phrase is a substring anywhere in the title.)
     */
    public ArrayList<QuakeEntry> filterByPhrase( ArrayList<QuakeEntry> quakeData, String where, String phrase )
    {
        ArrayList<QuakeEntry> quakesFilteredByPhrase = new ArrayList<>();

        // look at each QuakeEntry
        for(QuakeEntry qe : quakeData){
            // get current info
            String currInfo = qe.getInfo();
            // set found to false
            boolean found = false;

            switch ( where )
            {
                case "start":
                    found = currInfo.startsWith( phrase );
                    break;
                case "end":
                    found = currInfo.endsWith( phrase );
                    break;
                case "any":
                    found = currInfo.contains( phrase );
                    break;
            }

            if(found)
            {
                quakesFilteredByPhrase.add( qe );
            }


        }
        return quakesFilteredByPhrase;

    }

}

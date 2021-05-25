package main.java.company.quakes;

public class DistanceFilter implements Filter
{
    private final Location location;
    private final double distance;

    /**
     * @param location a Location object
     * @param distance is the maximum distance
     */
    public DistanceFilter( Location location, double distance )
    {
        this.location = location;
        this.distance = distance;
    }

    /**
     * @param qe is a QuakeEntry object
     * @return true if a QuakeEntry’s distance from the given location is less than the specified maximum distance.
     * Otherwise it returns false
     */
    @Override
    public boolean satisfies( QuakeEntry qe )
    {
        return qe.getLocation().distanceTo( location ) < distance;
    }
}

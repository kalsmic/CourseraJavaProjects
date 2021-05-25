package main.java.company.quakes;

public class MagnitudeFilter implements Filter
{
    private final MagMinFilter magMinFilter;
    private final MagMaxFilter magMaxFilter;

    /**
     * @param magMin is the minimum magnitude of the earthquake
     * @param magMax is the maximum magnitude of the earthquake
     */
    public MagnitudeFilter( double magMin, double magMax )
    {
        magMinFilter = new MagMinFilter( magMin );
        magMaxFilter = new MagMaxFilter( magMax );
    }

    /**
     * @param qe is a QuakeEntry object
     * @return returns true if a QuakeEntry’s magnitude is between the minimum and maximum magnitudes, or equal to one
     * of them. Otherwise it returns false.
     */
    @Override
    public boolean satisfies( QuakeEntry qe )
    {
        return ( magMinFilter.satisfies( qe ) && magMaxFilter.satisfies( qe ) );
    }

}

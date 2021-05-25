package main.java.company.quakes;

public class MagMinFilter implements Filter
{
    private final double magMin;

    /**
     * @param magMin is the minimum magnitude of earthquakes to consider for filtering
     */
    public MagMinFilter( double magMin )
    {
        this.magMin = magMin;
    }

    /**
     * @param qe is a QuakeEntry
     * @return true if its QuakeEntry parameter qe has a magnitude greater than or equal to magMin. Otherwise it returns
     * false
     */
    @Override
    public boolean satisfies( QuakeEntry qe )
    {
        return qe.getMagnitude() >= magMin;
    }
}

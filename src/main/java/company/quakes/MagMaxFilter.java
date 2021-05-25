package main.java.company.quakes;

public class MagMaxFilter implements Filter
{
    private static final String name = "MagMax";
    private final double magMax;

    /**
     * @param magMax is the maximum magnitude of earthquakes to consider for filtering
     */
    public MagMaxFilter( double magMax )
    {
        this.magMax = magMax;
    }

    /**
     * @param qe is a QuakeEntry
     * @return true if its QuakeEntry parameter qe has a magnitude less than or equal to magMax. Otherwise it returns
     * false
     */
    @Override
    public boolean satisfies( QuakeEntry qe )
    {
        return qe.getMagnitude() <= magMax;
    }

    @Override
    public String getName()
    {
        return MagMaxFilter.name;
    }
}

package main.java.company.quakes;

public class DepthFilter implements Filter
{
    private static final String name = "Depth";
    private final double minDepth;
    private final double maxDepth;

    /**
     * @param minDepth is the minimum earthquake depth
     * @param maxDepth is the maximum earthquake depth
     */
    public DepthFilter( double minDepth, double maxDepth )
    {
        this.minDepth = minDepth;
        this.maxDepth = maxDepth;
    }

    /**
     * @param qe is a QuakeEntry object
     * @return true if a QuakeEntry’s depth is between the minimum and maximum depths, or equal to one of them.
     * Otherwise it returns false
     */
    @Override
    public boolean satisfies( QuakeEntry qe )
    {
        return ( qe.getDepth() >= minDepth && qe.getDepth() <= maxDepth );
    }

    @Override
    public String getName()
    {
        return DepthFilter.name;
    }

}

package main.java.company.quakes;

import java.util.ArrayList;

public class MatchAllFilter implements Filter
{
    private final ArrayList<Filter> filters;

    public MatchAllFilter()
    {
        filters = new ArrayList<>();
    }

    /**
     * This method adds the filter to the private filters ArrayList
     *
     * @param filter is a Filter Object
     */
    public void addFilter( Filter filter )
    {
        this.filters.add( filter );
    }

    /**
     * @param qe is a QuakeEntry
     * @return true if the QuakeEntry satisfies all the filter conditions, otherwise it returns false.
     */
    public boolean satisfies( QuakeEntry qe )
    {
        for ( Filter filter : filters )
        {
            if ( !filter.satisfies( qe ) )
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getName()
    {
        StringBuilder filterNames = new StringBuilder( "Filters used are:" );
        for ( Filter filter : filters )
        {
            filterNames.append( " " );
            filterNames.append( filter.getName() );
        }
        return filterNames.toString();
    }
}

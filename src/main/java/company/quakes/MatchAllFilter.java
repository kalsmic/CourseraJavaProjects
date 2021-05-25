package main.java.company.quakes;

import java.util.ArrayList;

public class MatchAllFilter
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
        for ( Filter currFilter : filters )
        {
            if ( !currFilter.satisfies( qe ) )
            {
                return false;
            }
        }
        return true;
    }
}

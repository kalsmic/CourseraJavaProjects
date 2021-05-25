package main.java.company.quakes;

public class PhraseFilter implements Filter
{
    private final String phrase;
    private final String where;

    /**
     * @param where  is a String representing the type of request that indicates where to search in the title and has
     *               one of three values: (“start”, ”end”, or “any”)
     * @param phrase is a a String indicating the phrase to search for in the title of the earthquake
     */
    public PhraseFilter( String where, String phrase )
    {
        this.phrase = phrase.toLowerCase();
        this.where = where.toLowerCase();
    }

    /**
     * @param qe is a QuakeEntry object
     * @return true if the phrase is found at the requested location in the title. If the phrase is not found, this
     * method returns false.
     */
    @Override
    public boolean satisfies( QuakeEntry qe )
    {
        switch ( where )
        {
            case "start":
                return qe.getInfo().toLowerCase().startsWith( phrase );
            case "end":
                return qe.getInfo().toLowerCase().endsWith( phrase );
            case "any":
                return qe.getInfo().toLowerCase().contains( phrase );
            default:
                return false;
        }

    }
}

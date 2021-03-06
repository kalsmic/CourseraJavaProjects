package main.java.company.words;

import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.Random;

public class GladLib
{
    //    private static final String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static final String dataSourceDirectory = "main/data";
    private final Random myRandom;
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    private ArrayList<String> seenList;

    public GladLib()
    {
        initializeFromSource( dataSourceDirectory );
        myRandom = new Random();
    }

    public GladLib( String source )
    {
        initializeFromSource( source );
        myRandom = new Random();
    }

    private void initializeFromSource( String source )
    {
        adjectiveList = readIt( source + "/adjective.txt" );
        nounList = readIt( source + "/noun.txt" );
        colorList = readIt( source + "/color.txt" );
        countryList = readIt( source + "/country.txt" );
        nameList = readIt( source + "/name.txt" );
        animalList = readIt( source + "/animal.txt" );
        timeList = readIt( source + "/timeframe.txt" );
        fruitList = readIt( source + "/fruit.txt/" );
        verbList = readIt( source + "/verb.txt" );
        seenList = new ArrayList<>();
    }

    private String randomFrom( ArrayList<String> source )
    {
        int index = myRandom.nextInt( source.size() );
        return source.get( index );
    }

    private String getSubstitute( String label )
    {
        switch ( label )
        {
            case "country":
                return randomFrom( countryList );
            case "color":
                return randomFrom( colorList );
            case "noun":
                return randomFrom( nounList );
            case "name":
                return randomFrom( nameList );
            case "adjective":
                return randomFrom( adjectiveList );
            case "animal":
                return randomFrom( animalList );
            case "timeframe":
                return randomFrom( timeList );
            case "fruit":
                return randomFrom( fruitList );
            case "verb":
                return randomFrom( verbList );
            case "number":
                return "" + myRandom.nextInt( 50 ) + 5;
            default:
                return "**UNKNOWN**";
        }
    }

    private String processWord( String w )
    {
        int first = w.indexOf( "<" );
        int last = w.indexOf( ">", first );
        if ( first == -1 || last == -1 )
        {
            return w;
        }
        String prefix = w.substring( 0, first );
        String suffix = w.substring( last + 1 );
        String sub = getSubstitute( w.substring( first + 1, last ) );
        while ( true )
        {
            if ( !seenList.contains( sub ) )
            {
                seenList.add( sub );
                break;
            }
            sub = getSubstitute( w.substring( first + 1, last ) );
        }

        return prefix + sub + suffix;
    }

    private void printOut( String s, int lineWidth )
    {
        int charsWritten = 0;
        for ( String w : s.split( "\\s+" ) )
        {
            if ( charsWritten + w.length() > lineWidth )
            {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print( w + " " );
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate( String source )
    {
        StringBuilder story = new StringBuilder();
        if ( source.startsWith( "http" ) )
        {
            URLResource resource = new URLResource( source );
            for ( String word : resource.words() )
            {
                story.append( processWord( word ) ).append( " " );
            }
        }
        else
        {
            FileResource resource = new FileResource( source );
            for ( String word : resource.words() )
            {
                story.append( processWord( word ) ).append( " " );
            }
        }
        return story.toString();
    }

    private ArrayList<String> readIt( String source )
    {
        ArrayList<String> list = new ArrayList<>();
        if ( source.startsWith( "http" ) )
        {
            URLResource resource = new URLResource( source );
            for ( String line : resource.lines() )
            {
                list.add( line );
            }
        }
        else
        {
            FileResource resource = new FileResource( source );
            for ( String line : resource.lines() )
            {
                list.add( line );
            }
        }
        return list;
    }

    public void makeStory()
    {
        seenList.clear();
        System.out.println( "\n" );
        String story = fromTemplate( "src/main/resources/data/madtemplate3.txt" );
        printOut( story, 60 );
        System.out.println( "\n" );
        System.out.println( "seen words " + seenList.size() );

    }

}
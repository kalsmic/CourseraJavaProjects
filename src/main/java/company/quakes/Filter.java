package main.java.company.quakes;

public interface Filter
{
    boolean satisfies( QuakeEntry qe );

    String getName();
}

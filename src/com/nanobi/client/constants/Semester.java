package com.nanobi.client.constants;

public enum Semester
{
    I( 1 ), II( 2 ), III( 3 ), IV( 4 ), V( 5 ), VI( 6 ), VII( 7 ), VIII( 8 );

    private int val;


    Semester( int val )
    {
        this.setSemester( val );
    }


    public static Semester getSemester( String sem )
    {
        if ( sem.equals( "1" ) || sem.equals( "first" ) || sem.equals( "1st" ) || sem.equals( "one" ) ) {
            return I;
        } else if ( sem.equals( "2" ) || sem.equals( "second" ) || sem.equals( "2nd" ) || sem.equals( "two" ) ) {
            return II;
        } else if ( sem.equals( "3" ) || sem.equals( "third" ) || sem.equals( "3rd" ) || sem.equals( "three" ) ) {
            return III;
        } else if ( sem.equals( "4" ) || sem.equals( "fourth" ) || sem.equals( "4th" ) || sem.equals( "four" ) ) {
            return IV;
        } else if ( sem.equals( "5" ) || sem.equals( "fifth" ) || sem.equals( "5th" ) || sem.equals( "five" ) ) {
            return IV;
        } else if ( sem.equals( "6" ) || sem.equals( "sixth" ) || sem.equals( "6th" ) || sem.equals( "six" ) ) {
            return IV;
        } else if ( sem.equals( "7" ) || sem.equals( "seventh" ) || sem.equals( "7th" ) || sem.equals( "seven" ) ) {
            return IV;
        } else if ( sem.equals( "8" ) || sem.equals( "eighth" ) || sem.equals( "8th" ) || sem.equals( "eighth" ) || sem.equals( "last" )) {
            return IV;
        }
        return null; 
    }


    public int getSemester()
    {
        return val;
    }


    public void setSemester( int val )
    {
        this.val = val;
    }
}

/**
 * 
 */
package com.nanobi.client.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import com.nanobi.client.communication.TranslationResult;
import com.nanobi.client.constants.StudentDaoConstants;
import com.nanobi.client.model.Student;


/**
 * @author hduser
 *
 */
public class Utils
{
    public static ArrayList<String> constructMappingFromResult( TranslationResult result )
    {
        ArrayList<String> mappings = new ArrayList<String>();

        for ( String s : result.getDimensions().values() ) {
            mappings.add( s );
        }

        for ( String s : result.getMeasures().values() ) {
            mappings.add( s );
        }

        for ( String s : result.getFilters().values() ) {
            mappings.add( s );
        }

        for ( String s : result.getVisualizations().values() ) {
            mappings.add( s );
        }

        return mappings;
    }


    public static String formatStudent( Student s )
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "<tr>" );
        builder.append( tdColspan( strong( "Name: " ) + s.getName(), 2 ) );
        builder.append( tdColspan( strong( "USN: " ) + s.getUsn(), 2 ) );
        builder.append( tdColspan( strong( "Total: " ) + s.getTotal().toString(), 2 ) );
        builder.append( tdColspan( strong( "Percentage: " ) + s.getPercentage( StudentDaoConstants.MARKS_MAX ).toString(), 2 ) );
        builder.append( tdColspan( strong( "Class: " ) + s.getResultClass().toString(), 1 ) );
        builder.append( "</tr>" );
        builder.append( "\n<tr>" );
        ArrayList<String> allsubjects = new ArrayList<String>( s.getSubjectsWithSuffix( StudentDaoConstants.TOTAL_SUFFIX ) );
        Collections.sort( allsubjects );
        for ( String subj : allsubjects ) {
            builder.append( td( strong(subj) + s.getScore( subj ).toString() ) );
        }
        builder.append( "</tr>\n" );
        return builder.toString();
    }


    /*public static String getHeadersForTable(Student s) {
        StringBuilder builder = new StringBuilder();
        builder.append( "<tr>" );
        builder.append( th("Name") );
        builder.append( th("USN") );
        ArrayList<String> allsubjects  = new ArrayList<String>(s.getSubjectsWithSuffix( StudentDaoConstants.TOTAL_SUFFIX ));
        Collections.sort( allsubjects );
        for(String subj : allsubjects) {
            builder.append( th(subj) );
        }
        builder.append( th("Total") );
        builder.append( th("Percentage") );
        builder.append( th("Class") );
        builder.append( "</tr>" );
        return builder.toString();
    }*/

    public static String strong( String str )
    {
        return "<strong>" + str + "</strong>";
    }


    private static String td( String str )
    {
        return "<td>" + str + "</td>";
    }


    private static String tdColspan( String str, int colspan )
    {
        return "<td colspan=" + colspan + " >" + str + "</td>";
    }


    private static String th( String str )
    {
        return "<th>" + str + "</th>";
    }
}

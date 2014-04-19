/**
 * 
 */
package com.nanobi.client.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.nanobi.client.communication.TranslationResult;
import com.nanobi.client.constants.ResultClass;
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
        builder.append( tdColspan( strong( "Name: " ) + s.getName(), 1 ) );
        builder.append( tdColspan( strong( "USN: " ) + s.getUsn(), 2 ) );
        builder.append( tdColspan( strong( "Total: " ) + s.getTotal().toString(), 2 ) );
        builder.append( tdColspan( strong( "Percentage: " ) + s.getPercentage( StudentDaoConstants.MARKS_MAX ).toString(), 2 ) );
        builder.append( tdColspan( strong( "Class: " ) + s.getResultClass().toString(), 1 ) );
        builder.append( "</tr>" );
        builder.append( "\n<tr>" );
        ArrayList<String> allsubjects = new ArrayList<String>( s.getSubjectsWithSuffix( StudentDaoConstants.TOTAL_SUFFIX ) );
        Collections.sort( allsubjects );
        for ( String subj : allsubjects ) {
            builder.append( td( strong( subj.replace( "_TOT", "" ) ) + ": " + s.getScore( subj ).toString() ) );
        }
        builder.append( "</tr>\n" );
        builder.append( "<tr class=\"tablesep\"></tr>" );
        return builder.toString();
    }


   

    private static String strong( String str )
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


    public static String formatStudentsAsTable( List<Student> students )
    {
        StringBuilder ret = new StringBuilder();
        ret.append( "<table class=\"table table-bordered table-condensed student-table\">" );
        for ( Student s : students ) {
            ret.append( Utils.formatStudent( s ) );

        }
        ret.append( "</table>" );
        return ret.toString();
    }


    public static String getSummary( List<Student> students )
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "<div class=\"result-div\" style=\" padding: 3% \" >" );
        builder.append( "<h3> Summary </h3>" );
        builder.append( "Total Number of students: " + students.size() + "<br/>" );
        ResultClass[] classes = { ResultClass.FIRST_CLASS_DISCTINCTION, ResultClass.FIRST_CLASS, ResultClass.SECOND_CLASS,
            ResultClass.PASS, ResultClass.FAIL };
        for ( ResultClass c : classes ) {
            builder.append( "Number of Students who scored " + c + " class : " + countStudentsWithClass( students, c )
                + "<br/>" );
        }
        builder.append( "</div>" );
        return builder.toString();
    }


    public static int countStudentsWithClass( List<Student> students, ResultClass c )
    {
        int count = 0;
        for ( Student s : students ) {
            if ( s.getResultClass() == c )
                count++;
        }
        return count;
    }


    public static int getNumberofNumbers( String str )
    {
        String[] splitOnNumbers = str.replace( " ", "" ).split( "[0-9]+" );
        return splitOnNumbers.length;
    }


    public static Double[] extractNumbers( String str )
    {
        Double[] arr = null;
        int n = 0;
        if ( ( n = Utils.getNumberofNumbers( str ) ) > 0 ) {
            arr = new Double[n];
            int i = 0;
            for(String sub : str.split(" ")) {
                if(sub.matches( "[0-9]+([.,;'\"])?" )) {
                    String num = sub.replaceAll( "[^0-9]", "" );
                    arr[i++] = Double.parseDouble( num );
                }
            }
        }
        return arr;
    }
}

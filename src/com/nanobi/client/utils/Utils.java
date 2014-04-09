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
public class Utils {
	public static ArrayList<String> constructMappingFromResult(TranslationResult result) {
		ArrayList<String> mappings = new ArrayList<String>();
		
		for(String s: result.getDimensions().values()) {
			mappings.add(s);
		}
		
		for(String s: result.getMeasures().values()) {
			mappings.add(s);
		}
		
		for(String s: result.getFilters().values()) {
			mappings.add(s);
		}
		
		for(String s: result.getVisualizations().values()) {
			mappings.add(s);
		}
		
		return mappings;
	}
	
	
	public static String formatStudent(Student s) {
	    StringBuilder builder = new StringBuilder();
        builder.append( "<tr>" );
        builder.append( td(s.getName()) );
        builder.append( td(s.getUsn()) );
        ArrayList<String> allsubjects  = new ArrayList<String>(s.getSubjectsWithSuffix( StudentDaoConstants.TOTAL_SUFFIX ));
        Collections.sort( allsubjects );
        for(String subj : allsubjects) {
            builder.append( td(s.getScore( subj ).toString()) );
        }
        builder.append( td(s.getTotal().toString()) );
        builder.append( td(s.getPercentage( StudentDaoConstants.MARKS_MAX ).toString()) );
        builder.append( td(s.getResultClass().toString()) );
        builder.append( "</tr>" );
        return builder.toString();
	}
	
	public static String getHeadersForTable(Student s) {
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
	}
	
	private static String td(String str) {
	    return "<td>" + str + "</td>";
	}
	
	private static String th(String str) {
        return "<th>" + str + "</th>";
    }
}

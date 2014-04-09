/**
 * 
 */
package com.nanobi.client.utils;

import java.util.ArrayList;

import com.nanobi.client.communication.TranslationResult;

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
}

package com.oxf.client.mapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oxf.client.service.impl.AllStudentsService;
import com.oxf.client.service.impl.StudentPercentageService;
import com.oxf.client.service.impl.StudentsWithClassService;
import com.oxf.client.service.impl.TopStudentsService;

public class Mapper {
	
	private static Map<List<List<String>>, String> map = new HashMap<List<List<String>>, String>();
	
	private static final Mapper INSTANCE = new Mapper();
	
	
	public static Mapper getInstance() {
		return INSTANCE;
	}
	
	
	private Mapper() {
	}
	
	
	@SuppressWarnings("unchecked")
	private static void initMap() {
		map.clear();
		
		// For All Students Class Name
		String allStudentsClassName = AllStudentsService.class.getName();
		
		// list of mappings for All Students
		List<String> allStudentsMapping1 = Arrays.asList("all", "students");
		List<String> allStudentsMapping2 = Arrays.asList("every", "student");
		List<String> allStudentsMapping3 = Arrays.asList("students");
		
		List<List<String>> mappingsForAllStudents = Arrays.asList(allStudentsMapping1,allStudentsMapping2,allStudentsMapping3);
		map.put(mappingsForAllStudents, allStudentsClassName);
		
		
		// for percentage services
		String percentClassName = StudentPercentageService.class.getName();
		List<String> percentMapping1 = Arrays.asList("above N percent");
		List<String> percentMapping2 = Arrays.asList("below N percent");
		List<String> percentMapping3 = Arrays.asList("above N percent", "below N percent");
		List<String> percentMapping4 = Arrays.asList("above N percent","students");
        List<String> percentMapping5 = Arrays.asList("below N percent","students");
        List<String> percentMapping6 = Arrays.asList("above N percent", "below N percent","students");
		List<List<String>> mappingsForPercent= Arrays.asList(percentMapping1,percentMapping2,percentMapping3,percentMapping4,percentMapping5,percentMapping6);
		map.put( mappingsForPercent, percentClassName );
		
		
		String studentClassName = StudentsWithClassService.class.getName();
		List<String> classMapping1 = Arrays.asList("class");
		List<String> classMapping2 = Arrays.asList("students","class");
		List<String> classMapping3 = Arrays.asList("all", "students","class");
		List<String> classMapping4 = Arrays.asList("class","class");
		List<String> classMapping5 = Arrays.asList("class", "class","class");
		List<List<String>> mappingsForClass= Arrays.asList(classMapping1,classMapping2,classMapping3,classMapping4,classMapping5);
		map.put( mappingsForClass, studentClassName );
		
		
		String topNClassName =TopStudentsService.class.getName();
		List<String> topNMapping1 = Arrays.asList( "top" );
		List<String> topNMapping2 = Arrays.asList( "top", "students" );
		List<List<String>> mappingsForTop= Arrays.asList(topNMapping1,topNMapping2);
        map.put( mappingsForTop, topNClassName);
        
	}
	
	
	public static void loadMappings() {
		initMap();
		ServiceMapping serviceMapper = ServiceMapping.getInstance();
		serviceMapper.clearAllMappings();
		
		for(List<List<String>> mappings : map.keySet()) {
			String service = map.get(mappings);
			for(List<String> mapping : mappings) {
				serviceMapper.setMapping(mapping, service );
			}
		}		
	}
}

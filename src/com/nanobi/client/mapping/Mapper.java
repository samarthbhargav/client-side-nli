package com.nanobi.client.mapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.nanobi.client.service.impl.AllStudentsService;

public class Mapper {
	
	private static Map<List<List<String>>, String> map = new HashMap<List<List<String>>, String>();
	
	private static final Mapper INSTANCE = new Mapper();
	
	
	public static Mapper getInstance() {
		return INSTANCE;
	}
	
	
	private Mapper() {
		loadMappings();
	}
	
	
	@SuppressWarnings("unchecked")
	private static void initMap() {
		map.clear();
		
		
		// For All Students Class Name
		String allStudentsClassName = AllStudentsService.class.getName();
		List<String> allStudentsMapping1 = Arrays.asList("all", "students");
		
		
		
		List<List<String>> mappingsForAllStudents = Arrays.asList(allStudentsMapping1);
		map.put(mappingsForAllStudents, allStudentsClassName);
		
	
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
		System.out.println("Built Service Mapper: ");
		System.out.println(serviceMapper.toString());
	}
	
	
	public static void main(String[] args) {
		Mapper mapper = Mapper.getInstance();
		
	}
}

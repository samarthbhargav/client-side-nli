package com.nanobi.client.mapping;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nanobi.client.except.ServiceMappingDoesNotExist;
import com.nanobi.client.service.IService;
import com.nanobi.client.service.model.ServiceRequest;
import com.nanobi.client.service.model.ServiceResponse;

public class ServiceMapping
{
    private static final ServiceMapping INSTANCE = new ServiceMapping();
    
	private static Map<List<String>, String> map = new HashMap<List<String>, String>();
    
    private ServiceMapping() {
    }
    
    public static ServiceMapping getInstance() { 
    	return INSTANCE;
    }
    
    public void setMapping(List<String> mapping, String service) {
        Collections.sort( mapping );
        map.put( mapping, service );
    }
    
    public String getMappingFor(List<String> mapping) {
        Collections.sort( mapping );
        return map.get( mapping );
    }
    
    public void clearAllMappings() {
    	map.clear();
    }
    
    @SuppressWarnings ( "unchecked")
    public ServiceResponse getService(List<String> mappingTokens, Map<String,String> params) throws Exception { // TODO remove generic exception
        String className = getMappingFor( mappingTokens );
        if(className == null) {
            throw new ServiceMappingDoesNotExist("Mapping for " + mappingTokens.toString() + " does not exist");
        }
        Class<IService> cl = (Class<IService>) Class.forName( className );
        IService service = (IService) cl.newInstance();
        ServiceRequest req = new ServiceRequest(params);
        ServiceResponse response = service.service( req );
        return response;
    }
    
    @SuppressWarnings ( "unchecked")
    public String getServiceResponseAsString(List<String> mappingTokens, Map<String,String> params) throws Exception {
        System.out.println(map);
        String className = getMappingFor( mappingTokens );
        if(className == null) {
            throw new ServiceMappingDoesNotExist("Mapping for " + mappingTokens.toString() + " does not exist");
        }
        Class<IService> cl = (Class<IService>) Class.forName( className );
        IService service = (IService) cl.newInstance();
        ServiceRequest req = new ServiceRequest(params);
        String response = service.getResponseAsString( req );
        return response;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(List<String> mapping : map.keySet()) {
			builder.append( mapping + " -> " + map.get(mapping) );
		}
		
		return builder.toString();
	}
    
    
    
}

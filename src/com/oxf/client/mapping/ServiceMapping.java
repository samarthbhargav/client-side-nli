package com.oxf.client.mapping;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oxf.client.communication.TranslationResult;
import com.oxf.client.except.ServiceMappingDoesNotExist;
import com.oxf.client.service.IService;
import com.oxf.client.service.model.ServiceRequest;
import com.oxf.client.service.model.ServiceResponse;

public class ServiceMapping
{
    private static ServiceMapping INSTANCE;
    
	private static Map<List<String>, String> map = new HashMap<List<String>, String>();
    
    private ServiceMapping() {
        
    }
    
    public static ServiceMapping getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ServiceMapping();
            Mapper.loadMappings();
        }
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
    public ServiceResponse getService(List<String> mappingTokens, TranslationResult result) throws Exception { // TODO remove generic exception
        
        String className = getMappingFor( mappingTokens );
        if(className == null) {
            throw new ServiceMappingDoesNotExist("Mapping for " + mappingTokens.toString() + " does not exist");
        }
        Class<IService> cl = (Class<IService>) Class.forName( className );
        IService service = (IService) cl.newInstance();
        Map<String,String> params = service.extactParamsFromString( result );
        ServiceRequest req = new ServiceRequest(params);
        ServiceResponse response = service.service( req );
        return response;
    }
    
    @SuppressWarnings ( "unchecked")
    public String getServiceResponseAsString(List<String> mappingTokens, TranslationResult result) throws Exception {
        System.out.println(map);
        String className = getMappingFor( mappingTokens );
        if(className == null) {
            throw new ServiceMappingDoesNotExist("Mapping for " + mappingTokens.toString() + " does not exist");
        }
        Class<IService> cl = (Class<IService>) Class.forName( className );
        IService service = (IService) cl.newInstance();
        Map<String,String> params = service.extactParamsFromString( result );
        System.out.println(className + ":" +params);
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

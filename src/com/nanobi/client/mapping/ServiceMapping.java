package com.nanobi.client.mapping;

import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import com.nanobi.client.except.ServiceMappingDoesNotExist;
import com.nanobi.client.service.IService;
import com.nanobi.client.service.model.ServiceRequest;
import com.nanobi.client.service.model.ServiceResponse;

public class ServiceMapping
{
    Map<ArrayList<String>, String> map = new HashMap<ArrayList<String>, String>();
    
    public void setMapping(ArrayList<String> mapping, String service) {
        Collections.sort( mapping );
        map.put( mapping, service );
    }
    
    public String getMappingFor(ArrayList<String> mapping) {
        Collections.sort( mapping );
        return map.get( mapping );
    }
    
    @SuppressWarnings ( "unchecked")
    public ServiceResponse getService(ArrayList<String> mappingTokens, Map<String,String> params) throws Exception { // TODO remove generic exception
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
    public String getServiceResponseAsString(ArrayList<String> mappingTokens, Map<String,String> params) throws Exception {
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
}

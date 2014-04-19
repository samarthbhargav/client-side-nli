package com.nanobi.client.service;

import java.util.List;
import java.util.Map;

import com.nanobi.client.communication.TranslationResult;
import com.nanobi.client.service.model.ServiceRequest;
import com.nanobi.client.service.model.ServiceResponse;

public interface IService {
	ServiceResponse service(ServiceRequest request) throws Exception;
	
	String getResponseAsString(ServiceRequest request) throws Exception;
	
	public List<String> getParamMap();
	
	public Map<String,String> extactParamsFromString(TranslationResult result);
}

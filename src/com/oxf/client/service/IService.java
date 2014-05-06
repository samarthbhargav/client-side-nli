package com.oxf.client.service;

import java.util.List;
import java.util.Map;

import com.oxf.client.communication.TranslationResult;
import com.oxf.client.service.model.ServiceRequest;
import com.oxf.client.service.model.ServiceResponse;

public interface IService {
	ServiceResponse service(ServiceRequest request) throws Exception;
	
	String getResponseAsString(ServiceRequest request) throws Exception;
	
	public List<String> getParamMap();
	
	public Map<String,String> extactParamsFromString(TranslationResult result);
}

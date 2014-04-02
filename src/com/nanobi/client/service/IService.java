package com.nanobi.client.service;

import java.util.List;

import com.nanobi.client.service.model.ServiceRequest;
import com.nanobi.client.service.model.ServiceResponse;

public interface IService {
	ServiceResponse service(ServiceRequest request) throws Exception;
	
	String getResponseAsString(ServiceRequest request) throws Exception;
	
	public List<String> getParamMap();
}

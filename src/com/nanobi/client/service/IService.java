package com.nanobi.client.service;

import com.nanobi.client.service.model.ServiceRequest;
import com.nanobi.client.service.model.ServiceResponse;

public interface IService {
	ServiceResponse service(ServiceRequest request);
}

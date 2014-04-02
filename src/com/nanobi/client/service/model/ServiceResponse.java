package com.nanobi.client.service.model;

import java.util.HashMap;
import java.util.Map;

public class ServiceResponse {
	Map<String, Object> params = new HashMap<String, Object>();

	public Object getParam(String param) {
		return params.get(param);
	}

	public void setParam(String param, Object value) {
		params.put(param, value);
	}
}

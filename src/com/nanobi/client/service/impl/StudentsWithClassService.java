/**
 * 
 */
package com.nanobi.client.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.nanobi.client.communication.TranslationResult;
import com.nanobi.client.constants.ResultClass;
import com.nanobi.client.service.IService;
import com.nanobi.client.service.model.ServiceRequest;
import com.nanobi.client.service.model.ServiceResponse;

/**
 * @author hduser
 *
 */
public class StudentsWithClassService implements IService {

    public static final String PARAM_CLASS = "class";
    public static final String PARAM_STUDENT_LIST = "students";
    
    private static ResultClass mapClass(String cls) {
        
        if("fcd".equals( cls ) || "first class distinction".equals( cls )|| "first class with distinction".equals( cls )|| "distinction".equals( cls )) {
            return ResultClass.FIRST_CLASS_DISCTINCTION;
        } else if ("first class".equals( cls ) || "fc".equals( cls ) || "first class".equals( cls ))  {
            return ResultClass.FIRST_CLASS;
        } else if ("sc".equals( cls ) || "second class".equals( cls )) {
            return ResultClass.SECOND_CLASS;
        } else if ("passed".equals( cls ) || "pass".equals( cls )) {
            return ResultClass.PASS;
        } else if ("fail".equals( cls ) || "failed".equals( cls )) {
            return ResultClass.FAIL;
        }
        
        return null;
    }
	/* (non-Javadoc)
	 * @see com.nanobi.client.service.IService#service(com.nanobi.client.service.model.ServiceRequest)
	 */
	@Override
	public ServiceResponse service(ServiceRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nanobi.client.service.IService#getResponseAsString(com.nanobi.client.service.model.ServiceRequest)
	 */
	@Override
	public String getResponseAsString(ServiceRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nanobi.client.service.IService#getParamMap()
	 */
	@Override
	public List<String> getParamMap() {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public Map<String, String> extactParamsFromString( TranslationResult res )
    {
        Map<String,String> map = new HashMap<String,String>();
        Map<String,String> dimensions = res.getDimensions();
        for(Entry<String,String> entry: dimensions.entrySet()) {
            ResultClass cls = mapClass( entry.getKey() );
            if(cls != null) {
                map.put( PARAM_CLASS, cls.toString() );
            }
        }
        return map;
    }

}

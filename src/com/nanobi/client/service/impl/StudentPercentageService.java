package com.nanobi.client.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nanobi.client.constants.StudentDaoConstants;
import com.nanobi.client.model.Student;
import com.nanobi.client.service.IService;
import com.nanobi.client.service.model.ServiceRequest;
import com.nanobi.client.service.model.ServiceResponse;
import com.nanobi.client.utils.Utils;

public class StudentPercentageService implements IService{

    public static final String PARAM_UPPER_BOUND = "upper_bound" ;
    public static final String PARAM_LOWER_BOUND = "lower_bound" ;

    public static final String PARAM_STUDENT_LIST = "students";
    
	@SuppressWarnings ( "unchecked")
    @Override
	public ServiceResponse service(ServiceRequest request) throws Exception {
	    String upper = (String) request.getParam( PARAM_UPPER_BOUND );
	    String lower = (String) request.getParam( PARAM_LOWER_BOUND );
		
	    ServiceResponse resp = new AllStudentsService().service( null );
        List<Student> allStudents = (List<Student>) resp.getParam( AllStudentsService.PARAM_STUDENT_LIST );
        List<Student> reqdStudents = new ArrayList<Student>();
	    
	    if(upper != null && lower != null) {
	        Double upperBound = Double.parseDouble( upper );
	        Double lowerBound = Double.parseDouble( lower );
	        for(Student s : allStudents) {
	            if( s.getPercentage( StudentDaoConstants.MARKS_MAX ) >= lowerBound && s.getPercentage( StudentDaoConstants.MARKS_MAX ) <= upperBound) {
	                reqdStudents.add( s );
	            }
	        }
	    } else if (upper != null) {
	        Double upperBound = Double.parseDouble( upper );
	        for(Student s : allStudents) {
                if(s.getPercentage( StudentDaoConstants.MARKS_MAX ) <= upperBound) {
                    reqdStudents.add( s );
                }
            }
	    } else if (lower != null) {
	        Double lowerBound = Double.parseDouble( lower );
	        for(Student s : allStudents) {
                if( s.getPercentage( StudentDaoConstants.MARKS_MAX ) >= lowerBound ) {
                    reqdStudents.add( s );
                }
            }
	    } else {
	        for(Student s: allStudents) 
	            reqdStudents.add( s );
	    }
	    
	    ServiceResponse response = new ServiceResponse();
	    response.setParam( PARAM_STUDENT_LIST, reqdStudents);
		return response;
	}

	@SuppressWarnings ( "unchecked")
    @Override
	public String getResponseAsString(ServiceRequest request) throws Exception {
	    ServiceResponse response = this.service( request );
        List<Student> students = (List<Student>) response.getParam( PARAM_STUDENT_LIST );
        if ( students == null || students.size() == 0 ) {
            return "No Students Found";
        }
        StringBuilder b = new StringBuilder();
        b.append( Utils.getSummary( students ) );
        b.append( Utils.formatStudentsAsTable( students ) );
        return b.toString();
	}

	@Override
	public List<String> getParamMap() {
		List<String> list = Arrays.asList(PARAM_LOWER_BOUND,PARAM_UPPER_BOUND);
		return list;
	}
}

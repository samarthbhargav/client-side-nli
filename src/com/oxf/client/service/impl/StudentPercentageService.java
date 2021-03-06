package com.oxf.client.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.oxf.client.communication.TranslationResult;
import com.oxf.client.constants.Semester;
import com.oxf.client.constants.StudentDaoConstants;
import com.oxf.client.model.Student;
import com.oxf.client.service.IService;
import com.oxf.client.service.model.ServiceRequest;
import com.oxf.client.service.model.ServiceResponse;
import com.oxf.client.utils.Utils;


public class StudentPercentageService implements IService
{

    public static final String PARAM_UPPER_BOUND = "upper_bound";
    public static final String PARAM_LOWER_BOUND = "lower_bound";

    public static final String PARAM_STUDENT_LIST = "students";
    public static final String PARAM_SEMESTER = "semester";


    @SuppressWarnings ( "unchecked")
    @Override
    public ServiceResponse service( ServiceRequest request ) throws Exception
    {
        String upper = (String) request.getParam( PARAM_UPPER_BOUND );
        String lower = (String) request.getParam( PARAM_LOWER_BOUND );

        ServiceResponse resp = new AllStudentsService().service( request );
        List<Student> allStudents = (List<Student>) resp.getParam( AllStudentsService.PARAM_STUDENT_LIST );
        List<Student> reqdStudents = new ArrayList<Student>();

        if ( upper != null && lower != null ) {
            Double upperBound = Double.parseDouble( upper );
            Double lowerBound = Double.parseDouble( lower );
            for ( Student s : allStudents ) {
                if ( s.getPercentage( StudentDaoConstants.MARKS_MAX ) >= lowerBound
                    && s.getPercentage( StudentDaoConstants.MARKS_MAX ) <= upperBound ) {
                    reqdStudents.add( s );
                }
            }
        } else if ( upper != null ) {
            Double upperBound = Double.parseDouble( upper );
            for ( Student s : allStudents ) {
                if ( s.getPercentage( StudentDaoConstants.MARKS_MAX ) <= upperBound ) {
                    reqdStudents.add( s );
                }
            }
        } else if ( lower != null ) {
            Double lowerBound = Double.parseDouble( lower );
            for ( Student s : allStudents ) {
                if ( s.getPercentage( StudentDaoConstants.MARKS_MAX ) >= lowerBound ) {
                    reqdStudents.add( s );
                }
            }
        } else {
            for ( Student s : allStudents )
                reqdStudents.add( s );
        }

        ServiceResponse response = new ServiceResponse();
        response.setParam( PARAM_STUDENT_LIST, reqdStudents );
        return response;
    }


    @SuppressWarnings ( "unchecked")
    @Override
    public String getResponseAsString( ServiceRequest request ) throws Exception
    {
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
    public List<String> getParamMap()
    {
        List<String> list = Arrays.asList( PARAM_LOWER_BOUND, PARAM_UPPER_BOUND );
        return list;
    }


    @Override
    public Map<String, String> extactParamsFromString( TranslationResult res )
    {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String> dims = res.getDimensions();
        if ( dims.size() != 0 ) {
            for ( Entry<String, String> entry : dims.entrySet() ) {
                if ( entry.getValue().equals( "semester" ) ) {
                    Semester sem = Semester.getSemester( entry.getKey() );
                    if ( sem != null ) {
                        map.put( PARAM_SEMESTER, sem.toString() );
                    }
                }
            }
        }
        if ( map.size() == 0 ) {
            map.put( PARAM_SEMESTER, StudentDaoConstants.DEFAULT_SEMESTER.toString() );
        }
        Map<String, String> filters = res.getFilters();
        System.out.println( filters );
        for ( String filter : filters.keySet() ) {
            String value = filters.get( filter );
            if ( value.equals( "above N percent" ) ) {
                map.put( PARAM_LOWER_BOUND, Utils.extractNumbers( filter )[0].toString() );
            } else if ( value.equals( "below N percent" ) ) {
                map.put( PARAM_UPPER_BOUND, Utils.extractNumbers( filter )[0].toString() );
            }
        }
        return map;
    }


}

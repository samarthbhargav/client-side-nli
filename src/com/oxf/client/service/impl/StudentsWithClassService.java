/**
 * 
 */
package com.oxf.client.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.oxf.client.communication.TranslationResult;
import com.oxf.client.constants.ResultClass;
import com.oxf.client.constants.Semester;
import com.oxf.client.constants.StudentDaoConstants;
import com.oxf.client.dao.StudentDao;
import com.oxf.client.model.Student;
import com.oxf.client.service.IService;
import com.oxf.client.service.model.ServiceRequest;
import com.oxf.client.service.model.ServiceResponse;
import com.oxf.client.utils.Utils;


/**
 * @author hduser
 *
 */
public class StudentsWithClassService implements IService
{

    public static final String PARAM_CLASS = "class";
    public static final String PARAM_STUDENT_LIST = "students";
    public static final String PARAM_SEMESTER = "semester";


    private static ResultClass mapClass( String cls )
    {

        if ( "fcd".equals( cls ) || "first class distinction".equals( cls ) || "first class with distinction".equals( cls )
            || "distinction".equals( cls ) ) {
            return ResultClass.FIRST_CLASS_DISCTINCTION;
        } else if ( "first class".equals( cls ) || "fc".equals( cls ) || "first class".equals( cls ) ) {
            return ResultClass.FIRST_CLASS;
        } else if ( "sc".equals( cls ) || "second class".equals( cls ) ) {
            return ResultClass.SECOND_CLASS;
        } else if ( "passed".equals( cls ) || "pass".equals( cls ) ) {
            return ResultClass.PASS;
        } else if ( "fail".equals( cls ) || "failed".equals( cls ) ) {
            return ResultClass.FAIL;
        }

        return null;
    }


    /* (non-Javadoc)
     * @see com.nanobi.client.service.IService#service(com.nanobi.client.service.model.ServiceRequest)
     */
    @Override
    public ServiceResponse service( ServiceRequest request ) throws Exception
    {
        ResultClass cls = ResultClass.valueOf( request.getParam( PARAM_CLASS ).toString() );
        StudentDao dao = new StudentDao();
        List<Student> students = dao.getStudentsWithClass( cls,
            Semester.valueOf( request.getParam( PARAM_SEMESTER ).toString() ) );
        ServiceResponse response = new ServiceResponse();
        response.setParam( PARAM_STUDENT_LIST, students );
        return response;
    }


    /* (non-Javadoc)
     * @see com.nanobi.client.service.IService#getResponseAsString(com.nanobi.client.service.model.ServiceRequest)
     */
    @Override
    public String getResponseAsString( ServiceRequest request ) throws Exception
    {
        ServiceResponse response = this.service( request );
        @SuppressWarnings ( "unchecked") List<Student> students = (List<Student>) response.getParam( PARAM_STUDENT_LIST );
        if ( students == null || students.size() == 0 ) {
            return "No Students Found";
        }
        StringBuilder b = new StringBuilder();
        b.append( Utils.getSummary( students ) );
        b.append( Utils.formatStudentsAsTable( students ) );
        return b.toString();
    }


    /* (non-Javadoc)
     * @see com.nanobi.client.service.IService#getParamMap()
     */
    @Override
    public List<String> getParamMap()
    {
        return Arrays.asList( PARAM_CLASS, PARAM_STUDENT_LIST );
    }


    @Override
    public Map<String, String> extactParamsFromString( TranslationResult res )
    {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String> dimensions = res.getDimensions();
        List<String> identified = new ArrayList<String>();
        for ( Entry<String, String> entry : dimensions.entrySet() ) {
            ResultClass cls = mapClass( entry.getKey() );
            if ( cls != null ) {
                identified.add( cls.toString() );
                break;
            } else {
                Semester sem = Semester.getSemester( entry.getKey() );
                if ( sem != null ) {
                    map.put( PARAM_SEMESTER, sem.toString() );
                }
            }
        }
        String max = null;
        for ( String id : identified ) {
            if ( max == null ) {
                max = id;
            } else if ( max.length() < id.toString().length() ) {
                max = id;
            }
        }
        map.put( PARAM_CLASS, max );
        if ( !map.containsKey( PARAM_SEMESTER ) ) {
            map.put( PARAM_SEMESTER, StudentDaoConstants.DEFAULT_SEMESTER.toString() );
        }
        return map;
    }

}

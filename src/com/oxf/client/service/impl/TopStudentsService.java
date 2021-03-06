/**
 * 
 */
package com.oxf.client.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.oxf.client.communication.TranslationResult;
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
public class TopStudentsService implements IService
{

    public static final String PARAM_TOP = "top";
    public static final String PARAM_STUDENT_LIST = "students";
    public static final String PARAM_SEMESTER = "semester";


    /* (non-Javadoc)
     * @see com.nanobi.client.service.IService#service(com.nanobi.client.service.model.ServiceRequest)
     */
    @Override
    public ServiceResponse service( ServiceRequest request ) throws Exception
    {
        ServiceResponse response = new ServiceResponse();
        int n = (int) Double.parseDouble( request.getParam( PARAM_TOP ).toString() );
        StudentDao dao = new StudentDao();
        List<Student> top = dao.getTopNStudents( n, Semester.valueOf( request.getParam( PARAM_SEMESTER ).toString() ) );
        response.setParam( PARAM_STUDENT_LIST, top );
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
        return Arrays.asList( PARAM_STUDENT_LIST, PARAM_TOP, PARAM_SEMESTER );
    }


    @Override
    public Map<String, String> extactParamsFromString( TranslationResult res )
    {
        Map<String, String> filters = res.getFilters();
        Map<String, String> dims = res.getDimensions();
        Map<String, String> params = new HashMap<String, String>();
        if ( dims.size() != 0 ) {
            for ( Entry<String, String> entry : dims.entrySet() ) {
                if ( entry.getValue().equals( "semester" ) ) {
                    Semester sem = Semester.getSemester( entry.getKey() );
                    if ( sem != null ) {
                        params.put( PARAM_SEMESTER, sem.toString() );
                    }
                }
            }
        }
        if ( params.size() == 0 ) {
            params.put( PARAM_SEMESTER, StudentDaoConstants.DEFAULT_SEMESTER.toString() );
        }
        for ( Entry<String, String> entry : filters.entrySet() ) {
            if ( entry.getValue().equals( "top" ) ) {
                params.put( PARAM_TOP, Utils.extractNumbers( entry.getKey() )[0].toString() );
            }
        }
        params.put( PARAM_SEMESTER, Semester.III.toString() );
        return params;
    }

}

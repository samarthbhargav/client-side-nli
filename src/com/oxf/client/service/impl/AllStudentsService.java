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
public class AllStudentsService implements IService
{

    public static final String PARAM_STUDENT_LIST = "students";
    public static final String PARAM_SEMESTER = "semester";

    @Override
    public List<String> getParamMap()
    {
        String[] params = { PARAM_STUDENT_LIST , PARAM_SEMESTER};
        return Arrays.asList( params );
    }


    /* (non-Javadoc)
     * @see com.nanobi.client.service.IService#service(com.nanobi.client.service.model.ServiceRequest)
     */
    @Override
    public ServiceResponse service( ServiceRequest request ) throws Exception
    {
        StudentDao dao = new StudentDao();
        Semester sem = Semester.valueOf( request.getParam( PARAM_SEMESTER ).toString() );
        List<Student> students = dao.getStudents(sem);
        ServiceResponse response = new ServiceResponse();
        response.setParam( PARAM_STUDENT_LIST, students );
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
    public Map<String, String> extactParamsFromString( TranslationResult res )
    {
        Map<String,String> map = new HashMap<String,String>();
        Map<String, String> dims = res.getDimensions();
        if(dims.size() != 0) {
            for(Entry<String,String> entry : dims.entrySet()) {
                if(entry.getValue().equals( "semester" )) {
                    Semester sem = Semester.getSemester( entry.getKey() );
                    if(sem != null) {
                        map.put(PARAM_SEMESTER, sem.toString());
                    }
                }
            }
        }
        if(map.size() == 0) {
            map.put( PARAM_SEMESTER, StudentDaoConstants.DEFAULT_SEMESTER.toString() );
        }
        return map;
    }
    
    
}

/**
 * 
 */
package com.nanobi.client.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nanobi.client.communication.TranslationResult;
import com.nanobi.client.dao.StudentDao;
import com.nanobi.client.model.Student;
import com.nanobi.client.service.IService;
import com.nanobi.client.service.model.ServiceRequest;
import com.nanobi.client.service.model.ServiceResponse;
import com.nanobi.client.utils.Utils;


/**
 * @author hduser
 *
 */
public class AllStudentsService implements IService
{

    public static final String PARAM_STUDENT_LIST = "students";


    @Override
    public List<String> getParamMap()
    {
        String[] params = { PARAM_STUDENT_LIST };
        return Arrays.asList( params );
    }


    /* (non-Javadoc)
     * @see com.nanobi.client.service.IService#service(com.nanobi.client.service.model.ServiceRequest)
     */
    @Override
    public ServiceResponse service( ServiceRequest request ) throws Exception
    {
        StudentDao dao = new StudentDao();
        List<Student> students = dao.getStudents();
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
        return new HashMap<String,String>();
    }
    
    
}

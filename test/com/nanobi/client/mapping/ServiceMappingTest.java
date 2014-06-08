/**
 * 
 */
package com.nanobi.client.mapping;



import org.junit.Test;

import com.oxf.client.service.IService;
import com.oxf.client.service.impl.AllStudentsService;

/**
 * @author samarth
 *
 */
public class ServiceMappingTest
{

    @SuppressWarnings ( "unchecked")
    @Test
    public void test() throws Exception
    {
        String clas = AllStudentsService.class.getName();
        System.out.println(clas);
        Class<IService> serv = (Class<IService>) Class.forName( clas );
        IService service = serv.newInstance();
        System.out.println(service.getResponseAsString( null ));
    }

}

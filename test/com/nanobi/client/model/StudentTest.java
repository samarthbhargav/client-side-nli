package com.nanobi.client.model;


import java.net.UnknownHostException;
import java.util.List;

import org.junit.Test;

import com.nanobi.client.constants.DaoConstants;

import com.nanobi.client.dao.StudentDao;


public class StudentTest
{

    @Test
    public void test() throws UnknownHostException
    {
        StudentDao dao = new StudentDao();
        List<Student> students = dao.getStudents();
        Student s = students.get( 0 );

        System.out.println( s.getName() );
        System.out.println( s.getUsn() );
        System.out.println( s.getTotal() );
        System.out.println( s.getPercentage( DaoConstants.MAX_MARKS ) );

    }

}

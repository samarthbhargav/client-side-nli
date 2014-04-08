package com.nanobi.client.model;


import java.net.UnknownHostException;
import java.util.List;

import org.junit.Test;

import com.nanobi.client.constants.DaoConstants;
import com.nanobi.client.constants.ResultClass;
import com.nanobi.client.dao.StudentDao;


public class StudentTest
{

    @Test
    public void test() throws UnknownHostException
    {
        StudentDao dao = new StudentDao();
        List<Student> students = dao.getStudents();
        for(Student s : students) {
        	System.out.println(s.getName() + " " + s.getTotal() + " " + s.getPercentage(DaoConstants.MARKS_MAX));
        	System.out.println(s.getResultClass());
        	System.out.println();
        }
    }

}

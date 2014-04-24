package com.nanobi.client.dao;



import java.net.UnknownHostException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.nanobi.client.constants.ResultClass;
import com.nanobi.client.model.Student;

public class StudentDaoTest
{

   /* @Test
    public void test() throws UnknownHostException
    {
        StudentDao dao = new StudentDao();
        List<Student> students = dao.getStudents();
        Assert.assertTrue(students.size() == 111);
        
        Assert.assertTrue( 
            dao.getStudentsWithClass( ResultClass.FAIL ).size() +
            dao.getStudentsWithClass( ResultClass.PASS ).size() +
            dao.getStudentsWithClass( ResultClass.SECOND_CLASS ).size() + 
            dao.getStudentsWithClass( ResultClass.FIRST_CLASS ).size() + 
            dao.getStudentsWithClass( ResultClass.FIRST_CLASS_DISCTINCTION ).size() == 111 );
        
        students = dao.getStudentsWithClass( ResultClass.FIRST_CLASS_DISCTINCTION);
        
        Assert.assertTrue( students.size() == 27 );
        
        
        students = dao.getStudentsWithClass( ResultClass.FIRST_CLASS );
        System.out.println(students.size());
        Assert.assertTrue( students.size() == 35 ); // TODO Verify this
        
        students = dao.getStudentsWithClass( ResultClass.SECOND_CLASS );
        System.out.println(students.size()); // TODO VERIFY THIS
        Assert.assertTrue( students.size() == 16 ); 
    }
*/
}

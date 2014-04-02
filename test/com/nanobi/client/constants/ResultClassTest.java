package com.nanobi.client.constants;



import org.junit.Assert;
import org.junit.Test;

public class ResultClassTest
{

    @Test
    public void test()
    {
        ResultClass c = ResultClass.getClassForScore( 0.0 );
        Assert.assertTrue( c == ResultClass.FAIL );
        
        c = ResultClass.getClassForScore( 34.99 );
        Assert.assertTrue( c == ResultClass.FAIL );
        
        c = ResultClass.getClassForScore( 35.00 );
        Assert.assertTrue( c == ResultClass.THIRD_CLASS );
        
        c = ResultClass.getClassForScore( 49.999 );
        Assert.assertTrue( c == ResultClass.THIRD_CLASS );
        
        c = ResultClass.getClassForScore( 50.0 );
        Assert.assertTrue( c == ResultClass.SECOND_CLASS );
        
        c = ResultClass.getClassForScore( 59.99 );
        Assert.assertTrue( c == ResultClass.SECOND_CLASS);
        
        c = ResultClass.getClassForScore( 60.00);
        Assert.assertTrue( c == ResultClass.FIRST_CLASS );
        
        c = ResultClass.getClassForScore( 69.99);
        Assert.assertTrue( c == ResultClass.FIRST_CLASS );

        
        c = ResultClass.getClassForScore( 70.00);
        Assert.assertTrue( c == ResultClass.FIRST_CLASS_DISCTINCTION );
        
        c = ResultClass.getClassForScore( 100.00);
        Assert.assertTrue( c == ResultClass.FIRST_CLASS_DISCTINCTION );
    }
    
    @Test
    public void test2() {
        
    }

}

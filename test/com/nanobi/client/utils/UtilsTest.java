/**
 * 
 */
package com.nanobi.client.utils;

import static org.junit.Assert.*;


import org.junit.Test;

/**
 * @author samarth
 *
 */
public class UtilsTest
{

    @Test
    public void test()
    {
        assertTrue( 2 == Utils.getNumberofNumbers( "below 20 and above 30" ) );
        
        assertTrue( 1 == Utils.getNumberofNumbers( "below 20 " ) );
        String str = "below 29";
        System.out.println(str.matches( ".*[0-9]+.*" ));
    }

}

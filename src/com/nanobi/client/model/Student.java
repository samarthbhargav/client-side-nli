package com.nanobi.client.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.nanobi.client.constants.Semester;
import com.nanobi.client.constants.StudentDaoConstants;
import com.nanobi.client.constants.ResultClass;


public class Student
{
    private String name;
    private String usn;
    private Semester sem;
    
    
    private Map<String, Double> scores;


    public Student()
    {
        scores = new HashMap<String, Double>();
    }


    public String getName()
    {
        return name;
    }


    public void setName( String name )
    {
        this.name = name;
    }


    public String getUsn()
    {
        return usn;
    }


    public void setUsn( String usn )
    {
        this.usn = usn;
    }


    public void addScore( String subject, Double score )
    {
        scores.put( subject, score );
    }


    public Double getScore( String subject )
    {
        return scores.get( subject );
    }


    public Set<String> getAllSubjects()
    {
        return scores.keySet();
    }


    public Set<String> getSubjectsWithSuffix( String suffix )
    {
        Set<String> allSubjects = new HashSet<String>( scores.keySet() );
        Set<String> suffixedSubjects = new HashSet<String>();
        for ( String subj : allSubjects ) {
            if ( subj.endsWith( suffix ) ) {
                suffixedSubjects.add( subj );
            }
        }
        return suffixedSubjects;
    }


    public void clearScores()
    {
        scores.clear();
    }


    public Double getTotal()
    {
        Double total = 0.0;
        for ( String subj : scores.keySet() ) {
            if ( subj.endsWith( StudentDaoConstants.TOTAL_SUFFIX ) ) {
                total += scores.get( subj );
            }
        }
        return total;
    }


    public Double getPercentage( Double maxMarks )
    {
        return 100 * ( getTotal() / maxMarks );
    }


    public boolean hasFailed()
    {
        boolean fail = false;
        for ( String subj : scores.keySet() ) {
            if ( subj.endsWith( StudentDaoConstants.EXTERNAL_SUFFIX ) ) {
                if ( scores.get( subj ) < StudentDaoConstants.MARKS_EXTERNAL_MIN ) {
                    fail = true;
                }
            }
            if ( subj.endsWith( StudentDaoConstants.TOTAL_SUFFIX ) ) {
                if ( scores.get( subj ) < StudentDaoConstants.MARKS_TOTAL_MIN ) {
                    fail = true;
                }
            }
        }
        return fail;
    }


    public ResultClass getResultClass()
    {
        if ( hasFailed() )
            return ResultClass.FAIL;
        else
            return ResultClass.getClassForScore( getPercentage( StudentDaoConstants.MARKS_MAX ) );
    }


    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        String SEP = " ";
        builder.append( "Name: " + getName() );
        builder.append( SEP + "USN: " + getUsn() );
        for ( String s : scores.keySet() ) {
            builder.append( SEP + s + ": " + scores.get( s ) );
        }
        builder.append( SEP + "Total: " + getTotal() );
        // TODO Add Percentage and Class
        return builder.toString();
    }


    public static Comparator<Student> getTotalMarksComparator()
    {
        return new Comparator<Student>() {
            @Override
            public int compare( Student o1, Student o2 )
            {
                return o2.getTotal().compareTo( o1.getTotal() );

            }
        };
    }


    public Semester getSemester()
    {
        return sem;
    }


    public void setSemester( Semester sem )
    {
        this.sem = sem;
    }
}

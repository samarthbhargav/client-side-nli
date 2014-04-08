package com.nanobi.client.constants;

public class StudentDaoConstants {
	public static final String DB_NAME = "oxford_reporting";
	public static final String STUDENT_COLLECTION_NAME = "student";
	
	public static final Double MARKS_MAX = 900.0;
	
	public static final Double MARKS_EXTERNAL_MIN = 35.00;
	public static final Double MARKS_TOTAL_MIN = 50.00;
	
	public static final String INTERAL_SUFFIX = "_INT";
	public static final String EXTERNAL_SUFFIX = "_EXT";
	public static final String TOTAL_SUFFIX = "_TOT";
	
	public static final String FIELD_NAME = "name";
	public static final String FIELD_USN = "usn";
	
	
	// All subjects
	public static final String SUBJ_MATH_INT = "10MAT31_INT";
	public static final String SUBJ_MATH_EXT = "10MAT31_EXT";
	public static final String SUBJ_MATH_TOT = "10MAT31_TOT";
	
	public static final String SUBJ_ELEC_INT = "10CS32_INT";
	public static final String SUBJ_ELEC_EXT = "10CS32_EXT";
	public static final String SUBJ_ELEC_TOT = "10CS32_TOT";
	
	public static final String SUBJ_LOGIC_INT = "10CS33_INT";
	public static final String SUBJ_LOGIC_EXT = "10CS33_EXT";
	public static final String SUBJ_LOGIC_TOT = "10CS33_TOT";
	
	public static final String SUBJ_DMS_INT = "10CS34_INT";
	public static final String SUBJ_DMS_EXT = "10CS34_EXT";
	public static final String SUBJ_DMS_TOT = "10CS34_TOT";
	
	public static final String SUBJ_DATA_INT = "10CS35_INT";
	public static final String SUBJ_DATA_EXT = "10CS35_EXT";
	public static final String SUBJ_DATA_TOT = "10CS35_TOT";
	
	public static final String SUBJ_OOPS_INT = "10CS36_INT";
	public static final String SUBJ_OOPS_EXT = "10CS36_EXT";
	public static final String SUBJ_OOPS_TOT = "10CS36_TOT";
	
	public static final String SUBJ_LAB_DS_OOPS_INT = "10CSL37_INT";
	public static final String SUBJ_LAB_DS_OOPS_EXT = "10CSL37_EXT";
	public static final String SUBJ_LAB_DS_OOPS_TOT = "10CSL37_TOT";
	
	public static final String SUBJ_LAB_LD_EC_INT = "10CSL38_INT";
	public static final String SUBJ_LAB_LD_EC_EXT = "10CSL38_EXT";
	public static final String SUBJ_LAB_LD_EC_TOT = "10CSL38_TOT";
	
	
	public static final String[] SUBJECTS_LIST = {
		SUBJ_MATH_INT,SUBJ_MATH_EXT,SUBJ_MATH_TOT,
		SUBJ_ELEC_INT,SUBJ_ELEC_EXT,SUBJ_ELEC_TOT,
		SUBJ_LOGIC_INT,SUBJ_LOGIC_EXT,SUBJ_LOGIC_TOT,
		SUBJ_DMS_INT,SUBJ_DMS_EXT,SUBJ_DMS_TOT,
		SUBJ_DATA_INT,SUBJ_DATA_EXT,SUBJ_DATA_TOT,
		SUBJ_OOPS_INT,SUBJ_OOPS_EXT,SUBJ_OOPS_TOT,
		SUBJ_LAB_DS_OOPS_INT,SUBJ_LAB_DS_OOPS_EXT,SUBJ_LAB_DS_OOPS_TOT,
		SUBJ_LAB_LD_EC_INT,SUBJ_LAB_LD_EC_EXT,SUBJ_LAB_LD_EC_TOT
		} ;
}

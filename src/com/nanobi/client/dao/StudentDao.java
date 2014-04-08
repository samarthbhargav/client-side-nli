package com.nanobi.client.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.nanobi.client.constants.DaoConstants;
import com.nanobi.client.constants.ResultClass;
import com.nanobi.client.model.Student;

public class StudentDao {

	private static ArrayList<Student> students;

	public StudentDao() throws UnknownHostException {
		loadStudents();
	}

	private static void loadStudents() throws UnknownHostException {
		if (students == null) {
			MongoClient client = new MongoClient();
			DBCollection collection = client.getDB(DaoConstants.DB_NAME)
					.getCollection(DaoConstants.STUDENT_COLLECTION_NAME);

			students = new ArrayList<Student>();

			DBCursor cursor = collection.find();
			for (DBObject object : cursor) {
				students.add(convertToStudent(object));
			}
		}

	}

	private static Student convertToStudent(DBObject object) {
		Student student = new Student();
		student.setName(getFieldOrNull(object, DaoConstants.FIELD_NAME));
		student.setUsn(getFieldOrNull(object, DaoConstants.FIELD_USN));
		for(String subject: DaoConstants.SUBJECTS_LIST) {
			student.addScore(subject, getScoreForSubject(object, subject));
		}
		return student;
	}

	private static String getFieldOrNull(DBObject object,String field) {
		return object.get(field).toString();
	}
	
	private static Double getScoreForSubject(DBObject object, String subject) {
		return Double.parseDouble(object.get(subject).toString());
	}
	
	
	public List<Student> getStudentsWithClass(ResultClass c) {
		List<Student> allStudents = getStudents();
		List<Student> students = new ArrayList<Student>();
		for(Student s: allStudents) {
		    if(c == s.getResultClass()) {
		        students.add( s );
		    }
		}
		return students;
	}
	
	public List<Student> getStudents() {
		return students; 
	}
}

package com.ash.spring.orm.dao;

import java.util.List;

import com.ash.spring.orm.entities.Student;

public interface StudentDao {
	public int insert(Student student);

	public Student getStudent(int studentId);

	public List<Student> getAllStudents();


	void deleteStudent(int studentId);

	void updateStudent(int studentId, String name, String city);
}

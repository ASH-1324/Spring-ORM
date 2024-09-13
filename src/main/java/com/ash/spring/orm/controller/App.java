package com.ash.spring.orm.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ash.spring.orm.dao.StudentDao;
import com.ash.spring.orm.entities.Student;

public class App {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"com/ash/spring/orm/configuration/Config.xml");
		StudentDao studentDao = applicationContext.getBean(StudentDao.class);

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		boolean flag = true;

		while (flag) {
			System.out.println("PRESS 1 FOR ADD STUDENT");
			System.out.println("PRESS 2 FOR GET STUDENT");
			System.out.println("PRESS 3 FOR GET ALL STUDENTS");
			System.out.println("PRESS 4 FOR UPDATE STUDENT");
			System.out.println("PRESS 5 FOR DELETE STUDENT");
			System.out.println("PRESS 6 FOR EXIT");

			System.out.print("Choose a number: ");
			int input;
			try {
				input = Integer.parseInt(bufferedReader.readLine());

				switch (input) {
				case 1:
					System.out.print("Enter student name: ");
					String name = bufferedReader.readLine();
					System.out.print("Enter student city: ");
					String city = bufferedReader.readLine();
					Student student = new Student();
					student.setName(name);
					student.setCity(city);
					int insert = studentDao.insert(student);
					System.out.println("Inserted student with ID = " + insert);
					System.out.println("======================================");
					break;

				case 2:
					System.out.print("Enter student ID to fetch: ");
					int id = Integer.parseInt(bufferedReader.readLine());
					Student fetchedStudent = studentDao.getStudent(id);
					if (fetchedStudent != null) {
						System.out.println(
								"Fetched Student: " + fetchedStudent.getName() + ", " + fetchedStudent.getCity());
					} else {
						System.out.println("Student not found!");
					}
					System.out.println("======================================");
					break;

				case 3:
					List<Student> allStudents = studentDao.getAllStudents();
					System.out.println("All Students:");
					for (Student s : allStudents) {
						System.err.println("Id : " + s.getId() + " Name " + s.getName() + " City : " + s.getCity());
					}
					System.out.println("======================================");
					break;

				case 4:
					System.out.print("Enter student ID to update: ");
					int updateId = Integer.parseInt(bufferedReader.readLine());
					System.out.print("Enter new name: ");
					String newName = bufferedReader.readLine();
					System.out.print("Enter new city: ");
					String newCity = bufferedReader.readLine();
					studentDao.updateStudent(updateId, newName, newCity);
					System.out.println("Student updated successfully.");
					System.out.println("======================================");
					break;

				case 5:
					System.out.print("Enter student ID to delete: ");
					int deleteId = Integer.parseInt(bufferedReader.readLine());
					studentDao.deleteStudent(deleteId);
					System.out.println("Student deleted successfully.");
					System.out.println("======================================");
					break;

				case 6:
					flag = false;
					break;

				default:
					System.err.println("Select a valid option!!!");
					System.out.println("======================================");
					break;
				}

			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
		}
		System.err.println("Thank you for using my application");
		System.out.println("Program exited.");
	}
}

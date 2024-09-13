package com.ash.spring.orm.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ash.spring.orm.dao.StudentDao;
import com.ash.spring.orm.entities.Student;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public int insert(Student student) {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(student);
    }

    @Override
    @Transactional
    public Student getStudent(int studentId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Student.class, studentId);
    }

    @Override
    @Transactional
    public List<Student> getAllStudents() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Student", Student.class).list();
    }

    @Override
    @Transactional
    public void updateStudent(int studentId, String name, String city) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, studentId);
        if (student != null) {
            student.setName(name);
            student.setCity(city);
            session.update(student);
        }
    }

    @Override
    @Transactional
    public void deleteStudent(int studentId) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, studentId);
        if (student != null) {
            session.delete(student);
        }
    }
}

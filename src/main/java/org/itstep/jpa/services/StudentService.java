package org.itstep.jpa.services;

import org.itstep.jpa.entities.Student;

import java.util.List;

public interface StudentService {

    Student addStudent(Student student);

    List<Student> getAllStudent();

    Student getStudent(Long id);

    void updateStudent(Student student);

    void deleteStudent(Long id);
}

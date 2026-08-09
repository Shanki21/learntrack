package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import java.util.ArrayList;

public class StudentService {

    private StudentRepository studentRepository;

    // constructor receives an existing repository instance instead of creating one
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // addStudent — generates the ID itself, caller doesn't supply one
    public Student addStudent(String firstName, String lastName, String email, String batch) {
        String id = IdGenerator.getNextStudentId();   // service generates the ID, not the caller
        Student student = new Student(id, firstName, lastName, email, batch, true);
        studentRepository.add(student);
        return student;
    }

    public ArrayList<Student> listStudents(){
        return studentRepository.findAll()  ;
    }

    public Student findStudentById(String id) throws EntityNotFoundException {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new EntityNotFoundException("Student not found with id: " + id);
        }
        return student;
    }
    public void deactivateStudent(String id) throws EntityNotFoundException {
        Student student = findStudentById(id);   // reuse method 2 — also throws if not found
        student.setActive(false);
    }
}
package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import java.util.ArrayList;

public class StudentRepository {

    private ArrayList<Student> students = new ArrayList<>();

    public void add(Student student){
        students.add(student);
    }

    public Student findById(String id){
        for(Student s : students){
            if(s.getId().equals(id)){
                return s;
            }
        }
        return null;
    }

    public ArrayList<Student> findAll() {
        return students;
    }

    public void remove(Student student){
        students.remove(student);
    }
}

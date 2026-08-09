package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;

import java.util.ArrayList;

public class EnrollmentRepository {

    private ArrayList<Enrollment> enrollments = new ArrayList<>();

    public void add(Enrollment enrollment){
        enrollments.add(enrollment);
    }

    public Enrollment findById(String id){
        for(Enrollment e : enrollments){
            if(e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public ArrayList<Enrollment> findAll(){
        return enrollments;
    }

    public void remove(Enrollment enrollment){
        enrollments.remove(enrollment);
    }
}

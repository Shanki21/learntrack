package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;

import java.util.ArrayList;

public class CourseRepository {

    private ArrayList<Course> courses = new ArrayList<>();

    public void add(Course course){
        courses.add(course);
    }

    public Course findById(String id){
        for(Course c : courses){
            if(c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }

    public ArrayList<Course> findAll(){
        return courses;
    }

    public void remove(Course course){
        courses.remove(course);
    }
}

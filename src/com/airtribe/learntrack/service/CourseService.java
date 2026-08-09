package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.enums.CourseStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;

public class CourseService {

    private CourseRepository courseRepository;   // no 'new' here anymore

    // constructor receives an existing repository instance instead of creating one
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(String courseName, String description, int durationInWeeks){
        String id = IdGenerator.getNextCourseId();
        Course course = new Course(id, courseName, description, durationInWeeks, CourseStatus.ACTIVE);
        courseRepository.add(course);
        return course;
    }

    public ArrayList<Course> listCourses(){
        return courseRepository.findAll();
    }

    public Course findCourseById(String id) throws EntityNotFoundException {
        Course course = courseRepository.findById(id);
        if (course == null) {
            throw new EntityNotFoundException("Course not found with id: " + id);
        }
        return course;
    }
    public void deactivateCourse(String id) throws EntityNotFoundException {
        Course course = findCourseById(id);
        if (course.getStatus() == CourseStatus.ACTIVE) {
            course.setStatus(CourseStatus.INACTIVE);
        } else {
            course.setStatus(CourseStatus.ACTIVE);
        }
    }

}

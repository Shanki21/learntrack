package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.enums.CourseStatus;   // needed since CourseStatus lives in a different package

public class Course {

    // Private fields — encapsulation, no direct outside access
    private String id;
    private String courseName;
    private String description;
    private int durationInWeeks;
    private CourseStatus status;   // used enum

    // Parameterized constructor — sets all fields at creation time
    public Course(String id, String courseName, String description, int durationInWeeks, CourseStatus status) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.durationInWeeks = durationInWeeks;
        this.status = status;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDescription() {
        return description;
    }

    public int getDurationInWeeks() {
        return durationInWeeks;
    }

    public CourseStatus getStatus() {
        return status;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDurationInWeeks(int durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }
}
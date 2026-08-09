package com.airtribe.learntrack.entity;

import java.time.LocalDate;                              // for enrollmentDate
import com.airtribe.learntrack.enums.EnrollmentStatus;    // for status

public class Enrollment {

    private String id;
    private String studentId;       // references a Student by ID, not embedded directly (avoids tight coupling)
    private String courseId;        // references a Course by ID, same reasoning
    private LocalDate enrollmentDate;
    private EnrollmentStatus status;

    public Enrollment(String id, String studentId, String courseId, LocalDate enrollmentDate, EnrollmentStatus status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }
}
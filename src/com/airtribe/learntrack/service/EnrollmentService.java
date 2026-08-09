package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import java.time.LocalDate;
import java.util.ArrayList;

public class EnrollmentService {

    private EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

    // NOT created with 'new' here — received via constructor so the SAME
    // repository instances are shared with StudentService/CourseService
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public EnrollmentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // Enrolls a student in a course, after verifying BOTH exist first
    public Enrollment enrollStudent(String studentId, String courseId) throws EntityNotFoundException {
        Student student = studentRepository.findById(studentId);
        if (student == null) {
            throw new EntityNotFoundException("Student not found with id: " + studentId);
        }
        Course course = courseRepository.findById(courseId);
        if (course == null) {
            throw new EntityNotFoundException("Course not found with id: " + courseId);
        }
        String id = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(id, studentId, courseId, LocalDate.now(), EnrollmentStatus.ACTIVE);
        enrollmentRepository.add(enrollment);
        return enrollment;
    }

    // Filters all enrollments down to just the ones for a given student
    public ArrayList<Enrollment> getEnrollmentsByStudent(String studentId) {
        ArrayList<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollmentRepository.findAll()) {
            if (e.getStudentId().equals(studentId)) {
                result.add(e);
            }
        }
        return result;
    }

    // Marks an enrollment as COMPLETED / CANCELLED / etc.
    public void updateEnrollmentStatus(String enrollmentId, EnrollmentStatus newStatus) throws EntityNotFoundException {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId);
        if (enrollment == null) {
            throw new EntityNotFoundException("Enrollment id not found with id: " + enrollmentId);
        }
        enrollment.setStatus(newStatus);
    }
}
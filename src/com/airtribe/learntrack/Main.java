package com.airtribe.learntrack;

import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.util.InputValidator;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.enums.EnrollmentStatus;

public class Main {

    public static void main(String[] args) {

        // one shared repository per entity type
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();

        // all services share the SAME repository instances
        StudentService studentService = new StudentService(studentRepository);
        CourseService courseService = new CourseService(courseRepository);
        EnrollmentService enrollmentService = new EnrollmentService(studentRepository, courseRepository);

        InputValidator inputValidator = new InputValidator();

        while (true) {
            System.out.println(AppConstants.DIVIDER);
            System.out.println(AppConstants.APP_NAME);
            System.out.println(AppConstants.DIVIDER);
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Deactivate Student");
            System.out.println("5. Add Course");
            System.out.println("6. View Courses");
            System.out.println("7. Toggle Course Status");
            System.out.println("8. Enroll Student");
            System.out.println("9. View Enrollments for Student");
            System.out.println("10. Update Enrollment Status");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = inputValidator.getMenuChoice();

            if (choice == MenuOptions.EXIT) {
                System.out.println("Goodbye!");
                break;

            } else if (choice == MenuOptions.ADD_STUDENT) {
                String firstName = inputValidator.getNonEmptyString("Enter first name: ");
                String lastName = inputValidator.getNonEmptyString("Enter last name: ");
                String email = inputValidator.getNonEmptyString("Enter email: ");
                String batch = inputValidator.getNonEmptyString("Enter batch: ");
                studentService.addStudent(firstName, lastName, email, batch);
                System.out.println("Student added successfully!");

            } else if (choice == MenuOptions.VIEW_STUDENTS) {
                for (var student : studentService.listStudents()) {
                    System.out.println(student.getId() + " - " + student.getDisplayName() + " - Active: " + student.isActive());
                }

            } else if (choice == MenuOptions.SEARCH_STUDENT) {
                String id = inputValidator.getNonEmptyString("Enter student ID: ");
                try {
                    var student = studentService.findStudentById(id);
                    System.out.println(student.getId() + " - " + student.getDisplayName());
                } catch (EntityNotFoundException e) {
                    System.out.println("Error: " + e.getMessage());
                }

            } else if (choice == MenuOptions.DEACTIVATE_STUDENT) {
                String id = inputValidator.getNonEmptyString("Enter student ID: ");
                try {
                    studentService.deactivateStudent(id);
                    System.out.println("Student deactivated successfully!");
                } catch (EntityNotFoundException e) {
                    System.out.println("Error: " + e.getMessage());
                }

            } else if (choice == MenuOptions.ADD_COURSE) {
                String courseName = inputValidator.getNonEmptyString("Enter course name: ");
                String description = inputValidator.getNonEmptyString("Enter description: ");
                String durationInput = inputValidator.getNonEmptyString("Enter duration in weeks: ");
                int durationInWeeks = Integer.parseInt(durationInput);
                courseService.addCourse(courseName, description, durationInWeeks);
                System.out.println("Course added successfully!");

            } else if (choice == MenuOptions.VIEW_COURSES) {
                for (var course : courseService.listCourses()) {
                    System.out.println(course.getId() + " - " + course.getCourseName() + " - Status: " + course.getStatus());
                }

            } else if (choice == MenuOptions.TOGGLE_COURSE_STATUS) {
                String id = inputValidator.getNonEmptyString("Enter course ID: ");
                try {
                    courseService.deactivateCourse(id);
                    System.out.println("Course status updated successfully!");
                } catch (EntityNotFoundException e) {
                    System.out.println("Error: " + e.getMessage());
                }

            } else if (choice == MenuOptions.ENROLL_STUDENT) {
                // needs TWO ids, both used together as arguments to one service call
                String studentId = inputValidator.getNonEmptyString("Enter student ID: ");
                String courseId = inputValidator.getNonEmptyString("Enter course ID: ");
                try {
                    enrollmentService.enrollStudent(studentId, courseId);
                    System.out.println("Enrollment successful!");
                } catch (EntityNotFoundException e) {
                    System.out.println("Error: " + e.getMessage());
                }

            } else if (choice == MenuOptions.VIEW_ENROLLMENTS_FOR_STUDENT) {
                // no try-catch needed — getEnrollmentsByStudent() doesn't throw,
                // it just returns an empty list if there are no matches
                String studentId = inputValidator.getNonEmptyString("Enter student ID: ");
                for (var enrollment : enrollmentService.getEnrollmentsByStudent(studentId)) {
                    System.out.println(enrollment.getId() + " - Course: " + enrollment.getCourseId()
                            + " - Status: " + enrollment.getStatus()
                            + " - Date: " + enrollment.getEnrollmentDate());
                }

            } else if (choice == MenuOptions.UPDATE_ENROLLMENT_STATUS) {
                // EnrollmentStatus.valueOf(String) converts text like "COMPLETED"
                // into the matching enum constant. It throws IllegalArgumentException
                // if the text doesn't match any constant exactly (case-sensitive),
                // so we .toUpperCase() the input to be forgiving of lowercase typing.
                String enrollmentId = inputValidator.getNonEmptyString("Enter enrollment ID: ");
                String statusInput = inputValidator.getNonEmptyString("Enter new status (ACTIVE/COMPLETED/CANCELLED): ");
                try {
                    EnrollmentStatus newStatus = EnrollmentStatus.valueOf(statusInput.toUpperCase());
                    enrollmentService.updateEnrollmentStatus(enrollmentId, newStatus);
                    System.out.println("Enrollment status updated successfully!");
                } catch (EntityNotFoundException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid status. Please enter ACTIVE, COMPLETED, or CANCELLED.");
                }

            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
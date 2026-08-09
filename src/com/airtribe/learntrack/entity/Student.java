package com.airtribe.learntrack.entity;

// Student inherits id, firstName, lastName, email from Person
public class Student extends Person {

    // Student-specific fields not present in Person
    private String batch;
    private boolean active;

    public Student(String id, String firstName, String lastName, String email, String batch, boolean active) {
        // super() must be the first line — initializes the inherited Person fields
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = active;   // fixed: use the parameter, not a hardcoded value
    }

    public String getBatch() {
        return batch;
    }

    // 'is' prefix is the convention for boolean getters, not 'get'
    public boolean isActive() {
        return active;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Overrides Person's version — Student displays with a role prefix
    // Uses getFirstName()/getLastName() since the fields themselves are private in Person
    @Override
    public String getDisplayName() {
        return "Student: " + getFirstName() + " " + getLastName();
    }
}
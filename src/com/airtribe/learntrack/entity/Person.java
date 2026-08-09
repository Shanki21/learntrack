package com.airtribe.learntrack.entity;

// Base class for the inheritance hierarchy — Student (and optionally Trainer) will extend this
public class Person {

    // Private fields = encapsulation. Nothing outside this class can touch these directly.
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    // Parameterized constructor — sets all fields at the time the object is created
    public Person(String id, String firstName, String lastName, String email) {
        this.id = id;               // 'this.id' = the field, 'id' = the parameter
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters — controlled read access to private fields
    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    // Setters — controlled write access to private fields
    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return firstName + " " + lastName;
    }

}
package com.example.btn7.model;

public class Student {
    private long id;
    private String nameClass;
    private String nameStudent;

    public Student(long id, String nameClass, String nameStudent) {
        this.id = id;
        this.nameClass = nameClass;
        this.nameStudent = nameStudent;
    }

    public String getNameClass() {
        return nameClass;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public long getId() {
        return id;
    }
}

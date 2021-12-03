package com.example.courseregistration1;

class Course {
    int id;
    String Course;
    int hoursPerWeek;
    int fees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(int hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public Course(int id, String course, int hoursPerWeek, int fees) {
        this.id = id;
        Course = course;
        this.hoursPerWeek = hoursPerWeek;
        this.fees = fees;
    }
}

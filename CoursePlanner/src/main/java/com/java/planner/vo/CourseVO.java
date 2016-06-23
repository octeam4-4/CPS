/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.planner.vo;

import java.util.List;

/**
 *
 * @author sku263
 */
public class CourseVO {
    
    private String number;
    private String name;
    private String description;
    private String numberOfHours;
    private String capacity;
    private String preCourses;
    private String semesters; 
    String availbleInfall;
    String availableInSummer;
    String availableInSpring;
    String teachers;

    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }

    public String getAvailbleInfall() {
        return availbleInfall;
    }

    public void setAvailbleInfall(String availbleInfall) {
        this.availbleInfall = availbleInfall;
    }

    public String getAvailableInSummer() {
        return availableInSummer;
    }

    public void setAvailableInSummer(String availableInSummer) {
        this.availableInSummer = availableInSummer;
    }

    public String getAvailableInSpring() {
        return availableInSpring;
    }

    public void setAvailableInSpring(String availableInSpring) {
        this.availableInSpring = availableInSpring;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(String numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getPreCourses() {
        return preCourses;
    }

    public void setPreCourses(String preCourses) {
        this.preCourses = preCourses;
    }

    public String getSemesters() {
        return semesters;
    }

    public void setSemesters (String semesters) {
        this.semesters = semesters;
    }
    
}

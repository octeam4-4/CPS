/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.planner.vo;

import java.util.List;
import java.util.Map;

/**
 *
 * @author sku263
 */
public class ScheduleVO {
    
    
    String term;
    String degree;
    String semester;
    Map<String,Integer> courses;
    String capacity;
    String studentsCount;
    Map<String,List<SectionVO>> sections;
    Map<String,List<FacultyVO>> faculty;

    public Map<String, Integer> getCourses() {
        return courses;
    }

    public void setCourses(Map<String, Integer> courses) {
        this.courses = courses;
    }

    public Map<String, List<FacultyVO>> getFaculty() {
        return faculty;
    }

    public void setFaculty(Map<String, List<FacultyVO>> faculty) {
        this.faculty = faculty;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Map<String,Integer> getCourse() {
        return courses;
    }

    public void setCourse(Map<String,Integer> courses) {
        this.courses = courses;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(String studentsCount) {
        this.studentsCount = studentsCount;
    }

    public Map<String,List<SectionVO>> getSections() {
        return sections;
    }

    public void setSections(Map<String,List<SectionVO>> sections) {
        this.sections = sections;
    }
    
    
}

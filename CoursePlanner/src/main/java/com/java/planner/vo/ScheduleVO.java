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
    DegreeVO degree;
    List<String> roomList;
    String semester;
   
    String capacity;
    String studentsCount;

    public List<String> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<String> roomList) {
        this.roomList = roomList;
    }
    Map<String,List<SectionVO>> sections;
    Map<String,List<FacultyVO>> faculty;

   

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

    public DegreeVO getDegree() {
        return degree;
    }

    public void setDegree(DegreeVO degree) {
        this.degree = degree;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
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

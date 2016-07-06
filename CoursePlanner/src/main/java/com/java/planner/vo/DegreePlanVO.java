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
public class DegreePlanVO {
    
    
            String degreeCode;
            String department;
            String electiveHrs;
            String requiredHrs;
            String eCourseAppended;
            String rCoursesAppended;
            List<String> electiveCourses;
            List<String> requiredCourses;

            
    public String geteCourseAppended() {
				return eCourseAppended;
			}

			public void seteCourseAppended(String eCourseAppended) {
				this.eCourseAppended = eCourseAppended;
			}

			public String getrCoursesAppended() {
				return rCoursesAppended;
			}

			public void setrCoursesAppended(String rCoursesAppended) {
				this.rCoursesAppended = rCoursesAppended;
			}

	public String getDegreeCode() {
        return degreeCode;
    }

    public void setDegreeCode(String degreeCode) {
        this.degreeCode = degreeCode;
    }

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getElectiveHrs() {
		return electiveHrs;
	}

	public void setElectiveHrs(String electiveHrs) {
		this.electiveHrs = electiveHrs;
	}

	public String getRequiredHrs() {
		return requiredHrs;
	}

	public void setRequiredHrs(String requiredHrs) {
		this.requiredHrs = requiredHrs;
	}

	public List<String> getElectiveCourses() {
		return electiveCourses;
	}

	public void setElectiveCourses(List<String> electiveCourses) {
		this.electiveCourses = electiveCourses;
	}

	public List<String> getRequiredCourses() {
		return requiredCourses;
	}

	public void setRequiredCourses(List<String> requiredCourses) {
		this.requiredCourses = requiredCourses;
	}
    
}
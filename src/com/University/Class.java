package com.University;

import java.util.Objects;

public class Class {
    private int classId = 1;
    private static int classIdCounter = 1;
    private Professor professor;
    private String course;
    private int units;

    public Class(String course,int units){
        this.course = course;
        this.units = units;
        this.professor = null;
        this.classId = classIdCounter++;
    }

    public int getUnits() {
        return units;
    }

    public String getCourse() {
        return course;
    }

    public int getClassId() {
        return classId;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor() {
        this.professor = professor;
    }
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void edit(String course, int units){
        this.course = course;
        this.units = units;
    }

    @Override
    public String toString() {
        if(professor != null) {
            return "Class ID = " + classId +
                    " , Class Professor = " + professor.getFullName() +
                    " , Course Name = " + course +
                    " , Units = " + units
                    ;
        }
        else{
            return "Class ID = " + classId +
                    " , Course Name : " + course +
                    " , Units : " + units
                    ;
        }
    }

    public boolean isNull(Objects object){
        if(isNull(object)){
            return true;
        }
        return false;
    }

}

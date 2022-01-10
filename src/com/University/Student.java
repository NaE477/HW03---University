package com.University;

import java.util.ArrayList;

public class Student extends Person{
    private int studentId = 1;
    private static int studentIdCounter = 1;
    private ArrayList<Class> classes;
    private ArrayList<Class> passedClasses;
    private ArrayList<Double> grades;
    private int units;

    public Student(String fullName, int nationalCode,String username,String password) {
        super.setFullName(fullName);
        super.setNationalCode(nationalCode);
        super.setUsername(username);
        super.setPassword(password);
        this.studentId = studentIdCounter++;
    }

    public void setGrades(int classId,double grade){
        passedClasses.add(classes.get(getClassByID(classId)));
        classes.remove(getClassByID(classId));
        grades.add(grade);
    }

    public int getClassByID(int classId){
        for(Class classes : classes){
            if(classes.getClassId() == classId){
                return this.classes.indexOf(classes);
            }
        }
        return -1;
    }

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
        passedClasses = new ArrayList<>();
        grades = new ArrayList<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public void edit(String fullName,int nationalCode,String username,String password){
        super.setFullName(fullName);
        super.setNationalCode(nationalCode);
        super.setUsername(username);
        super.setPassword(password);
    }

    public double getGradesFinal() {
        double grades = 0;
        double output = 0;
        if(this.grades != null) {
            for (Double classGrades : this.grades) {
                grades += classGrades;
            }
            output = grades / getUnits();
        }
        return output;
    }

    public ArrayList<Double> getGrades() {
        return grades;
    }

    public int getUnits(){
        int units = 0;
        for(Class classes : classes){
            units += classes.getUnits();
        }
        return units;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public ArrayList<Class> getPassedClasses() {
        return passedClasses;
    }

    @Override
    public String toString() {
        return "Student ID = " + studentId +
                " , Fullname = " + getFullName() +
                " , Username = " + super.getUsername() +
                " , National Code = " + super.getNationalCode();
    }

}

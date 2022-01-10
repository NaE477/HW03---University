package com.University;

import java.util.ArrayList;
import java.util.Locale;

public class Professor extends Person{
    private int profId = 1;
    private static int profIdCounter = 1;
    private int salary;
    private boolean committee;
    private ArrayList<Class> profClasses = new ArrayList<>();

    public Professor(){
        this.profId = profIdCounter++;
    }

    public Professor(String fullName, int nationalCode, String username, String password, String committee, ArrayList<Class> classes){
        super.setFullName(fullName);
        super.setNationalCode(nationalCode);
        super.setUsername(username);
        super.setPassword(password);
        this.committee = checkCommittee(committee);
        profClasses.addAll(classes);
        this.profId = profIdCounter++;
    }
    public Professor(String fullName,int nationalCode,String username,String password,String committee){
        super.setFullName(fullName);
        super.setNationalCode(nationalCode);
        super.setUsername(username);
        super.setPassword(password);
        this.committee = checkCommittee(committee);
        this.profId = profIdCounter++;
    }

    private boolean checkCommittee(String committee){
        if(committee.equals("C")){
            return true;
        }
        else if(committee.equals("NC")){
            return false;
        }
        return false;
    }

    public int getProfId() {
        return profId;
    }

    public int getSalary() {
        if(committee){
            return courseUnitCalculator(this.profClasses) * 1000000 + 5000000;
        }
        else return courseUnitCalculator(this.profClasses) * 1000000;
    }
    private int courseUnitCalculator(ArrayList<Class> courses){
        int output = 0;
        for (Class classV : courses) {
            output += classV.getUnits();
        }
        return output;
    }

    public ArrayList<Class> getProfClasses() {
        return profClasses;
    }

    public void setCommittee(String committee) {
        this.committee = committeeStringChecker(committee);
    }

    public boolean committeeStringChecker(String cOrNc){
        if(cOrNc.toUpperCase(Locale.ROOT).equals("C")){
            return true;
        }
        else if(cOrNc.toUpperCase(Locale.ROOT).equals("NC")){
            return false;
        }
        return false;
    }
    public void setProfClasses(ArrayList<Class> profClasses) {
        this.profClasses = profClasses;
    }

    public String committeeString(boolean committee){
        if(committee){
            return "In Committee";
        }
        else return "Not In Committee";
    }
    public String classes(ArrayList<Class> classes){
        String output = "";
        if(classes.size() >0) {
            for (Class classV : classes) {
                output += classV.getCourse() + ",";
            }
            return output;
        }
        return "no classes for the professor yet";
    }

    public void edit(String fullName,int nationalCode,String username,String password,String committee,ArrayList<Class> classes){
        super.setFullName(fullName);
        super.setNationalCode(nationalCode);
        super.setUsername(username);
        super.setPassword(password);
        this.committee = committeeStringChecker(committee);
        this.setProfClasses(classes);
    }
    public void edit(String fullName,int nationalCode,String username,String password,String committee){
        super.setFullName(fullName);
        super.setNationalCode(nationalCode);
        super.setUsername(username);
        super.setPassword(password);
        this.committee = committeeStringChecker(committee);
    }
    @Override
    public String toString() {
        return  "Professor ID = " + profId +
                " , Fullname = " + getFullName() +
                " , National Code = " + getNationalCode() +
                " , salary = " + getSalary() +
                " , committee = " + committeeString(committee) +
                " , profClasses = " + classes(profClasses)
                ;
    }
}

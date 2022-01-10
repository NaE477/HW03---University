package com.University;

public class Employee extends Person {
    private int empId = 1;
    private static int empIdCounter = 1;
    private int salary;

    public Employee(String fullName,int nationalCode,int salary,String username,String password){
        this.salary = salary;
        super.setFullName(fullName);
        super.setNationalCode(nationalCode);
        super.setUsername(username);
        super.setPassword(password);
        this.empId = empIdCounter++;
    }


    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee ID = " + empId +
                " , Salary = " + salary;
    }
}

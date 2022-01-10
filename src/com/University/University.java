package com.University;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import static java.util.Objects.isNull;

public class University {
    Scanner scanner = new Scanner(System.in);
    Utilities utils = new Utilities();

    private final ArrayList<Student> studentList;
    private final ArrayList<Employee> employeeList;
    private final ArrayList<Professor> professorList;
    private final ArrayList<Class> classList;

    public University() {
        this.studentList = new ArrayList<>();
        this.employeeList = new ArrayList<>();
        this.professorList = new ArrayList<>();
        this.classList = new ArrayList<>();

        Employee admin = new Employee("adimn",12345,1000000,"admin","admin");
        employeeList.add(admin);
    }

    public void loginRole() throws InterruptedException {
        while (true) {
            System.out.println("""
                    Roles in the app include:
                    1-Employee
                    2-Professor
                    3-Student
                    Enter the role you want to login as or enter 0 to exit:\s""");
            String opt = scanner.nextLine();
            switch (opt) {
                case "1" -> {
                    login(employeeList);
                }
                case "2" -> {
                    login(professorList);
                }
                case "3" -> {
                    login(studentList);
                }
                case "0" -> Main.entry();
                default -> {
                    System.out.println("Try a number between 1-3");
                    Thread.sleep(700);
                }
            }
        }
    }

    private void login(ArrayList list) throws InterruptedException {
        System.out.println("Enter Username: ");
        String username = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();
        try {
            if (authenticate(username, password, list)) {
                System.out.print("Welcome to the uni");
                Object obj = list.get(0);
                if (obj instanceof Employee) {
                    System.out.print(" Employee!\n");
                    Thread.sleep(800);
                    employeeSection(username);
                } else if (obj instanceof Professor) {
                    System.out.print(" Professor!\n");
                    Thread.sleep(800);
                    professorSection(username);
                } else if (obj instanceof Student) {
                    System.out.print(" Student!\n");
                    Thread.sleep(800);
                    studentSection(username);
                }
            } else {
                System.out.println("Wrong username/password. You'll be redirected to choosing role section.");
                loginRole();
            }
        } catch (NullPointerException e) {
            System.out.println("Empty User/Pass fields not accepted!");
            loginRole();
        }
    }

    //===============================START OF EMPLOYEE SECTION===============================
    private void employeeSection(String username) throws InterruptedException {
        while (true) {
            System.out.println("""
                    Your Options:
                    1-Create User or Course
                    2-Edit User or Course
                    3-Delete User or Course
                    4-Check Salary
                    0-Exit
                    """);
            String opt = scanner.nextLine();
            switch (opt) {
                case "1":
                    createSection();
                    break;
                case "2":
                    editSection();
                    break;
                case "3":
                    deleteSection();
                    break;
                case "4":
                    System.out.println(employeeSalary(username));
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Wrong input,try 1,2,0: ");
                    Thread.sleep(1000);
                    break;
            }
        }
    }

    private void createSection() throws InterruptedException {
        System.out.println("""
                What do you want to create?:
                1-Employee 2-Professor 3-Student 4-Course 0-Exit
                """);
        String objToCreate = scanner.nextLine();
        switch (objToCreate) {
            case "1":
                addEmployee();
                break;
            case "2":
                addProfessor();
                break;
            case "3":
                addStudent();
                break;
            case "4":
                addClass();
                break;
            case "0":
                return;
            default:
                System.out.println("Wrong option!");
                break;
        }
    }

    private String employeeSalary(String username) {
        for (Employee employee : employeeList) {
            if (employee.getUsername().equals(username)) {
                return "Your salary is: " + employee.getSalary();
            }
        }
        return "";
    }

    //Add by employee section
    private void addEmployee() throws InterruptedException {
        System.out.print("Enter Fullname:");
        String fullName = scanner.nextLine();
        System.out.print("National Code:");
        int nationalCode = nationalCodeReceiver(employeeList);
        System.out.print("Salary: ");
        int salary = utils.intReceiver();
        System.out.print("Username for the new Account: ");
        String employeeUsername = usernameReceiver(employeeList);
        System.out.print("Password for the new Account: ");
        String password = scanner.nextLine();
        Employee employee = new Employee(fullName, nationalCode, salary, employeeUsername, password);
        employeeList.add(employee);
        objectAddedMessage("Employee");
    }

    private void addProfessor() throws InterruptedException {
        Professor professor = new Professor();
        System.out.print("Enter Fullname: ");
        String fullName = scanner.nextLine();
        System.out.print("National Code: ");
        int nationalCode = nationalCodeReceiver(professorList);
        System.out.print("Professor's ");
        String profUsername = utils.usernameReceiver(professorList, "Username");
        System.out.print("Professor's Password: ");
        String password = scanner.nextLine();
        System.out.print("Committee or non committee(Define by C or NC): ");
        String committee = scanner.nextLine().toUpperCase(Locale.ROOT);
        Thread.sleep(900);
        ArrayList<Class> classes = professorClassAdder(professor);
        if (classes.size() > 0) {
            professor.edit(fullName, nationalCode, profUsername, password, committee, classes);
            professorList.add(professor);
            for (Class classV : classes) {
                classList.get(classV.getClassId() - 1).setProfessor(professor);
            }
        } else {
            System.out.println("No classes were added yet. Add courses for the Professor later.");
            professor.edit(fullName, nationalCode, profUsername, password, committee);
            professorList.add(professor);
        }
        objectAddedMessage("Professor");
    }

    private void addStudent() throws InterruptedException {
        System.out.print("Enter Fullname: ");
        String fullName = scanner.nextLine();
        System.out.print("National Code: ");
        int nationalCode = nationalCodeReceiver(studentList);
        System.out.print("Student's ");
        String studentUsername = utils.usernameReceiver(studentList, "Username");
        System.out.print("Student's Password: ");
        String password = scanner.nextLine();
        Student student = new Student(fullName, nationalCode, studentUsername, password);
        studentList.add(student);

        objectAddedMessage("Student");
    }

    private void addClass() throws InterruptedException {
        System.out.print("Enter Course name: ");
        String course = scanner.nextLine();
        System.out.print("Units: ");
        int units = utils.intReceiver();
        Class classV = new Class(course, units);
        classList.add(classV);
        objectAddedMessage("Class");
    }

    //Edit by employee Section
    private void editSection() throws InterruptedException {
        System.out.println("""
                What do you want to edit?:
                1-Employee 2-Professor 3-Student 4-Class 0-Exit""");
        String opt = scanner.nextLine();
        switch (opt) {
            case "1":
                editEmployee();
                break;
            case "2":
                editProfessor();
                break;
            case "3":
                editStudent();
            case "4":
                editClass();
            case "0":
                return;
            default:
                System.out.println("Choose a number between 0 and 4.");
        }
        editSection();
    }

    private void editEmployee() throws InterruptedException {
        while (true) {
            System.out.println("Which employee do you want to edit?:");
            Thread.sleep(700);
            viewEmployees();
            System.out.println("-1- Exit");
            int empId = utils.intReceiver();
            if (empId != -1 && employeeList.get(empId - 1) != null) {
                System.out.println("Enter the salary for the employee: ");
                int newSalary = utils.intReceiver();
                employeeList.get(empId - 1).setSalary(newSalary);
                System.out.println("Employee's Salary was changed");
            } else if (empId == -1) {
                break;
            } else if (employeeList.get(empId - 1) == null) {
                System.out.println("Employee does not exist!");
            }
        }
    }

    private void editProfessor() throws InterruptedException {
        while (true) {
            System.out.println("Which professor do you want to edit?Choose by ID:");
            Thread.sleep(700);
            viewProfessors();
            System.out.println("-1- Exit");
            int profId = utils.intReceiver();
            if (profId != -1 && professorList.get(profId - 1) != null) {

                System.out.print("Enter new fields for the professor: \n" +
                        "Fullname: ");
                String fullName = scanner.nextLine();
                System.out.print("National Code: ");
                int nationalCode = nationalCodeReceiver(professorList);
                System.out.print("Username: ");
                String profUsername = usernameReceiver(professorList);
                System.out.print("Password: ");
                String password = scanner.nextLine();
                System.out.print("Committee(C or NC): ");
                String committee = scanner.nextLine();
                ArrayList<Class> classes = professorClassAdder(professorList.get(profId - 1));

                professorList.get(profId - 1).edit(fullName, nationalCode, profUsername, password, committee, classes);


                System.out.println("-----------------------\nProfessor was edited successfully.\n-------------------");
                Thread.sleep(1000);
            } else if (profId == -1) {
                break;
            } else if (professorList.get(profId - 1) == null) {
                System.out.println("Professor does not exist!");
            }
        }
    }

    private void editStudent() throws InterruptedException {
        while (true) {
            System.out.println("Which student do you want to edit?Choose by ID: ");
            Thread.sleep(700);
            viewStudents();
            System.out.println("-1- Exit");
            int studentId = utils.intReceiver();
            if (studentId != -1 && studentList.get(studentId - 1) != null) {

                System.out.print("Enter new fields for the student: \n" +
                        "Fullname: ");
                String fullName = scanner.nextLine();
                System.out.print("National Code: ");
                int nationalCode = nationalCodeReceiver(studentList);
                System.out.print("Username: ");
                String studentUsername = usernameReceiver(studentList);
                System.out.print("Password: ");
                String password = scanner.nextLine();

                studentList.get(studentId - 1).edit(fullName, nationalCode, studentUsername, password);

                System.out.println("Student was edited successfully.");
            } else if (studentId == -1) {
                break;
            } else if (studentList.get(studentId - 1) != null) {
                System.out.println("Student does not exist!");
            }
        }
    }

    private void editClass() throws InterruptedException {
        while (true) {
            System.out.println("Which Class do you want to edit?Choose by ID: ");
            Thread.sleep(700);
            viewClasses();
            System.out.println("-1- Exit");
            int classId = utils.intReceiver();
            if (classId != -1 && classList.get(classId - 1) != null) {
                System.out.print("Enter new fields for the class: \n" +
                        "Course Name: ");
                String courseName = scanner.nextLine();
                System.out.println("Units: ");
                int units = utils.intReceiver();
                classList.get(classId - 1).edit(courseName, units);
            }
        }
    }

    //delete by employee section
    private void deleteSection() throws InterruptedException {
        System.out.println("""
                What do you want to delete?:
                1-Employee 2-Professor 3-Student 4-Course 0-Exit
                """);
        String objToCreate = scanner.nextLine();
        switch (objToCreate) {
            case "1":
                deleteEmployee();
                break;
            case "2":
                deleteProfessor();
                break;
            case "3":
                deleteStudent();
                break;
            case "4":
                deleteClass();
                break;
            case "0":
                return;
            default:
                System.out.println("Wrong option!");
                break;
        }
    }

    private void deleteEmployee() throws InterruptedException {
        while (true) {
            System.out.println("Which Employee do you want to delete?Choose by ID: ");
            Thread.sleep(700);
            viewEmployees();
            System.out.println("-1- Exit");
            int employeeId = utils.intReceiver();
            if (employeeId != -1 && employeeList.get(employeeId - 1) != null) {
                employeeList.remove(employeeId - 1);
            } else if (employeeId == -1) {
                return;
            } else if (professorList.get(employeeId - 1) == null) {
                System.out.println("Employee does not exist!");
            }
        }
    }

    private void deleteProfessor() throws InterruptedException {
        while (true) {
            System.out.println("Which Professor do you want to delete?Choose by ID: ");
            Thread.sleep(700);
            viewProfessors();
            System.out.println("-1- Exit");
            int profId = utils.intReceiver();
            if (profId != -1 && employeeList.get(profId - 1) != null) {
                professorList.remove(profId - 1);
            } else if (profId == -1) {
                return;
            } else if (professorList.get(profId - 1) == null) {
                System.out.println("Professor does not exist!");
            }
        }
    }

    private void deleteStudent() throws InterruptedException {
        while (true) {
            System.out.println("Which Student do you want to delete?Choose by ID: ");
            Thread.sleep(700);
            viewStudents();
            System.out.println("-1- Exit");
            int studentId = utils.intReceiver();
            if (studentId != -1 && studentList.get(studentId - 1) != null) {
                studentList.remove(studentId - 1);
            } else if (studentId == -1) {
                return;
            } else if (studentList.get(studentId - 1) == null) {
                System.out.println("Student does not exist!");
            }
        }
    }

    private void deleteClass() throws InterruptedException {
        while (true) {
            System.out.println("Which Class do you want to delete?Choose by ID: ");
            Thread.sleep(700);
            viewClasses();
            System.out.println("-1- Exit");
            int classId = utils.intReceiver();
            if (classId != -1 && classList.get(classId - 1) != null) {
                classList.remove(classId - 1);
            } else if (classId == -1) {
                break;
            } else if (classList.get(classId - 1) == null) {
                System.out.println("Class does not exist!");
            }
        }
    }
    //===============================END OF EMPLOYEE SECTION===============================//


    //===============================START OF STUDENT SECTION===============================//
    public void studentSection(String username) throws InterruptedException {
        while (true) {
            System.out.println("""
                    Your options as a student:
                    1-Account Details
                    2-View Classes
                    3-Unit Choose
                    4-View Grades
                    0-Exit
                    """);
            String opt = scanner.nextLine();
            switch (opt) {
                case "1":
                    viewStudentProfile(username);
                    break;
                case "2":
                    viewClassesForStudent();
                    break;
                case "3":
                    unitChoose(username);
                    break;
                case "4":
                    viewClassAndGradesByClass(username);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Wrong input! try a number between 0 and 4");
                    Thread.sleep(1000);
            }
        }
    }

    private void viewStudentProfile(String username) throws InterruptedException {
        for (Student student : studentList) {
            if (student.getUsername().equals(username)) {
                System.out.println(student);
                Thread.sleep(1000);
            }
        }
    }

    private void unitChoose(String username) throws InterruptedException {
        int studentIndex = findStudentIDByUsername(username);
        int unitThreshold = unitThreshold(studentIndex);
        int unitsPicked = unitsPicked(studentIndex);
        ArrayList<Class> pickedClasses = new ArrayList<>();
        while (true) {
            if (unitsPicked < unitThreshold) {
                System.out.println("Choose the classes you want to pick by ID:");
                viewClassesForStudent();
                System.out.println("-1- Exit");
                int classId = utils.intReceiver();
                if (classId != -1) {
                    unitsPicked += classList.get(classId - 1).getUnits();
                    if (unitsPicked > unitThreshold) {
                        System.out.println("choose a class with less units.");
                        Thread.sleep(1000);
                        unitsPicked -= classList.get(classId - 1).getUnits();
                    } else if (unitsPicked == unitThreshold) {
                        pickedClasses.add(classList.get(classId - 1));
                        System.out.println("Unit added");
                        System.out.println("You have the reached your unit selection threshold.");
                        break;
                    } else if (pickedClasses.contains(classList.get(classId - 1))) {
                        System.out.println("You have picked this class.");
                        unitsPicked -= classList.get(classId - 1).getUnits();
                    } else if (studentList.get(studentIndex).getClasses() != null) {
                        if (studentList.get(studentIndex).getClasses().contains(classList.get(classId - 1))) {
                            System.out.println("You have picked this class already.");
                            unitsPicked -= classList.get(classId - 1).getUnits();
                        }
                    } else if (studentList.get(studentIndex).getPassedClasses() != null) {
                        if (studentList.get(studentIndex).getPassedClasses().contains(classList.get(classId - 1))) {
                            System.out.println("You have passed this course before.");
                            unitsPicked -= classList.get(classId - 1).getUnits();
                        }
                    } else {
                        pickedClasses.add(classList.get(classId - 1));
                        System.out.println("Unit added");
                        Thread.sleep(1000);
                    }
                } else {
                    break;
                }
            } else {
                System.out.println("Your unit picking Threshold was reached!");
                Thread.sleep(1000);
                break;
            }
        }
        studentList.get(studentIndex).setClasses(pickedClasses);
        System.out.println("Units were added!");
    }

    private void viewClassAndGradesByClass(String username) {
        int index = findStudentIDByUsername(username);
        if (!isNull(studentList.get(index).getPassedClasses()) && studentList.get(index).getPassedClasses().size() > 0) {
            for (int i = 0; i < studentList.get(index).getPassedClasses().size(); i++) {
                System.out.println("Course: " + studentList.get(index).getPassedClasses().get(i) + "   Grade: " + studentList.get(index).getGrades().get(i));
            }
        } else System.out.println("No grade is added yet.");
    }

    //===============================END OF STUDENT SECTION===============================//


    //===============================START OF PROFESSOR SECTION===============================//

    private void professorSection(String username) throws InterruptedException {
        while (true) {
            System.out.println("""
                    Your Options:
                    1-Account Details
                    2-Insert Grades
                    3-View Salary Receipt
                    0-Exit
                    """);
            String opt = scanner.nextLine();
            switch (opt) {
                case "1":
                    viewProfessorAccount(username);
                    break;
                case "2":
                    insertGrades(username);
                    break;
                case "3":
                    professorSalary(username);
                    break;
                case "4":
                    System.out.println(employeeSalary(username));
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Wrong input,try 1,2,0: ");
                    Thread.sleep(1000);
                    break;
            }
        }
    }

    private void viewProfessorAccount(String username) throws InterruptedException {
        for (Professor professor : professorList) {
            if (professor.getUsername().equals(username)) {
                System.out.println(professor);
            }
        }
    }

    private void insertGrades(String username) throws InterruptedException {
        int profIndex = findProfessorIDByUsername(username);
        for (Class classV : professorList.get(profIndex).getProfClasses()) {
            System.out.println(classV);
        }
        System.out.print("Select the class ID you want to insert grades for: ");
        Thread.sleep(1000);
        int classID = utils.intReceiver();
        Class temp = classList.get(classID - 1);
        System.out.println("Now select the Student ID you want to insert grades for: ");
        for (Student student : studentList) {
            if (!isNull(student.getClasses())) {
                if (student.getClasses().contains(temp)) {
                    System.out.println(student);
                }
            }
        }
        int studentID = utils.intReceiver();
        System.out.print("Grade: ");
        double grade = utils.doubleReceiver();
        studentList.get(studentID - 1).setGrades(classID, grade);
    }

    private void professorSalary(String username) throws InterruptedException {
        for (Professor professor : professorList) {
            if (professor.getUsername().equals(username)) {
                System.out.println("Your salary is: " + professor.getSalary());
                Thread.sleep(1000);
            }
        }
    }

    //===============================END OF PROFESSOR SECTION===============================//

    private String viewGrades(String username) {
        int studentIndex = findStudentIDByUsername(username);
        if (studentList.get(studentIndex).getGrades() != null) {
            return studentList.get(studentIndex).getGrades().toString();
        } else return null;
    }

    private void viewClasses() throws InterruptedException {
        for (Class classIn : classList) {
            System.out.println(classIn);
            Thread.sleep(500);
        }
    }

    private void viewClassesForStudent() throws InterruptedException {
        for (Class classIn : classList) {
            if (!isNull(classIn.getProfessor())) {
                System.out.println(classIn);
                Thread.sleep(500);
            }
        }
    }

    private void viewEmployees() throws InterruptedException {
        for (Employee employee : employeeList) {
            System.out.println(employee.toString());
            Thread.sleep(500);
        }
    }

    private void viewProfessors() throws InterruptedException {
        for (Professor professor : professorList) {
            System.out.println(professor.toString());
            Thread.sleep(500);
        }
    }

    private void viewStudents() throws InterruptedException {
        for (Student student : studentList) {
            System.out.println(student.toString());
            Thread.sleep(500);
        }
    }

    private int findStudentIDByUsername(String username) {
        for (Student student : studentList) {
            if (student.getUsername().equals(username)) {
                return studentList.indexOf(student);
            } else return -1;
        }
        return -1;
    }

    private int findProfessorIDByUsername(String username) {
        for (Professor professor : professorList) {
            if (professor.getUsername().equals(username)) {
                return professorList.indexOf(professor);
            } else return -1;
        }
        return -1;
    }

    private int unitsPicked(int index) {
        int unitsPicked = 0;
        if (studentList.get(index).getClasses() != null) {
            for (Class classes : studentList.get(index).getClasses()) {
                unitsPicked += classes.getUnits();
            }
        }
        return unitsPicked;
    }

    private ArrayList<Class> professorClassAdder(Professor professor) throws InterruptedException {
        ArrayList<Class> tempClasses = new ArrayList<>();
        System.out.println("Choose the class ID to add for professor: ");
        Thread.sleep(800);
        viewClasses();
        System.out.println("-1- exit");
        while (true) {
            int classId = utils.intReceiver();
            if (classId != -1) {
                if (classExists(classId) && !tempClasses.contains(classList.get(classId - 1)) && isNull(classList.get(classId - 1).getProfessor())) {
                    tempClasses.add(classList.get(classId - 1));
                    classList.get(classId - 1).setProfessor(professor);
                } else System.out.println("Class ID does not exist or has already a professor!");
            } else break;
        }
        return tempClasses;
    }


    private boolean classExists(int classId) {
        for (Class classV : classList) {
            if (classV.getClassId() == classId) {
                return true;
            }
        }
        return false;
    }

    private int unitThreshold(int index) {
        if (studentList.get(index).getGradesFinal() >= 18) {
            return 24;
        } else if (studentList.get(index).getGradesFinal() < 18) {
            return 20;
        }
        return 20;
    }

    private boolean authenticate(String username, String password, ArrayList<Person> list) {
        for (Person person : list) {
            if (person.getUsername().equals(username)) {
                String validUser = person.getUsername();
                String validPass = person.getPassword();
                return validUser.equals(username) && validPass.equals(password);
            }
        }
        return false;
    }

    private int nationalCodeReceiver(ArrayList list) {
        while (true) {
            try {
                int temp = utils.intReceiver();
                if (!ifNCodeExists(temp, list)) {
                    return temp;
                } else {
                    System.out.println("This National code already exists!\n" +
                            "Try another one that does not exist:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a Number!");
                nationalCodeReceiver(list);
            } catch (NullPointerException e) {
                System.out.println("Enter Something!");
                nationalCodeReceiver(list);
            }
        }
    }

    private String usernameReceiver(ArrayList list) {
        while (true) {
            try {
                String temp = scanner.nextLine();
                if (!ifUsernameExists(temp, list)) {
                    return temp;
                } else {
                    System.out.print("This Username already exists!\n" +
                            "Try another one that does not exist: ");
                }
            } catch (NullPointerException e) {
                System.out.println("Enter something!");
                usernameReceiver(list);
            }
        }
    }

    private boolean ifNCodeExists(int nationalCode, ArrayList list) {
        for (Object person : list) {
            if (((Person) person).getNationalCode() == nationalCode) {
                return true;
            }
        }
        return false;
    }

    private boolean ifUsernameExists(String username, ArrayList list) {
        for (Object person : list) {
            if (((Person) person).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private void objectAddedMessage(String object) throws InterruptedException {
        String message = object + " was added.";
        Thread.sleep(900);
        utils.printGreen(message);
        Thread.sleep(900);
    }
}

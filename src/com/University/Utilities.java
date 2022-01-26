package com.University;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Utilities {

    Scanner scanner = new Scanner(System.in);

    public String usernameReceiver(ArrayList target, String tag){
        Scanner scanner = new Scanner(System.in);
        while(true){
        System.out.print(tag + ": ");
        String input = scanner.nextLine();
            if(!checkExistence(target,input)){
                return input;
                }
            else {
                System.out.println("This " + tag + " Already Exists! Try another one: ");
            }
        }
    }

    public boolean checkExistence(ArrayList<Person> target,String username){
        for (Person person : target) {
                if (person.getUsername().equals(username)) {
                    return true;
            }
        }
        return false;
    }

    public String regexAdder(String regex,String tag){
        while(true){
        System.out.print(tag + ": ");
        Scanner scanner = new Scanner(System.in);
        String output = null;
        String input = scanner.nextLine();
            if(checkRegex(input,regex)){
                return input;
            }
            else{
                System.out.println("Wrong " + tag + " Format. Enter a Correct " + tag + " Format:");
            }
        }
    }

    public boolean checkRegex(String input, String regexPattern){
        return Pattern.compile(regexPattern).matcher(input).matches();
    }

    public int intReceiver(){
        while(true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("You should enter a number here:");
            }
        }
    }
    public Double doubleReceiver(){
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("You should enter a number here:");
            }
        }
    }

    void printRed(String input){
        System.out.println("------------------------------\n" + ANSI_RED + input + ANSI_RESET + "\n------------------------------");
    }
    void printGreen(String input){
        System.out.println("------------------------------\n" + ANSI_GREEN + input + ANSI_RESET + "\n------------------------------");
    }

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_YELLOW = "\u001B[33m";
}

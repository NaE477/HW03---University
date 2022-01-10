package com.University;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Utilities {

    Scanner scanner = new Scanner(System.in);

    public String usernameReceiver(ArrayList target, String tag){
        System.out.print(tag + ": ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String output = null;
        while(true){
            if(checkExistence(target,input)){
                System.out.println("This " + tag + " Already Exists! Try another one: ");
                usernameReceiver(target,tag);
            }
            else {
                output = input;
            }
            break;
        }
        return output;
    }

    public boolean checkExistence(ArrayList<Person> target,String username){
        boolean flag = false;
        for (Person person : target) {
                if (person.getUsername().equals(username)) {
                    flag = true;
                    break;
            }
        }
        return flag;
    }

    public String regexAdder(String regex,String tag){
        System.out.print(tag + ": ");
        Scanner scanner = new Scanner(System.in);
        String output = null;
        String input = scanner.nextLine();
        while(true){
            if(checkRegex(input,regex)){
                output = input;
            }
            else{
                System.out.println("Wrong " + tag + " Format. Enter a Correct " + tag + " Format:");
                regexAdder(regex,tag);
            }
            break;
        }
        return output;
    }

    public boolean checkRegex(String input, String regexPattern){
        return Pattern.compile(regexPattern).matcher(input).matches();
    }

    public int intReceiver(){
        try{
            int output = Integer.parseInt(scanner.nextLine());
            return output;
        }
        catch (NumberFormatException e) {
            System.out.println("You should enter a number here:");
            intReceiver();
        }
        return 0;
    }
    public Double doubleReceiver(){
        try{
            double output = Double.parseDouble(scanner.nextLine());
            return output;
        }
        catch (NumberFormatException e) {
            System.out.println("You should enter a number here:");
            doubleReceiver();
        }
        return 0.0;
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

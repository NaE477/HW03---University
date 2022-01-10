package com.University;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static University uni = new University();


    public static void main(String[] args) throws InterruptedException {
        entry();
    }

    public static void entry() throws InterruptedException {
        try {
            System.out.println("Welcome to University App!\n" +
                "Enter L/l to login or E/e to exit: ");
            while(true) {

                String opt = scanner.nextLine().toUpperCase(Locale.ROOT);
                switch (opt) {
                    case "L" -> {
                        uni.loginRole();
                        return;
                    }
                    case "E" -> System.exit(0);
                    default -> {
                        System.out.println("Wrong input.\n" +
                                "Choose L/l or E/e:");
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Empty String not accepted.");
        }
    }
}

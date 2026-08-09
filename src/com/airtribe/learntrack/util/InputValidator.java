package com.airtribe.learntrack.util;

import java.util.Scanner;

public class InputValidator {

    private Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;   // signals "invalid input" back to Main, which can show an error and re-loop
        }
    }

    public String getNonEmptyString(String prompt) {
        String input = "";
        while (input.trim().isEmpty()) {
            System.out.print(prompt);
            input = scanner.nextLine();
        }
        return input;
    }
}

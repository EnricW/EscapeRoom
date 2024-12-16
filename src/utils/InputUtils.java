package utils;

import java.util.Scanner;

public class InputUtils {
    public static final Scanner scanner = new Scanner(System.in);

    public static int requestPositiveInteger(String prompt) {
        int value = -1;
        while (value <= 0) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value <= 0) {
                    System.out.println("Please enter a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
            }
        }
        return value;
    }

    public static double requestPositiveDouble(String prompt) {
        double value = -1;
        while (value <= 0) {
            System.out.print(prompt);
            try {
                value = Double.parseDouble(scanner.nextLine());
                if (value <= 0) {
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive number.");
            }
        }
        return value;
    }

    public static String requestString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    public static void closeScanner() {
        scanner.close();
    }
}

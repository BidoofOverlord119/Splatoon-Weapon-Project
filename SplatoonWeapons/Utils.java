package SplatoonWeapons;

import java.util.Scanner;

public class Utils {
    private static final Scanner input = new Scanner(System.in);

    public static String nextLine() {
        return input.nextLine();
    }

    public static int getInt() {
        return getInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static int getInt(int min) {
        return getInt(min, Integer.MAX_VALUE);
    }

    public static int getInt(int min, int max) {
        while (true) {
            try {
                int response = Integer.parseInt(input.nextLine());
                if (response >= min && response <= max) {
                    return response;
                } else {
                    if (min == Integer.MIN_VALUE) {
                        System.out.printf("Please choose a valid option. Must be at most %d.%n", max);
                    } else if (max == Integer.MAX_VALUE) {
                        System.out.printf("Please choose a valid option. Must be at least %d.%n", min);
                    } else {
                        System.out.printf("Please choose a valid option. Must be %d to %d.%n", min, max);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please try again.");
            }
        }
    }

    public static double getDouble() {
        return getDouble(-Double.MAX_VALUE, Double.MAX_VALUE);
    }

    public static double getDouble(double min) {
        return getDouble(min, Double.MAX_VALUE);
    }

    public static double getDouble(double min, double max) {
        while (true) {
            try {
                double response = Double.parseDouble(input.nextLine());
                if (response >= min && response <= max) {
                    return response;
                } else {
                    // this should never be reached if both are min and max value, so it doesn't need a case for that
                    if (min == -Double.MAX_VALUE) {
                        System.out.printf("Please choose a valid option. Must be at most %.2f.%n", max);
                    } else if (max == Double.MAX_VALUE) {
                        System.out.printf("Please choose a valid option. Must be at least %.2f.%n", min);
                    } else {
                        System.out.printf("Please choose a valid option. Must be %.2f to %.2f.%n", min, max);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please try again.");
            }
        }
    }
}

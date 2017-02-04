package utils;

import java.util.Scanner;

/**
 * Created by sebastian on 09.12.16.
 */
public class BotUtils {
    private static Scanner in = new Scanner(System.in);

    public static void printMessage(final String message) {
        System.out.println(message);
    }

    public static String getUserInput() {
        return in.nextLine();
    }
}

package utils;

import java.io.*;

import java.time.LocalDate;

import static utils.ScannerSingleton.getInputScanner;

public class Utils {

    public static String getStringInput(String message) {
        System.out.println(message);
        return getInputScanner().next().toLowerCase();
    }

    public static int getPositiveIntInput(String message) {
        int number;
        do {
            System.out.println(message);
            while (!getInputScanner().hasNextInt()) {
                System.out.println("This is not a number, please try again");
                getInputScanner().next();
            }
            number = getInputScanner().nextInt();
        } while (number < 0);
        return number;
    }

    public static LocalDate getDateInput(String message) {
        while (true) {
            String text = getStringInput(message);
            try {
                return LocalDate.parse(text);
            } catch (Exception e) {
                System.out.println("Not a valid date format (YYYY-MM-DD), please try again.");
                System.out.println("Error: " + e);
            }
        }
    }

    public static void addLineToTxtFile(String txtLine, String filePath) {
        try {
            FileWriter myWriter = new FileWriter(filePath, true);
            myWriter.write(txtLine + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred when writing to txt file.");
            e.printStackTrace();
        }
    }

    public static void createFile(String filePath) {
        try {
            File myFile = new File(filePath);
            myFile.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static boolean doesFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
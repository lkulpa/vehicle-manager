package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public static long getNumberOfLines(String filePath) {
        try {
            Path textFile = Paths.get(filePath);
            return Files.lines(textFile).count() + 1;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return 0;
    }

    public static List<String> getLinesFromFileAsList(String filePath) {
        BufferedReader reader;
        List<String> linesList = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();

            while (line != null) {
                linesList.add(line);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return linesList;
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

    public static void readTxtFile(String filePath) {
        try {
            File myFile = new File(filePath);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String textLine = myReader.nextLine();
                System.out.println(textLine);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred when reading the file.");
            e.printStackTrace();
        }
    }
}
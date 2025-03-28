package utils;

import java.util.List;
import java.util.Arrays;

public class Settings {

    private static final String databaseFilepath = "./data/database.ser";
    private static final String txtExportFilepath = "./data/export.txt";
    private static final List<String> vehicleTypes = Arrays.asList("car", "motorcycle");
    private static String currentMenu = "main";

    public static String getTxtExportFilepath() {
        return txtExportFilepath;
    }

    public static String getDatabaseFilepath() {
        return databaseFilepath;
    }

    public static String getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(String currentMenu) {
        Settings.currentMenu = currentMenu;
    }

    public static List<String> getVehicleTypes() {
        return vehicleTypes;
    }
}
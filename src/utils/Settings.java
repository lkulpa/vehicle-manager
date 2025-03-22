package utils;

public class Settings {

    private static final String databaseFilepath = "./data/database.ser";
    private static final String txtExportFilepath = "./data/export.txt";

    public static String getTxtExportFilepath() {
        return txtExportFilepath;
    }

    public static String getDatabaseFilepath() {
        return databaseFilepath;
    }
}
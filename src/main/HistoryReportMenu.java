package main;

import vehicles.Vehicle;

import java.util.Map;
import java.time.LocalDate;

import static utils.Utils.getDateInput;
import static utils.Utils.getStringInput;
import static utils.Helpers.getVehicleById;
import static utils.Settings.setCurrentMenu;

public class HistoryReportMenu {

    public static void chooseCommand() {
        String command = getStringInput(showCommands());
        switch (command) {
            case "show":
                showHistoryReport();
                break;
            case "add":
                addEventToHistoryReport();
                break;
            case "back":
                back();
                break;
            default:
                System.out.println("Invalid command entered, please try again.");
        }
    }

    private static String showCommands() {
        return """
                Available commands:
                Show - show vehicle's history report
                Add - add an event to the vehicle's history report
                Back - go back to main menu
                """;
    }

    private static void showHistoryReport() {
        Map<LocalDate, String> vehicleHistory = getVehicleById().getVehicleHistory();
        if (vehicleHistory.isEmpty()) {
            System.out.println("No history entries found");
        } else {
            System.out.println("--- Vehicle history ---");
            for (var entry : vehicleHistory.entrySet()) {
                LocalDate date = entry.getKey();
                String text = entry.getValue();
                System.out.println(date + ":\n" + text);
            }
            System.out.println("-----------------------\n");
        }
    }

    private static void addEventToHistoryReport() {
        Vehicle vehicle = getVehicleById();
        LocalDate date = getDateInput("Enter the date (yyyy-mm-dd):");
        String text = getStringInput("Enter info about the event:");
        vehicle.addEventToHistory(date, text);
        System.out.println("Entry added successfully to the history report!");
    }

    private static void back() {
        setCurrentMenu("main");
    }
}
package main;

import utils.Database;
import utils.Settings;

public class Main {

    public static void main(String[] args) {

        Database.deserializeVehicleObjects();

        while (true) {
            switch (Settings.getCurrentMenu()) {
                case "main" -> MainMenu.chooseCommand();
                case "simulation" -> SimulationMenu.chooseCommand();
                case "workshop" -> WorkshopMenu.chooseCommand();
                case "history" -> HistoryReportMenu.chooseCommand();
            }
        }
    }
}
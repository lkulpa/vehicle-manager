package main;

import utils.Database;

public class Main {

    public static void main(String[] args) {

        Database.deserializeVehicleObjects();

        while (true) {
            Interface.chooseCommand();
        }
    }
}
package util;

import main.Game;

public class OfficeLogic {

    private static int power = 999;
    private static int powerCounter = 0; // passive power consumption
    private static int powerCounter1 = 0; // nightly power consumption
    private static int powerUsage = 1;

    private static boolean blackout = false;

    private static final int powerConsumption1 = 8;
    private static final int powerConsumption2 = 6;
    private static final int powerConsumption3 = 4;
    private static final int powerConsumption4 = 3;
    private static final double powerConsumption5 = 2.5;

    static void reset() {

        power = 999;
        powerCounter = 0;
        powerCounter1 = 0;
        powerUsage = 1;

        blackout = false;

    }

    static void update() {

        if (!blackout) {
            updatePowerUsage();
            consumePower();

            if (power <= 0) {
                blackout = true;
                MessageHandler.addMessage("Blackout! You ran out of power!");
                MessageHandler.addMessage("You better hope the night is nearly over!");
            }

        }

    }

    private static void updatePowerUsage() {

        powerUsage = 1;

        if (DoorLogic.getLeftDoor()) {
            powerUsage++;
        } if (DoorLogic.getRightDoor()) {
            powerUsage++;
        } if (DoorLogic.getLeftLight()) {
            powerUsage++;
        } if (DoorLogic.getRightLight()) {
            powerUsage++;
        } if (CameraLogic.getCamStatus()) {
            powerUsage++;
        }

    }

    private static void consumePower() {

        powerCounter++;
        powerCounter1++;

        if ((powerCounter % Game.FPS) == 0) {
            power -= powerUsage;
            powerCounter = 0;
        }

        if ((NightLogic.getNight() == 1 && powerCounter1 >= powerConsumption1 * Game.FPS) ||
                (NightLogic.getNight() == 2 && powerCounter1 >= powerConsumption2 * Game.FPS) ||
                (NightLogic.getNight() == 3 && powerCounter1 >= powerConsumption3 * Game.FPS) ||
                (NightLogic.getNight() == 4 && powerCounter1 >= powerConsumption4 * Game.FPS) ||
                (NightLogic.getNight() >= 5 && powerCounter1 >= powerConsumption5 * Game.FPS)) {
            power -= powerUsage;
            powerCounter1 = 0;
        }

    }

    // when foxy hits door
    public static void decreasePower(int percentage) {
        power -= percentage * 10;
    }

    public static int getPower() { return power / 10; }
    public static int getPowerActual() { return power; }
    public static int getPowerUsage() { return powerUsage; }
    public static boolean getBlackout() { return blackout; }

}

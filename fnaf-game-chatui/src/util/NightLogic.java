package util;

import main.Game;
import main.KeyHandler;

public class NightLogic {

    private static int night = 1;
    private static int hour = 0;
    private static int nightCounter = 0;

    private static final String[] hours = new String[] {"12AM", "1AM", "2AM", "3AM", "4AM", "5AM", "6AM"};

    private static final int secondsPerHour = 70;

    static void reset() {

        hour = 0;
        nightCounter = 0;

    }

    static void update() {

        if (GameLogic.getGameState() == GameState.PLAY) {

            nightCounter++;
            for (int i = hours.length-1; i >= 0; i--) {
                if (nightCounter > i * secondsPerHour * Game.FPS) {
                    hour = i;
                    break;
                }
            }

        }

    }

    public static void checkWin() {
        if (hour == 6) {
            GameLogic.setUpWin();
        }
    }

    public static void setNight(int nightNum) { night = nightNum; }

    public static int getNight() {
        return night;
    }

    public static String getHour() {
        if (hour < hours.length)
            return hours[hour];
        return null;
    }

    public static int getNightCounter() {
        return nightCounter;
    }

    public static void speedUpCounter(KeyHandler keyHandler) {
        if (GameLogic.getGameState() == GameState.PLAY && keyHandler.space()) {
            nightCounter+=3;
        }
    }

}

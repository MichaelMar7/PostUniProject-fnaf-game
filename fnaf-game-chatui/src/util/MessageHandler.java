package util;

import main.Game;

import java.util.ArrayList;

public class MessageHandler {

    private static ArrayList<String> messages = new ArrayList<>();
    private static ArrayList<Integer> cooldown = new ArrayList<>();

    private static final int cooldownTimer = Game.FPS * 6;

    static void update() {

        for (int i = getNumberOfMessages()-1; i >= 0; i--) {

            decreaseCooldown(i);

            if (getCooldown(i) < 0) {
                messages.remove(i);
                cooldown.remove(i);
            }

        }

    }

    private static void decreaseCooldown(int i) {
        int currentCooldown = cooldown.get(i);
        cooldown.set(i,currentCooldown-1);
    }

    public static void addMessage(String message) {

        messages.addFirst(message);
        cooldown.addFirst(cooldownTimer);

    }

    public static String getMessage(int i) { return messages.get(i); }

    public static int getCooldown(int i) { return cooldown.get(i); }

    public static int getNumberOfMessages() { return messages.size(); }

    public static void clear() {

        messages.clear();
        cooldown.clear();

    }

}

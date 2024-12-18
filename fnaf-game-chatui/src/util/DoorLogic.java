package util;

public class DoorLogic {

    private static boolean leftDoor = false;
    private static boolean rightDoor = false;
    private static boolean leftLight = false;
    private static boolean rightLight = false;

    static void reset() {

        leftDoor = false;
        rightDoor = false;
        leftLight = false;
        rightLight = false;

    }

    public static void changeDoor(boolean left) {
        if (left)
            leftDoor = !leftDoor;
        else
            rightDoor = !rightDoor;
    }

    public static void changeLight(boolean left) {
        if (left)
            leftLight = !leftLight;
        else
            rightLight = !rightLight;
    }

    public static void closeDoors() {
        leftDoor = false;
        rightDoor = false;
    }

    public static void closeLights() {
        leftLight = false;
        rightLight = false;
    }

    public static boolean getLeftDoor() { return leftDoor; }

    public static boolean getRightDoor() { return rightDoor; }

    public static boolean getLeftLight() { return leftLight; }

    public static boolean getRightLight() { return rightLight; }

}

package util;

public class CameraLogic {

    private static boolean camOn = false;
    private static String currentCam = "cam1A";

    static void setUpCamera() {

        camOn = false;
        currentCam = "cam1A";

    }

    public static boolean getCamStatus() { return camOn; }

    public static String getCurrentCam() { return currentCam; }

    public static void changeCamStatus() {

        camOn = !camOn;

        if (camOn) {
            AnimatronicLogic.resetFoxyStall();
        }

    }

    public static void disableCam() {

        camOn = false;

    }

    public static void changeCam(String newCam) { currentCam = newCam; }

}

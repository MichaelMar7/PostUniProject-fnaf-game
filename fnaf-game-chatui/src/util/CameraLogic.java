package util;

import entity.animatronic.CamNum;

public class CameraLogic {

    private static boolean camOn = false;
    private static CamNum currentCam = CamNum.CAM1A;

    static void setUpCamera() {

        camOn = false;
        currentCam = CamNum.CAM1A;

    }

    public static boolean getCamStatus() { return camOn; }

    public static CamNum getCurrentCam() { return currentCam; }

    public static void changeCamStatus() {

        camOn = !camOn;

        if (camOn) {
            AnimatronicLogic.resetFoxyStall();
        }

    }

    public static void disableCam() {

        camOn = false;

    }

    public static void changeCam(CamNum newCam) { currentCam = newCam; }

}

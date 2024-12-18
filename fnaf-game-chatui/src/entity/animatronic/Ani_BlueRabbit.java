package entity.animatronic;

import util.DoorLogic;

public class Ani_BlueRabbit extends Animatronic {

    public static String name = "Bonnie";

    public Ani_BlueRabbit(int ai) {

        this.ai = ai;

        setUpCam();

    }

    @Override
    void setUpCam() {

        CamNode cam1A = new CamNode("cam1A");
        CamNode cam1B = new CamNode("cam1B");
        CamNode cam2A = new CamNode("cam2A");
        CamNode cam2B = new CamNode("cam2B");
        CamNode cam3 = new CamNode("cam3");
        CamNode cam5 = new CamNode("cam5");
        CamNode office = new CamNode("office");

        cam1A.setNextCam(cam1B, cam5);
        cam1B.setNextCam(cam2A, cam5);
        cam2A.setNextCam(cam2B, cam3);
        cam2B.setNextCam(cam3, office);
        cam3.setNextCam(cam2A, office);
        cam5.setNextCam(cam1B, cam2A);
        office.setNextCam(cam1B, cam1B);

        currentCam = cam1A;

    }

    @Override
    boolean attack() {

        return !DoorLogic.getLeftDoor();

    }

    @Override
    public String getName() {
        return name;
    }

}

package entity.animatronic;

import util.DoorLogic;

public class Ani_YellowChicken extends Animatronic {

    public static String name = "Chica";

    public Ani_YellowChicken(int ai) {

        this.ai = ai;

        setUpCam();

    }

    @Override
    void setUpCam() {

        CamNode cam1A = new CamNode("cam1A");
        CamNode cam1B = new CamNode("cam1B");
        CamNode cam4A = new CamNode("cam4A");
        CamNode cam4B = new CamNode("cam4B");
        CamNode cam6 = new CamNode("cam6");
        CamNode cam7 = new CamNode("cam7");
        CamNode office = new CamNode("office");

        cam1A.setNextCam(cam1B, cam1B);
        cam1B.setNextCam(cam6, cam7);
        cam4A.setNextCam(cam1B, cam4B);
        cam4B.setNextCam(cam4A, office);
        cam6.setNextCam(cam4A, cam7);
        cam7.setNextCam(cam4A, cam6);
        office.setNextCam(cam4A, cam4A);

        currentCam = cam1A;

    }

    @Override
    boolean attack() {

        return !DoorLogic.getRightDoor();

    }

    @Override
    public String getName() {
        return name;
    }

}

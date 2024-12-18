package entity.animatronic;

import util.DoorLogic;

public class Ani_BrownBear extends Animatronic {

    public static String name = "Freddy";

    public Ani_BrownBear(int ai) {

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

        cam1A.setNextCam(cam1B, cam1B);
        cam1B.setNextCam(cam7, cam7);
        cam7.setNextCam(cam6, cam6);
        cam6.setNextCam(cam4A, cam7);
        cam4A.setNextCam(cam4B, cam4B);
        cam4B.setNextCam(cam4A, cam4A);

        currentCam = cam1A;

    }

    @Override
    public void move() {

    }

    @Override
    boolean attack() {

        return false;

    }

    @Override
    public String getName() {
        return name;
    }

}

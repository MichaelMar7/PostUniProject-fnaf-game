package entity.animatronic;

import util.AnimatronicLogic;
import util.GameLogic;
import util.GameState;
import util.MessageHandler;

import java.util.Random;

public abstract class Animatronic {

    CamNode currentCam;
    int ai;

    abstract void setUpCam();

    public void move() {

        int moveOp = new Random().nextInt(20)+1;

        if (moveOp <= ai) {

            if (currentCam.getName().equals("office")) {

                if (attack()) {

                    AnimatronicLogic.setJumpscareAnimatronic(this);
                    GameLogic.setUpJumpscare();
                    return;

                }

            }

            int next = new Random().nextInt(2);
            currentCam = currentCam.nextCam[next];

        }

    }

    abstract boolean attack();

    public void buffAI() { ai++; }

    public boolean isAttacking() { return currentCam.getName().equals("office"); }

    public CamNode getCurrentCam() { return currentCam; }

    public abstract String getName();


    public void changeStalled(boolean stalled) {};

    public boolean getStalled() { return false; }

    public int getPhase() { return 0; }

}

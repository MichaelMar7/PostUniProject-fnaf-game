package entity.animatronic;

import main.Game;
import util.*;

import java.util.Random;

public class Ani_PirateFoxy extends Animatronic {

    public static String name = "Foxy";

    private static int phase = 1;
    private static boolean stalled = false;

    private static boolean cam2AChecked = false;
    private static int runningCounter = 0;
    private static int attackCounter = 0;

    private static int attackCounts = 0;

    public Ani_PirateFoxy(int ai) {

        this.ai = ai;

        setUpCam();

    }

    @Override
    void setUpCam() {

        phase = 1;
        stalled = false;
        attackCounter = 0;
        runningCounter = 0;
        cam2AChecked = false;

        currentCam = new CamNode("cam1C");

    }

    @Override
    public void move() {

        if (phase < 4 && NightLogic.getNightCounter() % AnimatronicLogic.getFoxyInterval() == 0) {

            int moveOp = new Random().nextInt(20)+1;

            if (moveOp <= ai) {

                if (!stalled) {
                    phase++;
                }

            }

        }

        if (phase >= 4) {

            // check if player looks at cam 2a (causes foxy running animation to play)
            if (CameraLogic.getCurrentCam().equals(CamNum.CAM2A) && CameraLogic.getCamStatus() && !cam2AChecked) {

                MessageHandler.addMessage(getName() + " is running down the hallway!");
                cam2AChecked = true;

            }

            if (cam2AChecked) { // increments running counter if cam 2a looked at
                runningCounter++;
            } else {            // increments attack counter if cam 2a doesn't get looked at
                attackCounter++;
            }

            if ((runningCounter >= 2 * Game.FPS || attackCounter >= 25 * Game.FPS) && attack()) {           // if counters reached and successful attack

                AnimatronicLogic.setJumpscareAnimatronic(this);
                GameLogic.setUpJumpscare();

            } else if ((runningCounter >= 2 * Game.FPS || attackCounter >= 25 * Game.FPS) && !attack())  {  // if counters reached and unsuccessful attack

                phase = new Random().nextInt(2)+1;
                attackCounter = 0;
                runningCounter = 0;
                cam2AChecked = false;

                attackCounts++;

                if (attackCounts == 1) {
                    OfficeLogic.decreasePower(1);
                } else if (attackCounts == 2) {
                    OfficeLogic.decreasePower(6);
                } else if (attackCounts >= 3) {
                    OfficeLogic.decreasePower(11);
                }

                AnimatronicLogic.resetFoxyStall();

            }

        }

    }

    @Override
    boolean attack() {

        return !DoorLogic.getLeftDoor();

    }

    @Override
    public String getName() {
        return name;
    }


    public void changeStalled(boolean stalled) { Ani_PirateFoxy.stalled = stalled; }

    public boolean getStalled() { return stalled; }

    public int getPhase() { return phase; }

}

package util;

import entity.animatronic.*;
import main.Game;

import java.util.Random;

public class AnimatronicLogic {

    static Animatronic bonnie, chica, foxy, freddy;
    private static Animatronic jumpscare = null;

    private static final int bonnieMoveInterval = 248; // 298
    private static final int chicaMoveInterval = 249; // 299
    private static final int foxyMoveInterval = 251; // 301
    private static final int freddyMoveInterval = 158; // 181

    private static int foxyStallCooldown = 0;
    private static int foxyStallCooldownThreshold;

    private static int blackoutCountdown = 0;
    private static int blackoutPhase = 1;

    private static boolean aiBuff1 = false;
    private static boolean aiBuff2 = false;
    private static boolean aiBuff3 = false;


    static void setUpAnimatronic() {

        switch (NightLogic.getNight()) {
            case 1:
                setAI(0, 0, 0, 0);
                break;
            case 2:
                setAI(3, 1, 1, 0);
                break;
            case 3:
                setAI(0, 5, 2, 1);
                break;
            case 4:
                setAI(2, 4, 6, new Random().nextInt(2)+1);
                break;
            case 5:
                setAI(5, 7, 5, 3);
                break;
            case 6:
                setAI(10, 12, 16, 4);
                break;
            case 7:
                setAI(20, 20, 20, 20);
                break;
            case 8:
                setAI(10, 12, 16, 0);
                break;
        }

        blackoutCountdown = 0;
        blackoutPhase = 1;

        aiBuff1 = false;
        aiBuff2 = false;
        aiBuff3 = false;

    }

    static void update() {

        if (!OfficeLogic.getBlackout())
            updateGame();
        else if (OfficeLogic.getBlackout())
            updateBlackout();

    }

    private static void updateGame() {

        // foxy cam stall
        if (CameraLogic.getCamStatus()) {
            if (foxyStallCooldown == 0) {
                foxyStallCooldownThreshold = new Random().nextInt(50, 1050);
                foxy.changeStalled(true);
            }
        }

        if (foxy != null && foxy.getStalled()) {
            foxyStallCooldown++;
            if (foxyStallCooldown >= foxyStallCooldownThreshold) {
                resetFoxyStall();
                foxy.changeStalled(false);
            }
        }

        // animatronic move
        if (NightLogic.getNightCounter() % bonnieMoveInterval == 0 && NightLogic.getNightCounter() > 0)
            bonnie.move();
        if (NightLogic.getNightCounter() % chicaMoveInterval == 0 && NightLogic.getNightCounter() > 0)
            chica.move();
        if (NightLogic.getNightCounter() % freddyMoveInterval == 0 && NightLogic.getNightCounter() > 0)
            freddy.move();

        if (foxy != null && NightLogic.getNightCounter() > 0)
            foxy.move(); // gets called every frame instead of every movement interval

        // buff animatronic's ai at 2am, 3am, and 4am
        if (NightLogic.getHour().equals("2AM") && !aiBuff1) {
            bonnie.buffAI();
            aiBuff1 = true;
        }
        if (NightLogic.getHour().equals("3AM") && !aiBuff2) {
            bonnie.buffAI();
            chica.buffAI();
            foxy.buffAI();
            aiBuff2 = true;
        }
        if (NightLogic.getHour().equals("4AM") && !aiBuff3) {
            bonnie.buffAI();
            chica.buffAI();
            foxy.buffAI();
            aiBuff3 = true;
        }

    }

    private static void updateBlackout() {

        blackoutCountdown++;

        if (blackoutPhase == 1) {

            if (blackoutCountdown != 0 && (blackoutCountdown % (Game.FPS * 5) == 0 || blackoutCountdown > (Game.FPS * 20))) {

                int rand = new Random().nextInt(5);

                if (rand == 0 || blackoutCountdown > (Game.FPS * 20)) {

                    blackoutPhase++;
                    blackoutCountdown = 0;

                    MessageHandler.addMessage("An animatronic has appeared on the left door...");
                    MessageHandler.addMessage("It's flashing its eyes and playing music...");

                }

            }

        } else if (blackoutPhase == 2) {

            if (blackoutCountdown != 0 && (blackoutCountdown % (Game.FPS * 5) == 0 || blackoutCountdown > (Game.FPS * 20))) {

                int rand = new Random().nextInt(5);

                if (rand == 0 || blackoutCountdown > (Game.FPS * 20)) {

                    blackoutPhase++;
                    blackoutCountdown = 0;

                    MessageHandler.addMessage("The animatronic has stopped...");
                    MessageHandler.addMessage("But all of the remaining lights has turned off...");
                    MessageHandler.addMessage("It's a total blackout...");

                }

            }

        } else if (blackoutPhase == 3) {

            if (blackoutCountdown != 0 && blackoutCountdown % (Game.FPS * 2) == 0) {

                int rand = new Random().nextInt(5);

                if (rand == 0) {

                    AnimatronicLogic.setJumpscareAnimatronic(freddy);
                    GameLogic.setUpJumpscare();

                }

            }

        }

    }

    private static void setAI(int bonnieAI, int chicaAI, int foxyAI, int freddyAI) {

        bonnie = new Ani_BlueRabbit(bonnieAI);
        chica = new Ani_YellowChicken(chicaAI);
        foxy = new Ani_PirateFoxy(foxyAI);
        freddy = new Ani_BrownBear(freddyAI);

    }

    public static void resetFoxyStall() { foxyStallCooldown = 0; }

    // foxy gets a special movement condition
    public static int getFoxyInterval() { return foxyMoveInterval; }

    // for text based fnaf
    public static void checkAnimatronicOnCam(String cam) {

        if (!cam.equals("cam6")) {
            if (bonnie.getCurrentCam().getName().equals(cam))
                MessageHandler.addMessage(bonnie.getName() + " is on "+cam.toUpperCase()+"!");
            if (chica.getCurrentCam().getName().equals(cam))
                MessageHandler.addMessage(chica.getName() + " is on "+cam.toUpperCase()+"!");
            if (freddy.getCurrentCam().getName().equals(cam))
                MessageHandler.addMessage(freddy.getName() + " is on "+cam.toUpperCase()+"!");
        }

        if (cam.equals("cam1C")) {

            if (foxy.getPhase() == 2) {
                MessageHandler.addMessage(foxy.getName() + " is on "+cam.toUpperCase()+"!");
            } else if (foxy.getPhase() == 3) {
                MessageHandler.addMessage(foxy.getName() + " is on "+cam.toUpperCase()+"!");
                MessageHandler.addMessage("...and it seems he's about to leave!");
            } else if (foxy.getPhase() == 4) {
                MessageHandler.addMessage(foxy.getName() + " is gone...");
            }

        }

        if (cam.equals("cam6")) {

            MessageHandler.addMessage("This camera is disabled.");
            if (chica.getCurrentCam().getName().equals("cam6")) {
                MessageHandler.addMessage("You hear some rustling in the kitchen...");
            }

        }

        if (foxy.getPhase() == 4 && cam.equals("cam2A"))
            MessageHandler.addMessage(foxy.getName() + " is running down the hallway!");

    }

    // for text based fnaf
    public static void checkDoor(boolean left) {

        if (bonnie != null && bonnie.isAttacking() && left)
            MessageHandler.addMessage(bonnie.getName()+" is at the left door!");
        if (chica != null && chica.isAttacking() && !left)
            MessageHandler.addMessage(chica.getName()+" is at the right door!");

    }

    public static void setJumpscareAnimatronic(Animatronic animatronic) {

        MessageHandler.addMessage(animatronic.getName() + " attacked you!");
        jumpscare = animatronic;

    }

    public static String getJumpscareAnimatronic() {

        return jumpscare.getName();

    }

}

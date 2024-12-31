package gui.office;

import gui.GUI;
import gui.GUIButton;
import main.Game;
import util.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OfficeGUI extends GUI {

    private Map<Integer, BufferedImage> officesImages = new HashMap<>();

    DoorButton leftDoorButton, rightDoorButton;
    LightButton leftLightButton, rightLightButton;
    CameraButton cameraButton;
    NightUIText nightUIText;

    // office movement
    private int x = 0;
    private int moveCamCounter = 0;
    OfficeMoveButton moveCamLeft, moveCamLeft2, moveCamRight, moveCamRight2;

    private int lightFlick;
    private int blackoutCounter = 0;


    public OfficeGUI() {

        this.buttons = new GUIButton[10];

        leftDoorButton = new DoorButton(new Rectangle(16, 360, 56, 56), true);
        rightDoorButton = new DoorButton(new Rectangle(1500, 360, 56, 56), false);
        leftLightButton = new LightButton(new Rectangle(16, 420, 56, 56), true);
        rightLightButton = new LightButton(new Rectangle(1500, 420, 56, 56), false);

        cameraButton = new CameraButton(new Rectangle(256, 600, 768, 56));
        nightUIText = new NightUIText();

        moveCamLeft = new OfficeMoveButton(new Rectangle(0, 0, 512, Game.HEIGHT));
        moveCamLeft2 = new OfficeMoveButton(new Rectangle(0, 0, 320, Game.HEIGHT));
        moveCamRight = new OfficeMoveButton(new Rectangle(Game.WIDTH-512, 0, 512, Game.HEIGHT));
        moveCamRight2 = new OfficeMoveButton(new Rectangle(Game.WIDTH-320, 0, 320, Game.HEIGHT));

        buttons[0] = leftDoorButton;
        buttons[1] = leftLightButton;
        buttons[2] = rightDoorButton;
        buttons[3] = rightLightButton;
        buttons[4] = cameraButton;
        buttons[5] = nightUIText;
        buttons[6] = moveCamLeft;
        buttons[7] = moveCamLeft2;
        buttons[8] = moveCamRight;
        buttons[9] = moveCamRight2;

        setUpOfficeImages();

    }

    @Override
    public void update(Game game) {

        super.update(game);

        if (!OfficeLogic.getBlackout())
            blackoutCounter = 0;

        if (GameLogic.compareGameState(GameState.PLAY)) {

            lightFlick = new Random().nextInt(20); // flickering light

            if (!CameraLogic.getCamStatus() ||
                    moveCamLeft.getMoveCamera() ||
                    moveCamLeft2.getMoveCamera() ||
                    moveCamRight.getMoveCamera() ||
                    moveCamRight2.getMoveCamera()) {

                int moveX = 0;

                if (moveCamLeft.getMoveCamera())
                    moveX += 3;
                if (moveCamLeft2.getMoveCamera())
                    moveX += 7;
                if (moveCamRight.getMoveCamera())
                    moveX -= 3;
                if (moveCamRight2.getMoveCamera())
                    moveX -= 7;

                x += moveX;

                if (x >= 0) {
                    x = 0;
                    moveX = 0;
                }
                if (x <= -320) {
                    x = -320;
                    moveX = 0;
                }

                leftDoorButton.updateButtonPosition(moveX);
                leftLightButton.updateButtonPosition(moveX);
                rightDoorButton.updateButtonPosition(moveX);
                rightLightButton.updateButtonPosition(moveX);

            }
        }

    }

    public void render(Graphics2D g2, Rectangle camera, int scale) {

        if (GameLogic.compareGameState(GameState.PLAY)) {

            if (!CameraLogic.getCamStatus()) {
                renderOfficeImages(g2);
                for (int i=0; i < 4; i++) {
                    if (buttons[i] != null) {
                        buttons[i].render(g2, camera, scale);
                    }
                }
            }

            for (int i=4; i < buttons.length; i++) {
                if (buttons[i] != null) {
                    buttons[i].render(g2, camera, scale);
                }
            }

        }

    }

    private void renderOfficeImages(Graphics2D g2) {

        if (!OfficeLogic.getBlackout()) {

            for (int imageKey : officesImages.keySet()) {

                BufferedImage image = officesImages.get(imageKey);

                if (image != null) {

                    switch (imageKey) {
                        case 0:
                            g2.drawImage(image, x, 0, null);
                            break;
                        case 1:
                            if (DoorLogic.getLeftLight() && lightFlick != 0 && !AnimatronicLogic.checkDoorBool(true))
                                g2.drawImage(image, x, 0, null);
                            break;
                        case 2:
                            if (DoorLogic.getRightLight() && lightFlick != 0 && !AnimatronicLogic.checkDoorBool(false))
                                g2.drawImage(image, x+1060, 0, null);
                            break;
                        case 3:
                            if (DoorLogic.getLeftLight() && lightFlick != 0 && AnimatronicLogic.checkDoorBool(true))
                                g2.drawImage(image, x, 0, null);
                            break;
                        case 4:
                            if (DoorLogic.getRightLight() && lightFlick != 0 && AnimatronicLogic.checkDoorBool(false))
                                g2.drawImage(image, x+1060, 0, null);
                            break;
                        case 7:
                            if (DoorLogic.getLeftDoor())
                                g2.drawImage(image, x+70, 0, null);
                            break;
                        case 8:
                            if (DoorLogic.getRightDoor())
                                g2.drawImage(image, x+1260, 0, null);
                            break;
                    }

                }

            }

        } else if (OfficeLogic.getBlackout()) {

            BufferedImage officeBlackout = officesImages.get(5);
            BufferedImage officeBlackoutP2 = officesImages.get(6);

            if (AnimatronicLogic.getBlackoutPhase() == 1 && officeBlackout != null) {
                g2.drawImage(officeBlackout, x, 0, null);
            }
            else if (AnimatronicLogic.getBlackoutPhase() == 2 && officeBlackout != null && officeBlackoutP2 != null) {
                if (lightFlick < 3)
                    g2.drawImage(officeBlackout, x, 0, null);
                else
                    g2.drawImage(officeBlackoutP2, x, 0, null);
            } else if (AnimatronicLogic.getBlackoutPhase() == 3) {
                blackoutCounter++;
                g2.setColor(Color.BLACK);
                if (blackoutCounter > 99 || lightFlick < 5)
                    g2.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
                else
                    g2.drawImage(officeBlackout, x, 0, null);
            }

        }

    }

    private void setUpOfficeImages() {

        try {

            officesImages.put(0, ImageIO.read(getClass().getResourceAsStream("/office/office_main.png")));
            officesImages.put(1, ImageIO.read(getClass().getResourceAsStream("/office/office_leftLight.png")));
            officesImages.put(2, ImageIO.read(getClass().getResourceAsStream("/office/office_rightLight.png")));
            officesImages.put(3, ImageIO.read(getClass().getResourceAsStream("/office/office_leftLight_ani.png")));
            officesImages.put(4, ImageIO.read(getClass().getResourceAsStream("/office/office_rightLight_ani.png")));
            officesImages.put(5, ImageIO.read(getClass().getResourceAsStream("/office/office_blackout.png")));
            officesImages.put(6, ImageIO.read(getClass().getResourceAsStream("/office/office_blackout_p2.png")));
            officesImages.put(7, ImageIO.read(getClass().getResourceAsStream("/office/office_leftDoor.png")));
            officesImages.put(8, ImageIO.read(getClass().getResourceAsStream("/office/office_rightDoor.png")));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}

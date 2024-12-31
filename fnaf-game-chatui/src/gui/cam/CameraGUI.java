package gui.cam;

import entity.animatronic.CamNum;
import gui.GUI;
import gui.GUIButton;
import main.Game;
import util.AnimatronicLogic;
import util.CameraLogic;
import util.GameLogic;
import util.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CameraGUI extends GUI {

    private Map<String, BufferedImage> cameraImages = new HashMap<>();

    // auto camera movement
    private boolean moveRight = true;
    private int x = 0;
    private int moveCamCounter = 0;

    public CameraGUI() {

        try {

            this.backgroundImage = ImageIO.read(getClass().getResourceAsStream("/maps/fnafmapw.png"));

            setUpCameraImages();

            area.width = backgroundImage.getWidth();
            area.height = backgroundImage.getHeight();

        } catch (IOException e) {

            this.backgroundImage = null;

        }

        this.buttons = new GUIButton[12];

        int buttonWidth = 50;
        int buttonHeight = 36;

        buttons[0] = new CamButton(CamNum.CAM1A, new Rectangle(212, 48, buttonWidth, buttonHeight));
        buttons[1] = new CamButton(CamNum.CAM1B, new Rectangle(208, 104, buttonWidth, buttonHeight));
        buttons[2] = new CamButton(CamNum.CAM1C, new Rectangle(120, 228, buttonWidth, buttonHeight));
        buttons[3] = new CamButton(CamNum.CAM2A, new Rectangle(206, 412, buttonWidth, buttonHeight));
        buttons[4] = new CamButton(CamNum.CAM2B, new Rectangle(206, 450, buttonWidth, buttonHeight));
        buttons[5] = new CamButton(CamNum.CAM3, new Rectangle(96, 380, buttonWidth, buttonHeight));
        buttons[6] = new CamButton(CamNum.CAM4A, new Rectangle(370, 412, buttonWidth, buttonHeight));
        buttons[7] = new CamButton(CamNum.CAM4B, new Rectangle(370, 450, buttonWidth, buttonHeight));
        buttons[8] = new CamButton(CamNum.CAM5, new Rectangle(80, 180, buttonWidth, buttonHeight));
        buttons[9] = new CamButton(CamNum.CAM6, new Rectangle(460, 348, buttonWidth, buttonHeight));
        buttons[10] = new CamButton(CamNum.CAM7, new Rectangle(520, 144, buttonWidth, buttonHeight));

        buttons[11] = new CheckButton(new Rectangle(270, 480, 80, 64));

    }

    @Override
    public void update(Game game) {

        super.update(game);

        if (GameLogic.compareGameState(GameState.PLAY)) {

            if (x >= 0)
                moveRight = true;
            else if (x <= -320)
                moveRight = false;

            if (moveRight)
                x--;
            else
                x++;

        }

    }

    @Override
    public void render(Graphics2D g2, Rectangle camera, int scale) {

        if (GameLogic.compareGameState(GameState.PLAY)) {

            if (CameraLogic.getCamStatus()) {
                renderCameraImages(g2);

                if (backgroundImage != null) {
                    g2.drawImage(backgroundImage, area.x, area.y, null);
                }

                if (buttons != null) {
                    for (int i = 0; i < buttons.length; i++) {
                        buttons[i].render(g2, camera, scale);
                    }
                }
                
            }

        }

    }

    private void renderCameraImages(Graphics2D g2) {

        CamNum camNum = CameraLogic.getCurrentCam();
        BufferedImage image = null;

        AnimatronicLogic.checkAnimatronicOnCam(camNum);

        switch (camNum) {
            case CAM1A:
            {
                if (AnimatronicLogic.getBonnieOnCam() && AnimatronicLogic.getChicaOnCam() && AnimatronicLogic.getFreddyOnCam())
                    image = cameraImages.get("cam1a_bcf");
                else if (AnimatronicLogic.getBonnieOnCam() && AnimatronicLogic.getFreddyOnCam())
                    image = cameraImages.get("cam1a_bf");
                else if (AnimatronicLogic.getChicaOnCam() && AnimatronicLogic.getFreddyOnCam())
                    image = cameraImages.get("cam1a_cf");
                else if (AnimatronicLogic.getFreddyOnCam())
                    image = cameraImages.get("cam1a_f");
                else
                    image = cameraImages.get("cam1a_");
                break;
            }
            case CAM1B:
            {
                if (AnimatronicLogic.getBonnieOnCam())
                    image = cameraImages.get("cam1b_b");
                else if (AnimatronicLogic.getChicaOnCam())
                    image = cameraImages.get("cam1b_c");
                else if (AnimatronicLogic.getFreddyOnCam())
                    image = cameraImages.get("cam1b_f");
                else
                    image = cameraImages.get("cam1b_");
                break;
            }
            case CAM1C:
            {
                if (AnimatronicLogic.getFoxyPhase() == 1)
                    image = cameraImages.get("cam1c_1");
                else if (AnimatronicLogic.getFoxyPhase() == 2)
                    image = cameraImages.get("cam1c_2");
                else if (AnimatronicLogic.getFoxyPhase() == 3)
                    image = cameraImages.get("cam1c_3");
                else if (AnimatronicLogic.getFoxyPhase() == 4)
                    image = cameraImages.get("cam1c_4");
                break;
            }
            case CAM2A:
            {
                if (AnimatronicLogic.getBonnieOnCam())
                    image = cameraImages.get("cam2a_b");
                else if (AnimatronicLogic.getFoxyPhase() == 4)
                    image = cameraImages.get("cam2a_fr");
                else
                    image = cameraImages.get("cam2a_");
                break;
            }
            case CAM2B:
            {
                if (AnimatronicLogic.getBonnieOnCam())
                    image = cameraImages.get("cam2b_b");
                else
                    image = cameraImages.get("cam2b_");
                break;
            }
            case CAM3:
            {
                if (AnimatronicLogic.getBonnieOnCam())
                    image = cameraImages.get("cam3_b");
                else
                    image = cameraImages.get("cam3_");
                break;
            }
            case CAM4A:
            {
                if (AnimatronicLogic.getChicaOnCam())
                    image = cameraImages.get("cam4a_c");
                else if (AnimatronicLogic.getFreddyOnCam())
                    image = cameraImages.get("cam4a_f");
                else
                    image = cameraImages.get("cam4a_");
                break;
            }
            case CAM4B: {
                if (AnimatronicLogic.getChicaOnCam())
                    image = cameraImages.get("cam4b_c");
                else if (AnimatronicLogic.getFreddyOnCam())
                    image = cameraImages.get("cam4b_f");
                else
                    image = cameraImages.get("cam4b_");
                break;
            }
            case CAM5: {
                if (AnimatronicLogic.getBonnieOnCam())
                    image = cameraImages.get("cam5_b");
                else
                    image = cameraImages.get("cam5_");
                break;
            }
            case CAM6: {
                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
                break;
            }
            case CAM7: {
                if (AnimatronicLogic.getChicaOnCam())
                    image = cameraImages.get("cam7_c");
                else if (AnimatronicLogic.getFreddyOnCam())
                    image = cameraImages.get("cam7_f");
                else
                    image = cameraImages.get("cam7_");
                break;
            }
        }

        if (image != null)
            g2.drawImage(image, x, 0, null);

    }

    private void setUpCameraImages() {

        try {

            cameraImages.put("cam1a_", ImageIO.read(getClass().getResourceAsStream("/cameras/cam1a/cam1a_.png")));
            cameraImages.put("cam1a_bcf", ImageIO.read(getClass().getResourceAsStream("/cameras/cam1a/cam1a_bcf.png")));
            cameraImages.put("cam1a_bf", ImageIO.read(getClass().getResourceAsStream("/cameras/cam1a/cam1a_bf.png")));
            cameraImages.put("cam1a_cf", ImageIO.read(getClass().getResourceAsStream("/cameras/cam1a/cam1a_cf.png")));
            cameraImages.put("cam1a_f", ImageIO.read(getClass().getResourceAsStream("/cameras/cam1a/cam1a_f.png")));
            cameraImages.put("cam1b_", ImageIO.read(getClass().getResourceAsStream("/cameras/cam1b/cam1b_.png")));
            cameraImages.put("cam1b_b", ImageIO.read(getClass().getResourceAsStream("/cameras/cam1b/cam1b_b.png")));
            cameraImages.put("cam1b_c", ImageIO.read(getClass().getResourceAsStream("/cameras/cam1b/cam1b_c.png")));
            cameraImages.put("cam1b_f", ImageIO.read(getClass().getResourceAsStream("/cameras/cam1b/cam1b_f.png")));
            cameraImages.put("cam1c_1", ImageIO.read(getClass().getResourceAsStream("/cameras/cam1c/cam1c_1.png")));
            cameraImages.put("cam1c_2", ImageIO.read(getClass().getResourceAsStream("/cameras/cam1c/cam1c_2.png")));
            cameraImages.put("cam1c_3", ImageIO.read(getClass().getResourceAsStream("/cameras/cam1c/cam1c_3.png")));
            cameraImages.put("cam1c_4", ImageIO.read(getClass().getResourceAsStream("/cameras/cam1c/cam1c_4.png")));
            cameraImages.put("cam2a_", ImageIO.read(getClass().getResourceAsStream("/cameras/cam2a/cam2a_.png")));
            cameraImages.put("cam2a_b", ImageIO.read(getClass().getResourceAsStream("/cameras/cam2a/cam2a_b.png")));
            cameraImages.put("cam2a_fr", ImageIO.read(getClass().getResourceAsStream("/cameras/cam2a/cam2a_fr.png")));
            cameraImages.put("cam2b_", ImageIO.read(getClass().getResourceAsStream("/cameras/cam2b/cam2b_.png")));
            cameraImages.put("cam2b_b", ImageIO.read(getClass().getResourceAsStream("/cameras/cam2b/cam2b_b.png")));
            cameraImages.put("cam3_", ImageIO.read(getClass().getResourceAsStream("/cameras/cam3/cam3_.png")));
            cameraImages.put("cam3_b", ImageIO.read(getClass().getResourceAsStream("/cameras/cam3/cam3_b.png")));
            cameraImages.put("cam4a_", ImageIO.read(getClass().getResourceAsStream("/cameras/cam4a/cam4a_.png")));
            cameraImages.put("cam4a_c", ImageIO.read(getClass().getResourceAsStream("/cameras/cam4a/cam4a_c.png")));
            cameraImages.put("cam4a_f", ImageIO.read(getClass().getResourceAsStream("/cameras/cam4a/cam4a_f.png")));
            cameraImages.put("cam4b_", ImageIO.read(getClass().getResourceAsStream("/cameras/cam4b/cam4b_.png")));
            cameraImages.put("cam4b_c", ImageIO.read(getClass().getResourceAsStream("/cameras/cam4b/cam4b_c.png")));
            cameraImages.put("cam4b_f", ImageIO.read(getClass().getResourceAsStream("/cameras/cam4b/cam4b_f.png")));
            cameraImages.put("cam5_", ImageIO.read(getClass().getResourceAsStream("/cameras/cam5/cam5_.png")));
            cameraImages.put("cam5_b", ImageIO.read(getClass().getResourceAsStream("/cameras/cam5/cam5_b.png")));
            cameraImages.put("cam7_", ImageIO.read(getClass().getResourceAsStream("/cameras/cam7/cam7_.png")));
            cameraImages.put("cam7_c", ImageIO.read(getClass().getResourceAsStream("/cameras/cam7/cam7_c.png")));
            cameraImages.put("cam7_f", ImageIO.read(getClass().getResourceAsStream("/cameras/cam7/cam7_f.png")));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}

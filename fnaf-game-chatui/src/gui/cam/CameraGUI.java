package gui.cam;

import gui.GUI;
import gui.GUIButton;
import util.CameraLogic;
import util.GameLogic;
import util.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class CameraGUI extends GUI {

    public CameraGUI() {

        try {

            this.backgroundImage = ImageIO.read(getClass().getResourceAsStream("/maps/fnafmapb.png"));

            area.width = backgroundImage.getWidth();
            area.height = backgroundImage.getHeight();

        } catch (IOException e) {

            this.backgroundImage = null;

        }

        this.buttons = new GUIButton[12];

        int buttonWidth = 50;
        int buttonHeight = 36;

        buttons[0] = new CamButton("cam1A", new Rectangle(212, 48, buttonWidth, buttonHeight));
        buttons[1] = new CamButton("cam1B", new Rectangle(208, 104, buttonWidth, buttonHeight));
        buttons[2] = new CamButton("cam1C", new Rectangle(120, 228, buttonWidth, buttonHeight));
        buttons[3] = new CamButton("cam2A", new Rectangle(206, 412, buttonWidth, buttonHeight));
        buttons[4] = new CamButton("cam2B", new Rectangle(206, 450, buttonWidth, buttonHeight));
        buttons[5] = new CamButton("cam3", new Rectangle(96, 380, buttonWidth, buttonHeight));
        buttons[6] = new CamButton("cam4A", new Rectangle(370, 412, buttonWidth, buttonHeight));
        buttons[7] = new CamButton("cam4B", new Rectangle(370, 450, buttonWidth, buttonHeight));
        buttons[8] = new CamButton("cam5", new Rectangle(80, 180, buttonWidth, buttonHeight));
        buttons[9] = new CamButton("cam6", new Rectangle(460, 348, buttonWidth, buttonHeight));
        buttons[10] = new CamButton("cam7", new Rectangle(520, 144, buttonWidth, buttonHeight));

        buttons[11] = new CheckButton(new Rectangle(270, 480, 80, 64));

    }

    @Override
    public void render(Graphics2D g2, Rectangle camera, int scale) {

        if (CameraLogic.getCamStatus() && GameLogic.compareGameState(GameState.PLAY)) {
            if (backgroundImage != null) {
                g2.drawImage(backgroundImage, area.x, area.y, null);
            }

            if (buttons != null) {
                for (int  i =0; i < buttons.length; i++) {
                    buttons[i].render(g2, camera, scale);
                }
            }
        }

    }

}

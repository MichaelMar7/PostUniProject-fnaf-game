package gui.office;

import gui.GUI;
import gui.GUIButton;
import util.CameraLogic;
import util.GameLogic;
import util.GameState;
import util.OfficeLogic;

import java.awt.*;

public class OfficeGUI extends GUI {

    public OfficeGUI() {

        this.buttons = new GUIButton[6];

        buttons[0] = new DoorButton(new Rectangle(140, 420, 64, 64), true);
        buttons[1] = new LightButton(new Rectangle(140, 500, 64, 64), true);
        buttons[2] = new DoorButton(new Rectangle(452, 420, 64, 64), false);
        buttons[3] = new LightButton(new Rectangle(452, 500, 64, 64), false);
        buttons[4] = new CameraButton(new Rectangle(200, 600, 256, 64));
        buttons[5] = new NightUIText();

    }

    public void render(Graphics2D g2, Rectangle camera, int scale) {

        if (!OfficeLogic.getBlackout() && GameLogic.compareGameState(GameState.PLAY)) {

            if (!CameraLogic.getCamStatus()) {
                for (int  i =0; i < 4; i++) {
                    if (buttons[i] != null) {
                        buttons[i].render(g2, camera, scale);
                    }
                }
            }

            for (int  i=4; i < buttons.length; i++) {
                if (buttons[i] != null) {
                    buttons[i].render(g2, camera, scale);
                }
            }

        }

    }

}

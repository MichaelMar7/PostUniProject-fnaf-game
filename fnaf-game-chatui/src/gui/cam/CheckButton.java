package gui.cam;

import gui.GUIButton;
import main.Game;
import util.AnimatronicLogic;
import util.CameraLogic;

import java.awt.*;

public class CheckButton extends GUIButton {

    private boolean canHover = true;
    private Color colour;

    public CheckButton(Rectangle area) {
        super(null, area, false);
    }

    @Override
    public void render(Graphics2D g2, Rectangle camera, int scale) {

        g2.setColor(colour);
        g2.fillRect(area.x, area.y, area.width, area.height);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 20));

        g2.drawString("Check", area.x+4, area.y+area.height-30);
        g2.drawString("Cam", area.x+4, area.y+area.height-8);

    }

    @Override
    public void update(Game game) {

        if (canHover) {
            colour = Color.GREEN;
        } else {
            colour = Color.GRAY;
        }

    }

    @Override
    public void buttonClickAction() {

        if (CameraLogic.getCamStatus())
            AnimatronicLogic.checkAnimatronicOnCamMessage(CameraLogic.getCurrentCam());

    }

    @Override
    public boolean handleMouseHover(Rectangle camera, Rectangle mouseRect, int scale) {

        if (mouseRect.intersects(area) && CameraLogic.getCamStatus()) {
            canHover = true;
        }  else
            canHover = false;

        return canHover;

    }

}

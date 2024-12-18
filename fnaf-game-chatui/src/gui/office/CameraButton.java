package gui.office;

import gui.GUIButton;
import main.Game;
import util.CameraLogic;
import util.DoorLogic;
import util.OfficeLogic;

import java.awt.*;


public class CameraButton extends GUIButton {

    private boolean canHover = true;

    public CameraButton(Rectangle area) {

        super(null, area, false);

    }

    @Override
    public void render(Graphics2D g2, Rectangle camera, int scale) {

        if (canHover)
            g2.drawRect(area.x, area.y, area.width, area.height);
        else
            g2.fillRect(area.x, area.y, area.width, area.height);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.PLAIN, 16));
        int textLength = (int) g2.getFontMetrics().getStringBounds("Camera", g2).getWidth();
        g2.drawString("Cameras", area.x+(area.width-textLength)/2, area.y+(area.height/2)+6);

    }

    @Override
    public void update(Game game) {

        if (OfficeLogic.getBlackout()) {
            CameraLogic.disableCam();
            DoorLogic.closeDoors();
            DoorLogic.closeLights();
        }

    }

    @Override
    public boolean handleMouseHover(Rectangle camera, Rectangle mouseRect, int scale) {

        if (mouseRect.intersects(area) && !OfficeLogic.getBlackout()) {
            if (!canHover) {
                CameraLogic.changeCamStatus();
                DoorLogic.closeLights();
            }
            canHover = true;
        } else
            canHover = false;

        return canHover;

    }

}

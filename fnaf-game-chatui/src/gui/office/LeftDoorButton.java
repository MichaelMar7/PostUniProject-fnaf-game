package gui.office;

import gui.GUIButton;
import main.Game;
import util.CameraLogic;
import util.DoorLogic;
import util.OfficeLogic;

import java.awt.*;

public class LeftDoorButton extends GUIButton {

    private boolean active = false;

    private Color colour;
    private final Color onColour = Color.GREEN;
    private final Color offColour = Color.RED;

    public LeftDoorButton(Rectangle area) {

        super(null, area, false);

    }

    @Override
    public void render(Graphics2D g2, Rectangle camera, int scale) {

        g2.setColor(colour);
        g2.fillRect(area.x, area.y, area.width, area.height);
        g2.setColor(Color.BLACK);

    }

    @Override
    public void update(Game game) {

        if (DoorLogic.getLeftDoor()) {
            colour = onColour;
        } else {
            colour = offColour;
        }

    }

    @Override
    public void buttonClickAction() {

        if (!CameraLogic.getCamStatus() && !OfficeLogic.getBlackout()) {
            DoorLogic.changeDoor(true);
        }

    }

}

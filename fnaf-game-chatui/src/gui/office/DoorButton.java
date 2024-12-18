package gui.office;

import gui.GUIButton;
import main.Game;
import util.CameraLogic;
import util.DoorLogic;
import util.OfficeLogic;

import java.awt.*;

public class DoorButton extends GUIButton {

    private boolean left;

    private Color colour;
    private final Color onColour = Color.GREEN;
    private final Color offColour = Color.RED;

    public DoorButton(Rectangle area, boolean left) {

        super(null, area, false);

        this.left = left;

    }

    @Override
    public void render(Graphics2D g2, Rectangle camera, int scale) {

        g2.setColor(colour);
        g2.fillRect(area.x, area.y, area.width, area.height);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.PLAIN, 16));
        int textLength = (int) g2.getFontMetrics().getStringBounds("Door", g2).getWidth();
        g2.drawString("Door", area.x+(area.width-textLength)/2, area.y+(area.height/2)+6);

        g2.setColor(Color.BLACK);

    }

    @Override
    public void update(Game game) {

        if ((left && DoorLogic.getLeftDoor()) || (!left && DoorLogic.getRightDoor())) {
            colour = onColour;
        } else {
            colour = offColour;
        }

    }

    @Override
    public void buttonClickAction() {

        if (!CameraLogic.getCamStatus() && !OfficeLogic.getBlackout()) {
            DoorLogic.changeDoor(left);
        }

    }

}

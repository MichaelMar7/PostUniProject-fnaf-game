package gui.office;

import gui.GUIButton;
import main.Game;
import util.AnimatronicLogic;
import util.CameraLogic;
import util.DoorLogic;
import util.OfficeLogic;

import java.awt.*;

public class LightButton extends GUIButton {

    private boolean left;

    private Color colour;
    private final Color onColour = Color.WHITE;
    private final Color offColour = Color.LIGHT_GRAY;

    public LightButton(Rectangle area, boolean left) {

        super(null, area, false);

        this.left = left;

    }

    @Override
    public void render(Graphics2D g2, Rectangle camera, int scale) {

        g2.setColor(colour);
        g2.fillRect(area.x, area.y, area.width, area.height);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.PLAIN, 16));
        int textLength = (int) g2.getFontMetrics().getStringBounds("Light", g2).getWidth();
        g2.drawString("Light", area.x+(area.width-textLength)/2, area.y+(area.height/2)+6);

        g2.setColor(Color.BLACK);

    }

    @Override
    public void update(Game game) {

        if ((left && DoorLogic.getLeftLight()) || (!left && DoorLogic.getRightLight())) {
            colour = onColour;
        } else {
            colour = offColour;
        }

    }

    @Override
    public void buttonClickAction() {

        if (!CameraLogic.getCamStatus() && !OfficeLogic.getBlackout()) {
            DoorLogic.changeLight(left);

            if (left && DoorLogic.getLeftLight())
                AnimatronicLogic.checkDoor(true);
            else if (!left && DoorLogic.getRightLight())
                AnimatronicLogic.checkDoor(false);

        }

    }

}

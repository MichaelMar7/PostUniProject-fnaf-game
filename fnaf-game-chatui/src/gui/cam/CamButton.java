package gui.cam;

import gui.GUIButton;
import main.Game;
import util.CameraLogic;

import java.awt.*;

public class CamButton extends GUIButton {

    private String name;
    private Color colour;

    public CamButton(String name, Rectangle area) {

        super(null, area, false);

        this.name = name;

    }

    @Override
    public void render(Graphics2D g2, Rectangle camera, int scale) {

        g2.setColor(colour);
        g2.fillRect(area.x, area.y, area.width, area.height);

        g2.setColor(new Color(200, 200, 200));
        g2.drawRect(area.x, area.y, area.width, area.height);

        String cam = getName().substring(0,3).toUpperCase();
        String camNum = getName().substring(3);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 16));

        g2.drawString(cam, area.x+4, area.y+area.height-20);
        g2.drawString(camNum, area.x+4, area.y+area.height-4);

    }

    @Override
    public void update(Game game) {

        if (CameraLogic.getCurrentCam().equals(getName())) {
            colour = Color.GREEN;
        } else {
            colour = Color.GRAY;
        }

    }

    @Override
    public void buttonClickAction() {

        if (CameraLogic.getCamStatus())
            CameraLogic.changeCam(getName());

    }

    public String getName() { return name; }

}

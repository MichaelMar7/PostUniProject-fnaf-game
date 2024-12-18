package gui.other;

import gui.GUIButton;
import util.*;

import java.awt.*;

public class MenuButton extends GUIButton {

    private boolean canHover = false;

    private String text;
    private int night;

    public MenuButton(Rectangle area, String text, int night) {
        super(null, area, false);

        this.text = text;
        this.night = night;
    }

    @Override
    public void render(Graphics2D g2, Rectangle camera, int scale) {

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 32));

        g2.drawString(text, area.x, area.y+area.height);

        if (canHover)
            g2.drawString("|", area.x - 16, area.y+area.height);

    }

    @Override
    public void buttonClickAction() {

        if (GameLogic.compareGameState(GameState.MENU)) {
            NightLogic.setNight(night);
            GameLogic.setUpIntro();
        }

    }

    @Override
    public boolean handleMouseHover(Rectangle camera, Rectangle mouseRect, int scale) {

        if (mouseRect.intersects(area) && GameLogic.compareGameState(GameState.MENU)) {
            canHover = true;
        }  else
            canHover = false;

        return canHover;

    }

}

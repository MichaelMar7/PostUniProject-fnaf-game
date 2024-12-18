package gui.other;

import gui.GUI;
import main.Game;
import util.GameLogic;
import util.GameState;
import util.NightLogic;

import java.awt.*;

public class WinGUI extends GUI {

    public WinGUI() {}

    @Override
    public void render(Graphics2D g2, Rectangle camera, int scale) {

        if (GameLogic.compareGameState(GameState.WIN)) {

            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.PLAIN, 32));

            String text = "6 AM";

            int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            g2.drawString(text, (Game.WIDTH - textLength) / 2, 360);

        }

    }

    @Override
    public boolean handleMouseClick(Rectangle camera, Rectangle mouseRect, int scale) {
        return false;
    }

    @Override
    public boolean handleMouseHover(Rectangle camera, Rectangle mouseRect, int scale) {
        return false;
    }

}

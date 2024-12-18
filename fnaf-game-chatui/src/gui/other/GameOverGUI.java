package gui.other;

import gui.GUI;
import main.Game;
import util.GameLogic;
import util.GameState;

import java.awt.*;

public class GameOverGUI extends GUI {

    public GameOverGUI() {}

    @Override
    public void render(Graphics2D g2, Rectangle camera, int scale) {

        if (GameLogic.compareGameState(GameState.GAMEOVER)) {

            g2.setColor(Color.RED);
            g2.setFont(new Font("Arial", Font.PLAIN, 24));

            String text = "GAME OVER";

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

package gui.other;

import gui.GUI;
import main.Game;
import util.GameLogic;
import util.GameState;
import util.NightLogic;

import java.awt.*;

public class IntroGUI extends GUI {

    public IntroGUI() {}

    @Override
    public void render(Graphics2D g2, Rectangle camera, int scale) {

        if (GameLogic.compareGameState(GameState.INTRO)) {

            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.PLAIN, 32));

            String time = "12:00 AM";
            String night = "";

            switch (NightLogic.getNight()) {
                case 1:
                    night = "1st Night";
                    break;
                case 2:
                    night = "2nd Night";
                    break;
                case 3:
                    night = "3rd Night";
                    break;
                case 6:
                    night = "Nightmare";
                    break;
                default:
                    night = NightLogic.getNight() + "th Night";
                    break;
            }

            int timeLength = (int) g2.getFontMetrics().getStringBounds(time, g2).getWidth();
            int nightLength = (int) g2.getFontMetrics().getStringBounds(night, g2).getWidth();

            g2.drawString(time, (Game.WIDTH - timeLength) / 2, 360);
            g2.drawString(night, (Game.WIDTH - nightLength) / 2, 420);

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

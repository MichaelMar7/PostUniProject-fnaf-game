package gui.office;

import gui.GUIButton;
import main.Game;
import util.GameLogic;
import util.GameState;
import util.NightLogic;
import util.OfficeLogic;

import java.awt.*;

public class NightUIText extends GUIButton {

    private Font font = new Font("Arial", Font.BOLD, 32);

    public NightUIText() {

        super(null, new Rectangle(), false);

    }

    @Override
    public void render(Graphics2D g2, Rectangle camera, int scale) {

        g2.setFont(font);
        g2.setColor(Color.BLACK);

        if (GameLogic.getGameState() == GameState.PLAY) {
            g2.drawString("Hour: "+ NightLogic.getHour(), 1080, 60);

            g2.setFont(new Font("Arial", Font.PLAIN, 24));

            // night number (night 6 special condition)
            if (NightLogic.getNight() == 6)
                g2.drawString("Nightmare", 1080, 88);
            else
                g2.drawString("Night "+ NightLogic.getNight(), 1080, 88);

            g2.drawString("Power: "+ OfficeLogic.getPower() +"%", 16, 668);
            g2.drawString("Usage: ", 16, 700);

            if (OfficeLogic.getPowerUsage() >= 1) {
                g2.setColor(Color.GREEN);
                g2.fillRect(96, 680, 16, 24);
            } if (OfficeLogic.getPowerUsage() >= 2) {
                g2.setColor(Color.GREEN);
                g2.fillRect(114, 680, 16, 24);
            } if (OfficeLogic.getPowerUsage() >= 3) {
                g2.setColor(Color.YELLOW);
                g2.fillRect(132, 680, 16, 24);
            } if (OfficeLogic.getPowerUsage() >= 4) {
                g2.setColor(Color.RED);
                g2.fillRect(150, 680, 16, 24);
            } if (OfficeLogic.getPowerUsage() >= 5) {
                g2.setColor(Color.RED);
                g2.fillRect(168, 680, 16, 24);
            }

        }

        if (GameLogic.getGameState() == GameState.JUMPSCARE) {
            g2.drawString("You are dead!", 1120, 200);
        }

        if (GameLogic.getGameState() == GameState.WIN) {
            g2.drawString("6AM!", 1120, 200);
        }

    }

    @Override
    public void update(Game game) {

    }

}

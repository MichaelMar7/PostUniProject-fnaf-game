package test;

import gui.GUIButton;
import main.Game;
import util.GameLogic;
import util.GameState;
import util.NightLogic;
import util.OfficeLogic;

import java.awt.*;

public class TestUIText extends GUIButton {

    private Game game;
    private Font font = new Font("Arial", Font.PLAIN, 16);

    public TestUIText(Game game) {

        super(null, new Rectangle(), false);
        this.game = game;

    }

    @Override
    public void render(Graphics2D g2, Rectangle camera, int scale) {

        g2.setFont(font);
        g2.drawString("Frames: "+game.frames, 20, 32);
        g2.drawString("Ticks: "+game.ticks, 20, 64);

    }

    @Override
    public void update(Game game) {

    }


}

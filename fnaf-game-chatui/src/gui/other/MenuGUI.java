package gui.other;

import gui.GUI;
import gui.GUIButton;
import main.Game;
import util.GameLogic;
import util.GameState;

import java.awt.*;

public class MenuGUI extends GUI {

    public MenuGUI() {

        this.buttons = new GUIButton[3];

        buttons[0] = new MenuButton(new Rectangle(96, 500, 150, 32), "Easy", 1);
        buttons[1] = new MenuButton(new Rectangle(96, 550, 150, 32), "Normal", 2);
        buttons[2] = new MenuButton(new Rectangle(96, 600, 150, 32), "Nightmare", 6);

    }

    public void render(Graphics2D g2, Rectangle camera, int scale) {

        if (GameLogic.compareGameState(GameState.MENU)) {

            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.BOLD, 64));

            g2.drawString("(Not) Five Night at Freddy's", 64, 128);

            g2.setFont(new Font("Arial", Font.PLAIN, 30));
            g2.drawString("Java Chat UI Edition (Demo)", 66, 172);

            g2.setFont(new Font("Arial", Font.PLAIN, 24));
            g2.drawString("Build "+ Game.BUILD, 1194, 640);
            g2.drawString("2024 Michael Mar", 1080, 670);
            g2.drawString("Original by Scott Cawthon", 985, 700);

            for (int  i =0; i < buttons.length; i++) {
                if (buttons[i] != null) {
                    buttons[i].render(g2, camera, scale);
                }
            }

        }

    }

}

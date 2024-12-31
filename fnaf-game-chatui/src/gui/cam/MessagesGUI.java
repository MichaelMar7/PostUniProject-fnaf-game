package gui.cam;

import gui.GUI;
import util.CameraLogic;
import util.MessageHandler;

import java.awt.*;

public class MessagesGUI extends GUI {

    private final int x = 720;

    public MessagesGUI() {}

    @Override
    public void render(Graphics2D g2, Rectangle camera, int scale) {

        int y = 560;

        g2.setColor(Color.WHITE);

        g2.setFont(new Font("Arial", Font.PLAIN, 24));

        for (int i = 0; i < MessageHandler.getNumberOfMessages(); i++) {

            if (MessageHandler.getCooldown(i) > 0) {
                String message = MessageHandler.getMessage(i);
                g2.drawString(message, x, y);
                y -= 32;
            }

        }

    }

    @Override
    public boolean handleMouseClick(Rectangle camera, Rectangle mouseRect, int scale) { return false; }

    @Override
    public boolean handleMouseHover(Rectangle camera, Rectangle mouseRect, int scale) { return false; }

}

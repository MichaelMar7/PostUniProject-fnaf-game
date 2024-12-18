package gui;

import entity.GameEntity;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GUI implements GameEntity {

    protected BufferedImage backgroundImage;
    protected GUIButton[] buttons;
    protected Rectangle area = new Rectangle();
    boolean fixed;

    public GUI(BufferedImage backgroundImage, GUIButton[] buttons, int x, int y, boolean fixed) {

        this.backgroundImage = backgroundImage;
        this.buttons = buttons;
        this.fixed = fixed;

        area.x = x;
        area.y = y;

        if (backgroundImage != null) {
            area.width = backgroundImage.getWidth();
            area.height = backgroundImage.getHeight();
        }

    }

    public GUI(GUIButton[] buttons, int x, int y, boolean fixed) {

        this(null, buttons, x, y, fixed);

    }

    public GUI() {

    }

    @Override
    public void update(Game game) {

        if (buttons != null) {
            for (int i = 0; i < buttons.length; i++)
                if (buttons[i] != null)
                    buttons[i].update(game);
        }

    }

    @Override
    public void render(Graphics2D g2, Rectangle camera, int scale) {

        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, area.x, area.y, null);
        }

        if (buttons != null) {
            for (int  i =0; i < buttons.length; i++) {
                buttons[i].render(g2, camera, scale);
            }
        }

    }

    @Override
    public boolean handleMouseClick(Rectangle camera, Rectangle mouseRect, int scale) {

        boolean stopChecking = false;

        mouseRect = new Rectangle(mouseRect.x, mouseRect.y, 1, 1);

        if (area.width == 0 || area.height == 0 || mouseRect.intersects(area)) {

            mouseRect.x -= area.x;;
            mouseRect.y -= area.y;
            for (int i = 0; i < buttons.length; i++) {
                boolean result = buttons[i].handleMouseClick(camera, mouseRect, scale);
                if (!stopChecking) {
                    stopChecking = result;
                }
            }
        }

        return stopChecking;

    }

    @Override
    public boolean handleMouseHover(Rectangle camera, Rectangle mouseRect, int scale) {

        boolean stopChecking = false;

        mouseRect = new Rectangle(mouseRect.x, mouseRect.y, 1, 1);

        if (area.width == 0 || area.height == 0 || mouseRect.intersects(area)) {

            mouseRect.x -= area.x;;
            mouseRect.y -= area.y;
            for (int i = 0; i < buttons.length; i++) {
                boolean result = buttons[i].handleMouseHover(camera, mouseRect, scale);
                if (!stopChecking) {
                    stopChecking = result;
                }
            }
        }

        return stopChecking;

    }

}

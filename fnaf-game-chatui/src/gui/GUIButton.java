package gui;

import entity.GameEntity;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GUIButton implements GameEntity {

    protected BufferedImage image;
    protected Rectangle area;
    protected boolean fixed;

    public GUIButton(BufferedImage image, Rectangle area, boolean fixed) {

        this.image = image;
        this.area = area;
        this.fixed = fixed;

    }

    @Override
    public void update(Game game) {

    }

    @Override
    public void render(Graphics2D g2, Rectangle camera, int scale) {

        g2.drawImage(image, area.x, area.y, null);

    }

    @Override
    public boolean handleMouseClick(Rectangle camera, Rectangle mouseRect, int scale) {

        if (mouseRect.intersects(area)) {

            buttonClickAction();
            return true;

        }

        return false;

    }

    @Override
    public boolean handleMouseHover(Rectangle camera, Rectangle mouseRect, int scale) {

        return false;

    }

    public void buttonClickAction() {}

}

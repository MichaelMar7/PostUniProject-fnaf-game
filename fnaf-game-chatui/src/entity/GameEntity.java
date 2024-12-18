package entity;

import main.Game;

import java.awt.*;
import java.util.Random;

public interface GameEntity {

    void update(Game game);

    void render(Graphics2D g2, Rectangle camera, int scale);

    boolean handleMouseClick(Rectangle camera, Rectangle mouseRect, int scale);

    boolean handleMouseHover(Rectangle camera, Rectangle mouseRect, int scale);

}

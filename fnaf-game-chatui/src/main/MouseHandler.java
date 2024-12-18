package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    private Game game;

    public MouseHandler(Game game) {
        this.game = game;
    }


    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1)
            game.mouseLeftPressed(e.getX(), e.getY());

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        game.mouseHover(e.getX(), e.getY());
    }

}

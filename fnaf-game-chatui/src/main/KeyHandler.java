package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private Game game;

    public boolean[] keys = new boolean[128];

    public KeyHandler(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code < keys.length)
            keys[code] = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code < keys.length)
            keys[code] = false;

    }


    public boolean up() {

        return keys[KeyEvent.VK_W];

    }

    public boolean down() {

        return keys[KeyEvent.VK_S];

    }

    public boolean left() {

        return keys[KeyEvent.VK_A];

    }

    public boolean right() {

        return keys[KeyEvent.VK_D];

    }

    public boolean space() {

        return keys[KeyEvent.VK_SPACE];

    }


}

package main;

import entity.GameEntity;
import gui.GUI;
import gui.GUIButton;
import gui.cam.CameraGUI;
import gui.cam.MessagesGUI;
import gui.office.OfficeGUI;
import gui.other.*;
import test.TestUIText;
import util.GameLogic;
import util.GameState;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game extends JPanel implements Runnable {

    private Rectangle camera;

    private KeyHandler keyHandler = new KeyHandler(this);
    private MouseHandler mouseHandler = new MouseHandler(this);

    private ArrayList<GameEntity> entities = new ArrayList<>();

    private boolean running = false;
    private int mouseHoverCounter = 0;

    public static final int FPS = 60;
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    private static final int SCALE = 1;

    public static final int BUILD = 2;

    public int frames, ticks;


    public Game() {

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDoubleBuffered(true);

        addKeyListener(keyHandler);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        setFocusable(true);

        generateEntities();

        GameLogic.changeGameState(GameState.MENU);

        camera = new Rectangle(0, 0, WIDTH, HEIGHT);

        new Window(this, WIDTH, HEIGHT);

    }

    @Override
    public void run() {

        long lastTime = System.nanoTime();
        double nsPerTick = (double) 1000000000 / FPS;

        // display only
        int frames = 0;
        int ticks = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        while (running) {

            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            while (delta > 1) {
                tick();
                ticks++;
                delta--;
            }

            frames++;
            repaint();

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                this.frames = frames;
                this.ticks = ticks;
                frames = 0;
                ticks = 0;
            }

        }

    }

    public void tick() {

        GameLogic.update(this);

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update(this);
        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(g2, camera, SCALE);
        }

        g2.dispose();

    }

    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void stop() {
        running = false;
    }


    public void mouseLeftPressed(int x, int y) {

        Rectangle mouseRect = new Rectangle(x, y, 1, 1);
        boolean stopChecking = false;


        for (int i = 0; i < entities.size(); i++) {
            if (!stopChecking)
                stopChecking = entities.get(i).handleMouseClick(camera, mouseRect, SCALE);
        }

    }

    public void mouseHover(int x, int y) {

        if (mouseHoverCounter == 0) {

            Rectangle mouseRect = new Rectangle(x, y, 1, 1);
            boolean stopChecking = false;

            for (int i = 0; i < entities.size(); i++) {
                if (!stopChecking)
                    stopChecking = entities.get(i).handleMouseHover(camera, mouseRect, SCALE);
            }

            if (stopChecking)
                mouseHoverCounter++;

        } else {

            mouseHoverCounter++;

            if (mouseHoverCounter == 21)
                mouseHoverCounter = 0;

        }

    }

    private void generateEntities() {

        entities.add(new GUI(new GUIButton[] {new TestUIText(this)}, 0, 0, true));
        entities.add(new OfficeGUI());
        entities.add(new CameraGUI());
        entities.add(new MessagesGUI());
        entities.add(new MenuGUI());
        entities.add(new IntroGUI());
        entities.add(new JumpscareGUI());
        entities.add(new GameOverGUI());
        entities.add(new WinGUI());

    }


    public void generateTestEntities() {

//        entities.add(new TestRectangle());

    }

    public void generateTestImages() {}


    public static void main(String[] args) {

        Game game = new Game();
        game.start();

    }
}

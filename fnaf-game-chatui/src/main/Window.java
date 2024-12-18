package main;

import javax.swing.*;

public class Window extends JFrame {

    public Window(Game game, int width, int height) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, width, height);
        setLocationRelativeTo(null);

        setTitle("Fnaf Clone");
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon/freddyicon.png"));
        setIconImage(icon.getImage());

        add(game);

        pack();
        setVisible(true);

    }
}

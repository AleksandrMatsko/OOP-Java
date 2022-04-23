package View;

import Controller.KeyboardListener;

import javax.swing.*;

public class Viewer extends JFrame {

    public Viewer() {
        super("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        this.addKeyListener(new KeyboardListener());
    }

    public void drawFigure() {}

}

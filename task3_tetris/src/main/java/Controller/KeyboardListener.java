package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyboardListener implements KeyListener {
    private HashMap<KeyEvent, Boolean> isPressed;

    public KeyboardListener() {
        isPressed = new HashMap<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("Space pressed");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left pressed");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}

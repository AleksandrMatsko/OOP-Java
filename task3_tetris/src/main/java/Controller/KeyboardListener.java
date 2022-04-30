package Controller;

import Model.Names.ActionName;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
    private final KeyboardSettings keyboardSettings;
    private ActionName currentAction;


    public KeyboardListener(KeyboardSettings keyboardSettings) {
        this.keyboardSettings = keyboardSettings;
        currentAction = null;
    }

    public ActionName getCurrentAction() {
        return currentAction;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        currentAction = keyboardSettings.getActionOnKey(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentAction = null;
    }
}

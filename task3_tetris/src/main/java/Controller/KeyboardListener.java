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

    public KeyboardSettings getKeyboardSettings() {
        return keyboardSettings;
    }

    public ActionName getCurrentAction() {
        return currentAction;
    }

    public void dropCurrentAction() {
        currentAction = null;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        currentAction = keyboardSettings.getActionOnKey(e.getKeyCode());
        System.err.println(currentAction.getName());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentAction = null;
    }
}

package Controller;

import Exceptions.NameExceptions.InvalidActionNameException;
import Exceptions.NotFoundExceptions.ActionNotFoundException;
import Model.Names.ActionName;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class KeyboardSettings {
    private final HashMap<Integer, ActionName> actionsProvidedOnKey;
    private final ArrayList<Integer> usedKeys;

    public KeyboardSettings() {
        actionsProvidedOnKey = new HashMap<>();
        usedKeys = new ArrayList<>();
        final String configFileName = "/keys.properties";
        Properties properties = new Properties();
        try {
            properties.load(KeyboardSettings.class.getResourceAsStream(configFileName));
        }
        catch (IOException ex) {
            throw new RuntimeException("Problems with keys.properties");
        }
        for (Map.Entry entry : properties.entrySet()) {
            int keyCode = 0;
            try {
                keyCode = (Integer) KeyEvent.class.getDeclaredField((String) entry.getKey()).get(null);
            }
            catch (NoSuchFieldException | IllegalAccessException ex) {
                throw new RuntimeException("Invalid format for key fields in keys.properties");
            }

            String toActionName = (String) entry.getValue();
            ActionName actionName = null;

            try {
                actionName = new ActionName(toActionName);
            }
            catch (InvalidActionNameException ex) {
                throw new RuntimeException("Invalid format for ActionName in keys.properties");
            }
            actionsProvidedOnKey.put(keyCode, actionName);
            usedKeys.add(keyCode);
        }
    }

    public ActionName getActionOnKey(int keyCode) {
        return actionsProvidedOnKey.get(keyCode);
    }

}

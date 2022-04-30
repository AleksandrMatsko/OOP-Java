package Controller;

import Model.Names.ActionName;

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
        //TODO create resource
        final String configFileName = "src/main/resources/keys.ini";
        Properties properties = new Properties();
        try {
            properties.load(KeyboardSettings.class.getResourceAsStream(configFileName));
        }
        catch (IOException ex) {
            //TODO exception
            ex.printStackTrace();
        }
        for (Map.Entry entry : properties.entrySet()) {
            //TODO convert KeyEvent.() to integer
            int keyCode = Integer.getInteger((String) entry.getKey());
            String toActionName = (String) entry.getValue();
            ActionName actionName;
            //TODO try catch
            actionName = new ActionName(toActionName);
            actionsProvidedOnKey.put(keyCode, actionName);
            usedKeys.add(keyCode);
        }
    }

    public ArrayList<Integer> getUsedKeys() {
        return usedKeys;
    }

    public HashMap<Integer, ActionName> getActionsProvidedOnKey() {
        return actionsProvidedOnKey;
    }

    public ActionName getActionOnKey(int keyCode) {
        return actionsProvidedOnKey.get(keyCode);
    }

    private int getKeyOnName(ActionName actionName) {
        for (Map.Entry<Integer, ActionName> entry : actionsProvidedOnKey.entrySet()) {
            if (entry.getValue().equals(actionName)) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public void change(int keyCode, ActionName actionName) {
        if (!actionsProvidedOnKey.containsValue(actionName)) {
            return;
        }
        int prevKeyCode = getKeyOnName(actionName);
        if (prevKeyCode == -1) {
            //TODO exception
        }
        actionsProvidedOnKey.remove(prevKeyCode);
        actionsProvidedOnKey.put(keyCode, actionName);
        usedKeys.remove(prevKeyCode);
        usedKeys.add(keyCode);
    }
}

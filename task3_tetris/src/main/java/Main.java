import Controller.KeyboardSettings;
import Model.Names.ActionName;
import View.Viewer;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        /*Viewer viewer = new Viewer();
        viewer.setVisible(true);*/
        KeyboardSettings settings = new KeyboardSettings();
        for (Map.Entry<Integer, ActionName> entry: settings.getActionsProvidedOnKey().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getName());
        }
    }
}

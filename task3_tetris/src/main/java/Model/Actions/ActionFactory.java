package Model.Actions;

import Model.Actions.PossibleActions.ActionInterface;
import Model.Names.ActionName;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ActionFactory {
    private static ActionFactory actionFactory = null;
    private final HashMap<ActionName, ActionInterface> actionTable;

    private ActionFactory() {
        actionTable = new HashMap<>();
        final String configFileName = "/actions.properties";
        Properties properties = new Properties();
        try {
            properties.load(ActionFactory.class.getResourceAsStream(configFileName));
        }
        catch (IOException ex) {
            //TODO normal exception
            ex.printStackTrace();
        }
        for (Map.Entry entry : properties.entrySet()) {
            String toActionName = (String) entry.getKey();
            String className = (String) entry.getValue();
            ActionName actionName;
            //TODO try catch
            actionName = new ActionName(toActionName);
            try {
                actionTable.put(actionName, (ActionInterface) Class.forName(className).getDeclaredConstructor().newInstance());
            }
            catch (ClassNotFoundException ex) {
                //TODO normal exception
                ex.printStackTrace();
            }
            catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static ActionFactory getInstance() {
        if (actionFactory == null) {
            actionFactory = new ActionFactory();
        }
        return actionFactory;
    }

    public ActionInterface getAction(ActionName actionName) {
        if (!actionTable.containsKey(actionName)) {
            //TODO exception
        }
        return actionTable.get(actionName);
    }
}

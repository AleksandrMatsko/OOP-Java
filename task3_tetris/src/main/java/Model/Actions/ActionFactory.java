package Model.Actions;

import Exceptions.NameExceptions.InvalidActionNameException;
import Exceptions.NotFoundExceptions.ActionNotFoundException;
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
            throw new RuntimeException("Problems with actions.properties");
        }
        for (Map.Entry entry : properties.entrySet()) {
            String toActionName = (String) entry.getKey();
            String className = (String) entry.getValue();
            ActionName actionName = null;
            try {
                actionName = new ActionName(toActionName);
            }
            catch (InvalidActionNameException ex) {
                throw new RuntimeException("Invalid format for ActionName in actions.properties");
            }

            try {
                actionTable.put(actionName, (ActionInterface) Class.forName(className).getDeclaredConstructor().newInstance());
            }
            catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                   IllegalAccessException | NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static ActionFactory getInstance() {
        if (actionFactory == null) {
            actionFactory = new ActionFactory();
        }
        return actionFactory;
    }

    public ActionInterface getAction(ActionName actionName) throws ActionNotFoundException {
        if (!actionTable.containsKey(actionName)) {
            throw new ActionNotFoundException();
        }
        return actionTable.get(actionName);
    }
}

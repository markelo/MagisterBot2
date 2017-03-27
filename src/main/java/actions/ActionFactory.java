package actions;

import org.apache.commons.lang.StringUtils;
import states.State;

/**
 * Created by sebastian on 25.02.17.
 */
public class ActionFactory {
    public static Action getActionBasedOnType(final State state) {
        final ActionType actionType = state.getActionType();
        if (actionType == ActionType.MENU) {
            return new MenuAction(state);
        } else if (actionType == ActionType.TEXT_INPUT) {
            return new UserInputAction(state, StringUtils::isAlpha);
        } else if (actionType == ActionType.CONTEXT_INITIALISATION) {
            return new ContextInitialisationAction(state);
        } else if (actionType == ActionType.ACCOUNT_NUMBER_INPUT) {
            return new UserInputAction(state, StringUtils::isNumeric);
        } else if (actionType == ActionType.LONG_TEXT_INPUT) {
            return new UserInputAction(state, StringUtils::isAlphaSpace);
        }
        throw new RuntimeException("Unsupported actions.Action type: " + actionType);
    }
}

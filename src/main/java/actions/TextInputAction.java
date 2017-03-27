package actions;

import actions.Action;
import org.apache.commons.lang.StringUtils;
import states.State;
import utils.Utils;

/**
 * Created by sebastian on 25.02.17.
 */
public class TextInputAction implements Action {
    private final UserInputAction action;

    public TextInputAction(final State state) {
        this.action = new UserInputAction(state, StringUtils::isAlpha);
    }
    public int perform() {
        return action.perform();
    }
}

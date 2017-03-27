package actions;

import context.ContextManager;
import org.apache.commons.lang.StringUtils;
import states.State;
import utils.Utils;

/**
 * Created by sebastian on 26.02.17.
 */
public class LongTextInputAction implements Action {
    private final UserInputAction action;

    public LongTextInputAction(final State state) {
        this.action = new UserInputAction(state, StringUtils::isAlphaSpace);
    }

    @Override
    public int perform() {
        return action.perform();
    }
}

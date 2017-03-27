package actions;

import context.ContextManager;
import states.State;
import utils.Utils;

/**
 * Created by sebastian on 26.02.17.
 */
public class UserInputAction implements Action {
    private final State state;
    private final InputValidator validator;

    public UserInputAction(final State state, final InputValidator validator) {
        this.state = state;
        this.validator = validator;
    }

    @Override
    public int perform() {
        printMessage();
        String userInput = Utils.getUserInputStatic();
        while (!validator.validate(userInput)) {
            userInput = Utils.getUserInputStatic();
        }
        ContextManager.putInformationIntoContext(state.getContextInformationName(), userInput);
        return 0;
    }

    private void printMessage() {
        Utils.printLineMessageToUser(state.getCommandForUser());
    }
}

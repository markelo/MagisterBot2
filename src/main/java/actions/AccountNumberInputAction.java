package actions;

import context.ContextManager;
import org.apache.commons.lang.StringUtils;
import states.State;
import utils.Utils;

/**
 * Created by sebastian on 25.02.17.
 */
public class AccountNumberInputAction implements Action {
    private final State state;

    public AccountNumberInputAction(final State state) {
        this.state = state;
    }

    @Override
    public int perform() {
        printMessage();
        String userInput = Utils.getUserInputStatic();
        while(!validateUserInput(userInput)) {
            userInput = Utils.getUserInputStatic();
        }
        ContextManager.putInformationIntoContext(state.getInformationName(), userInput);
        return 0;
    }

    private void printMessage() {
        Utils.printLineMessageToUser(state.getCommandForUser());
    }

    private boolean validateUserInput(final String userInput) {
        if (StringUtils.isNumeric(userInput)) {
            return true;
        }
        Utils.printLineMessageToUser("Incorrect account number. Please try again.");
        return false;
    }
}

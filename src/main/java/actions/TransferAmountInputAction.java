package actions;

import context.ContextManager;
import org.apache.commons.lang3.StringUtils;
import states.State;
import utils.Utils;

/**
 * Created by sebastian on 26.02.17.
 */
public class TransferAmountInputAction implements Action {
    private final State state;

    public TransferAmountInputAction(final State state) {
        this.state = state;
    }

    @Override
    public int perform() {
        printMessage();
        String userInput = Utils.getUserInputStatic();
        while (!validateUserInput(userInput)) {
            userInput = Utils.getUserInputStatic();
        }
        ContextManager.putInformationIntoContext(state.getInformationName(), userInput);
        return 0;
    }

    private void printMessage() {
        Utils.printLineMessageToUser(state.getCommandForUser());
    }

    private boolean validateUserInput(final String userInput) {
        return StringUtils.isNumeric(userInput);
    }
}

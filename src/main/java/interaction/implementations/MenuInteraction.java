package interaction.implementations;

import interaction.interfaces.Interaction;
import interaction.validators.Validator;
import utils.BotUtils;

/**
 * Created by sebastian on 09.12.16.
 */
public class MenuInteraction implements Interaction {
    @Override
    public String interact(final Validator validator, final String messageToUser) {
        while(true) {
            BotUtils.printMessage(messageToUser);
            final String userInput = BotUtils.getUserInput();
            if (validator.isVaildInput(userInput)) {
                return userInput;
            } else {
                BotUtils.printMessage("You have put invalid input. Try again.");
            }
        }
    }
}

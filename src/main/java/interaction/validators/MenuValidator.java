package interaction.validators;

import states.State;

/**
 * Created by sebastian on 10.12.16.
 */
public class MenuValidator implements Validator {

    private final State state;

    public MenuValidator(final State state) {
        this.state = state;
    }

    @Override
    public boolean isVaildInput(final String input) {
        return state.hasChildWithName(input);
    }
}

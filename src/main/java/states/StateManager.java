package states;

import context.Context;
import interaction.implementations.MenuInteraction;
import org.apache.commons.lang.StringUtils;
import utils.StateType;

/**
 * Created by sebastian on 11.12.16.
 */
public class StateManager {

    public State createInitalState() {
        final State rootState = new State(
                "Root state",
                new MenuInteraction(),
                () -> {},
                StringUtils::isAlphaSpace,
                "Choose what you want to do.",
                true,
                StateType.MENU
        );

        final State transferTypeState = new State(
                "Choose Transfer Type",
                new MenuInteraction(),
                () -> {},
                StringUtils::isAlpha,
                "Choose transfer type",
                true,
                StateType.MENU
        );

        final State countryTransferState = new State(
                "This is country transfer state",
                new MenuInteraction(),
                () -> Context.getInstance().initialiseContext("Country Transfer"),
                tmp -> false,
                "Some question",
                false,
                StateType.ACTION
        );

        final State nameInputState = new State(
                "Input name state",
                new MenuInteraction(),
                () -> {},
                StringUtils::isAlpha,
                "Please put your name",
                true,
                StateType.ACTION
        );
        final State surnameInputState = new State(
                "Surname input state",
                new MenuInteraction(),
                () -> {},
                StringUtils::isAlpha,
                "Please put your surname",
                true,
                StateType.ACTION
        );

        final State accountNumberInputState = new State(
                "Account number Input state",
                new MenuInteraction(),
                () -> {},
                StringUtils::isNumeric,
                "Please put account number",
                true,
                StateType.ACTION
        );

        surnameInputState.addChild(accountNumberInputState);
        nameInputState.addChild(surnameInputState);
        countryTransferState.addChild(nameInputState);
        transferTypeState.addChild(countryTransferState);
        rootState.addChild(transferTypeState);
        return rootState;
    }
}

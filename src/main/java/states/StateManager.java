package states;

import actions.ActionType;
import actions.UserInputAction;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import context.transfer.CountryTransferContextInformation;

import java.util.List;
import java.util.Optional;

/**
 * Created by sebastian on 25.02.17.
 */
public class StateManager {
    private final State initialState;

    public StateManager() {
        this.initialState = new State("Initial state", "Choose option from menu", ActionType.MENU, "", false);
        this.initialState.addChildStates(createInitialStateChilds());
    }

    private List<State> createInitialStateChilds() {
        return ImmutableList.of(
                createTransferChooseState(),
                createCardsMenuState()
        );
    }

    private State createTransferChooseState() {
        final State state = new State("Transfer Type", "Choose transfer type you want to do", ActionType.MENU, "", false);
        state.addChildState(builder()
                .withChildState(countryTransferState())
                .withChildState(nameInputState())
                .withChildState(surnameInputState())
                .withChildState(accountNumberState())
                .withChildState(transferTitleState())
                .withChildState(countryTransferFinalState())
                .build()
        );
        return state;
    }

    private State createCardsMenuState(){
        return new State("Cards Menu", "Choose which card you want to check", ActionType.MENU, "", false);
    }

    private State countryTransferState() {
        return new State("Country Transfer", "", ActionType.CONTEXT_INITIALISATION, "", false);
    }

    private State nameInputState() {
        return new State("Name Input", "Put your name", ActionType.TEXT_INPUT, "Name", false,
                Optional.of(CountryTransferContextInformation.RECIPIENT_NAME));
    }

    private State surnameInputState() {
        return new State("Surname Input", "Put your surnam", ActionType.TEXT_INPUT, "Surname", false,
                Optional.of(CountryTransferContextInformation.RECIPIENT_SURNAME));
    }

    private State accountNumberState() {
        return new State("Account Number", "Put account number", ActionType.ACCOUNT_NUMBER_INPUT, "Account Number", false,
                Optional.of(CountryTransferContextInformation.ACCOUNT_NUMBER));
    }

    private State reciverNameState() {
        return new State("Reciver Name", "Put reciver name", ActionType.TEXT_INPUT, "Reciever name", false);
    }

    private State transferTitleState() {
        return new State("Transfer Title", "Put transfer title", ActionType.TEXT_INPUT, "Transfer title", false,
                Optional.of(CountryTransferContextInformation.TRANSFER_TITLE));
    }

    public State getInitialState() {
        //This will be initial state
        return initialState;
    }

    private State countryTransferFinalState() {
        return new State("Country Transfer Final State", "", ActionType.AUTOMATIC, "Some information", true);
    }

    private StateBuilder builder() {
        return new StateBuilder();
    }

    private class StateBuilder {
        private List<State> states;

        public StateBuilder() {
            states = Lists.newLinkedList();
        }

        public StateBuilder withChildState(final State state) {
            states.add(state);
            return this;
        }

        public State build() {
            final State rootState = states.get(0);
            State state = rootState;
            for(int i = 1; i < states.size(); i++) {
                final State nextState = states.get(i);
                state.addChildState(nextState);
                state = nextState;
            }
            return rootState;
        }
    }
}

package states;

import context.initialisers.Initializer;
import interaction.interfaces.Interaction;
import interaction.validators.Validator;
import utils.StateType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sebastian on 05.12.16.
 */
public class State {
    private final String name;
    private final List<State> childStates;
    private final Interaction interaction;
    private final Initializer contextInitializer;
    private final Validator validator;
    private final String questionToUser;
    private final boolean interactive;
    private final StateType type;

    State(
            final String name,
            final Interaction interaction,
            final Initializer contextInitialiser,
            final Validator validator,
            final String questionToUser,
            final boolean interactive,
            final StateType type
    ) {
        this.name = name;
        this.childStates = new ArrayList<>();
        this.interaction = interaction;
        this.contextInitializer = contextInitialiser;
        this.validator = validator;
        this.questionToUser = questionToUser;
        this.interactive = interactive;
        this.type = type;
    }


    public void addChild(final State state) {
        childStates.add(state);
    }

    private String getName() {
        return name;
    }

    /**
     * This method is used by state to prepare for interaction with user, run interaction and retrieve
     * next state to go to.
     * @return Next state that we need to move to
     */
    public State mainMethod() {
        contextInitializer.initialise();
        final String interactionResult = interaction.interact(validator, getMessageForUser());
        if (type == StateType.ACTION) {
            return childStates.get(0);
        } else {
            return childStates.stream().filter(state -> state.getName().equals(interactionResult)).findFirst().orElseGet(null);
        }
    }

    private String getMessageForUser() {
        final StringBuilder builder = new StringBuilder();
        if (type == StateType.MENU) {
            childStates.stream()
                    .map(state -> state.getName() + '\n')
                    .forEach(builder::append);
        }
        builder.append(questionToUser).append('\n');
        return builder.toString();
    }

    private int getChildsNumber() {
        return childStates.size();
    }

    public boolean hasChildWithName(final String name) {
        return childStates.stream()
                .map(State::getName)
                .filter(stateName -> stateName.equals(name))
                .findAny()
                .isPresent();
    }

    public boolean isInteractive() {
        return interactive;
    }
}

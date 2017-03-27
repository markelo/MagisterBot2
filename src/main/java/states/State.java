package states;

import actions.ActionType;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;

/**
 * Created by sebastian on 25.02.17.
 */
public class State {
//    private static WebDriver webDriver = new ChromeDriver();
    private final String description;
    private final String commandForUser;
    private final List<State> childStates;
    private final ActionType actionType;
    private final boolean finall;
    private final String informationName;
    private final Optional<String> contextInformationName;

    public State(
            final String description,
            final String commandForUser,
            final ActionType actionType,
            final String informationName,
            final boolean finall
    ) {
        this(description, commandForUser, actionType, informationName, finall, Optional.empty());
    }

    public State(
            final String description,
            final String commandForUser,
            final ActionType actionType,
            final String informationName,
            final boolean finall,
            final Optional<String> contextInformationName
    ) {
        this.description = description;
        this.actionType = actionType;
        this.commandForUser = commandForUser;
        this.childStates = Lists.newArrayList();
        this.finall = finall;
        this.informationName = informationName;
        this.contextInformationName = contextInformationName;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void addChildState(final State state) {
        childStates.add(state);
    }

    public void addChildStates(final List<State> newChildStates) {
        childStates.addAll(newChildStates);
    }

    public List<State> getChildStates() {
        return childStates;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFinall() {
        return finall;
    }

    public State getChildState(final int stateNumber) {
        if (stateNumber >= childStates.size()) {
            throw new RuntimeException("states.State number was bigger than child state list size");
        }
        return childStates.get(stateNumber);
    }

    public String getCommandForUser() {
        return commandForUser;
    }

    public String getInformationName() {
        return informationName;
    }

    public String getContextInformationName() {
        return contextInformationName.orElse("");
    }

//    public WebDriver getWebDriver() {
//        return webDriver;
//    }
}

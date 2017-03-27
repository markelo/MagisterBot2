package actions;

import states.State;
import utils.Utils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sebastian on 25.02.17.
 */
public class MenuAction implements Action {
    private final State state;
    private final List<String> menuEntries;

    public MenuAction(final State state) {
        this.state = state;
        this.menuEntries = getMenuEntries(state);
    }

    public int perform() {
        printMenu();
        String userInput = Utils.getUserInputStatic();
        while(!validateInput(userInput)) {
            userInput = Utils.getUserInputStatic();
        }
        return menuEntries.indexOf(userInput);
    }

    private void printMenu() {
        for (int i = 0; i < menuEntries.size(); i++) {
            Utils.printLineMessageToUser(String.format(
                    "%d. %s.",
                    i + 1,
                    menuEntries.get(i)
            ));
        }
        Utils.printLineMessageToUser(state.getCommandForUser());
    }

    private boolean validateInput(final String input) {
        if (menuEntries.contains(input)) {
            return true;
        }
        Utils.printLineMessageToUser("I haven't found such entry in menu. Please repeat.");
        return false;
    }

    private List<String> getMenuEntries(final State state) {
        return state.getChildStates()
                .stream()
                .map(State::getDescription)
                .collect(Collectors.toList());
    }
}

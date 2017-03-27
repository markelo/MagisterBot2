package actions;

import context.ContextManager;
import states.State;

/**
 * Created by sebastian on 25.02.17.
 */
public class ContextInitialisationAction implements Action {
    private final State state;

    public ContextInitialisationAction(final State state) {
        this.state = state;
    }

    @Override
    public int perform() {
        ContextManager.createNewContext(state.getDescription());
        return 0;
    }
}

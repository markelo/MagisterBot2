import states.State;
import states.StateManager;

/**
 * Created by sebastian on 11.01.17.
 */
public class Main {

    public static void main(String[] args) {
        final StateManager manager = new StateManager();
         State state = manager.createInitalState();

        while(state != null) {
            state = state.mainMethod();
        }
    }
}

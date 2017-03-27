package context;

import java.util.Set;

/**
 * Created by sebastian on 25.02.17.
 */
public class ContextManager {
    private static Context currentContext;

    public static void createNewContext(final String contextName) {
        currentContext = new Context(contextName);
    }

    public static void putInformationIntoContext(final String name, final String value) {
        currentContext.putData(name, value);
    }

    public static String getInformationFromContext(final String name) {
        return currentContext.getData(name);
    }

    public static Set<String> getInformationNamesFromContext() {
        return currentContext.getInformationNames();
    }
}

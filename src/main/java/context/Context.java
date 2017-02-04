package context;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by sebastian on 06.12.16.
 */

public class Context {
    private static Context instance = new Context();
    private List<String> fieldNames;
    private int currentField;
    private Map<String, String> fieldValues;

    private Context() {
        this.fieldValues = Collections.emptyMap();
        this.currentField = 0;
        this.fieldNames = Collections.emptyList();
    }

    public static Context getInstance() {
        return instance;
    }

    public void reset() {
        currentField = 0;
        fieldNames.clear();
        fieldValues.clear();
    }

    public void initialiseContext(final String contextName) {
        reset();
        if (contextName.equals("Country Transfer")) {
            fieldNames.add("Name");
            fieldNames.add("Surname");
            fieldNames.add("Account Number");
        }
    }

    public void setNextFieldValue(final String value) {
        final String key = fieldNames.get(currentField);
        fieldValues.put(key, value);
        currentField++;
    }
}

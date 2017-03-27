package context;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Set;

/**
 * Created by sebastian on 25.02.17.
 */
public class Context {
    private String contextName;
    private Map<String, String> contextData;

    public Context(final String contextName) {
        this.contextName = contextName;
        this.contextData = Maps.newHashMap();
    }

    public void putData(final String name, final String value) {
        contextData.put(name, value);
    }

    public String getData(final String name) {
        return contextData.get(name);
    }

    public Set<String> getInformationNames() {
        return contextData.keySet();
    }
}

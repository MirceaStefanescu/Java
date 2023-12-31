package org.templateClass;

import java.util.HashMap;
import java.util.Map;

public class Template {

    private Map<String, String> placeholdersValuesMapping;
    private String templateText;

    {
        placeholdersValuesMapping = new HashMap<>();
    }

    public Template(String templateText) {
        this.templateText = templateText;
    }

    public void set(String placeholderName, String variableValue) {
        placeholdersValuesMapping.put(placeholderName, variableValue);
    }

    public String createEmailText() {
        String result = templateText;
        for (Map.Entry<String, String> entry : placeholdersValuesMapping.entrySet()) {
            String regex = "\\$\\{" + entry.getKey() + "\\}";
            result = result.replaceAll(regex, entry.getValue());
        }

        if (result.contains("${") && result.contains("}")) {
            throw new RuntimeException("Not enough values for template");
        }

        return result;
    }
}

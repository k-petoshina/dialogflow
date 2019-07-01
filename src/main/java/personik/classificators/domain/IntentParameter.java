package personik.classificators.domain;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntentParameter {

    private String name;
    private String displayName;
    private String value;
    private String entityTypeDisplayName;
    private List<String> prompts;


    // todo - getters/setters and so on..

    public IntentParameter(String name, String displayName, String value, String entityTypeDisplayName, List<String> prompts) {
        this.name = name;
        this.displayName = displayName;
        this.value = value;
        this.entityTypeDisplayName = entityTypeDisplayName;
        this.prompts = prompts;
    }

    public IntentParameter(String name, String displayName, String value, String entityTypeDisplayName) {
        this.name = name;
        this.displayName = displayName;
        this.value = value;
        this.entityTypeDisplayName = entityTypeDisplayName;
    }

    public void addPrompt(String prompt) {
        if (prompts == null) {
            prompts = new ArrayList<String>();
        }
        prompts.add(prompt);
    }

    public static IntentParameter fromJson(JSONObject obj) {

        IntentParameter parameter = new IntentParameter(obj.getString("name"), obj.getString("displayName"), obj.getString("value"),
                obj.getString("entityTypeDisplayName"));

        JSONArray prompts = obj.getJSONArray("prompts");
        for (Object prompt : prompts) {
            parameter.addPrompt((String) prompt);
        }
        return parameter;
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("name", this.name);
        obj.put("displayName", this.displayName);
        obj.put("value", this.value);
        obj.put("entityTypeDisplayName", this.entityTypeDisplayName);

        JSONArray pr = new JSONArray();
        for (String prompt : prompts) {
            pr.put(prompt);
        }
        obj.put("prompts", pr);
        return obj;
    }
}

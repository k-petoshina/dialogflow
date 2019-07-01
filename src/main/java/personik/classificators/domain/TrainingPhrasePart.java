package personik.classificators.domain;

import org.json.JSONObject;

public class TrainingPhrasePart {

    private String text;
    private String entityType;
    private String alias;
    private Boolean userDefined;

    public TrainingPhrasePart(String text) {
        this.text = text;
    }

    public TrainingPhrasePart(String text, String entityType, String alias, Boolean userDefined) {
        this.text = text;
        this.entityType = entityType;
        this.alias = alias;
        this.userDefined = userDefined;
    }

    public void entityType(String entityType) {
        this.entityType = entityType;
    }

    public void alias(String alias) {
        this.alias = alias;
    }

    public void userDefined(Boolean userDefined) {
        this.userDefined = userDefined;
    }

    public static TrainingPhrasePart fromJson(JSONObject json) {
        TrainingPhrasePart part = new TrainingPhrasePart(json.getString("text"));
        if (json.has("entityType")) {
            part.entityType(json.getString("entityType"));
        }
        if (json.has("alias")) {
            part.alias(json.getString("alias"));
        }
        if (json.has("userDefined")) {
            part.userDefined(json.getBoolean("userDefined"));
        }
        return part;
    }


    public JSONObject toJson()
    {
        JSONObject obj = new JSONObject();
        obj.put("text", this.text);
        obj.put("entityType", this.entityType);
        obj.put("alias", this.alias);
        obj.put("userDefined", this.userDefined);
        return obj;
    }
}

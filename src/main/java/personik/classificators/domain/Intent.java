package personik.classificators.domain;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Intent {

    private String name;
    private String displayName;
    private long priority;
    private Boolean mlEnabled;
    private List<TrainingPhrase> trainingPhrases;

    // todo - getters/setters and so on..
    private String action;
    private List<IntentParameter> parameters;
    private List<IntentMessages> messages;


    public Intent(String name) {
        this.name = name;
    }

    public Intent(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public void priority(long priority) {
        this.priority = priority;
    }

    public void mlEnabled(Boolean mlEnabled) {
        this.mlEnabled = mlEnabled;
    }

    public void trainingPhrases(List<TrainingPhrase> trainigPhrases) {
        this.trainingPhrases = trainigPhrases;
    }

    public void action(String action) {
        this.action = action;
    }

    public String name() {
        return name;
    }

    public String displayName() {
        return displayName;
    }

    public List<TrainingPhrase> trainingPhrases() {
        return trainingPhrases;
    }

    public void addTrainingPhrase(TrainingPhrase phrase) {
        if (trainingPhrases == null) {
            trainingPhrases = new ArrayList<TrainingPhrase>();
        }
        trainingPhrases.add(phrase);
    }

    public void addParameter(IntentParameter pr) {
        if (parameters == null) {
            parameters = new ArrayList<IntentParameter>();
        }
        parameters.add(pr);
    }

    public void addMessage(IntentMessages msg) {
        if (messages == null) {
            messages = new ArrayList<IntentMessages>();
        }
        messages.add(msg);
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("name", this.name);
        obj.put("displayName", this.displayName);
        obj.put("priority", this.priority);
        obj.put("mlEnabled", this.mlEnabled);

        JSONArray phrases = new JSONArray();
        if (this.trainingPhrases != null && !this.trainingPhrases.isEmpty()) {
            for (TrainingPhrase trainingPhrase : trainingPhrases) {
                phrases.put(trainingPhrase.toJSON());
            }
        }
        obj.put("trainingPhrases", phrases);

        JSONArray prs = new JSONArray();
        if (this.parameters != null && !this.parameters.isEmpty()) {
            for (IntentParameter param : parameters) {
                prs.put(param.toJson());
            }
        }
        obj.put("parameters", prs);

        JSONArray msges = new JSONArray();
        if (this.messages != null && !this.messages.isEmpty()) {
            for (IntentMessages msg : messages) {
                msges.put(msg.toJson());
            }
        }
        obj.put("messages", msges);

        return obj;
    }


    public static Intent fromJson(JSONObject obj) {
        Intent intent = new Intent(obj.getString("name"), obj.getString("displayName"));
        intent.priority(obj.getLong("priority"));
        intent.mlEnabled(obj.getBoolean("mlEnabled"));
        if (obj.has("action")) {
            intent.action(obj.getString("action"));
        }

        if (obj.has("trainingPhrases")) {
            JSONArray array = obj.getJSONArray("trainingPhrases");
            if (array.length() > 0) {
                for (Object o : array) {
                    intent.addTrainingPhrase(TrainingPhrase.fromJson((JSONObject) o));
                }
            }
        }

        if (obj.has("parameters")) {
            JSONArray array = obj.getJSONArray("parameters");
            if (array.length() > 0) {
                for (Object o : array) {
                    intent.addParameter(IntentParameter.fromJson((JSONObject) o));
                }
            }
        }

        if (obj.has("messages")) {
            JSONArray array = obj.getJSONArray("messages");
            if (array.length() > 0) {
                for (Object o : array) {
                    intent.addMessage(IntentMessages.fromJson((JSONObject) o));
                }
            }
        }
        return intent;
    }


}

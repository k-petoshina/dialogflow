package personik.classificators.domain;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TrainingPhrase {

    private String name;
    private String type;
    private List<TrainingPhrasePart> parts;

    public TrainingPhrase(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public TrainingPhrase(String name, String type, String phrase) {
        this.name = name;
        this.type = type;
        addTrainingPart(new TrainingPhrasePart(phrase));
    }

    public void addTrainingPart(TrainingPhrasePart part) {
        if (parts == null) {
            parts = new ArrayList<TrainingPhrasePart>();
        }
        parts.add(part);

    }

    public static TrainingPhrase fromJson(JSONObject json) {
        TrainingPhrase phrase = new TrainingPhrase(json.getString("name"),
                json.getString("type"));
        JSONArray parts = json.getJSONArray("parts");
        if (parts.length() > 0) {
            for (Object part : parts) {
                phrase.addTrainingPart(TrainingPhrasePart.fromJson((JSONObject) part));
            }
        }
        return phrase;
    }

    public JSONObject toJSON()
    {
        JSONObject obj = new JSONObject();

        obj.put("name", this.name);
        obj.put("type", this.type);
        if (parts!=null && !parts.isEmpty())
        {
            JSONArray array = new JSONArray();
            for (TrainingPhrasePart part : parts) {
                array.put(part.toJson());
            }
            obj.put("parts", array);
        }

        //todo
        return obj;
    }
}

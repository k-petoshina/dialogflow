package personik.classificators.domain;

import org.json.JSONObject;

public class IntentMessages {

    private String text;

    // todo - getters/setters and so on..


    public IntentMessages() {
    }

    public IntentMessages(String text) {
        this.text = text;
    }

    public static IntentMessages fromJson(JSONObject obj) {
//        if (obj.get("text")!=null) {
//            return new IntentMessages(obj.getString("text"));
//        }else
//        {
//            return new IntentMessages();
//        }
        return new IntentMessages();
    }

    public JSONObject toJson() {
        if (text!=null) {
            return new JSONObject().put("text", text);
        }else
        {
            return new JSONObject().put("text", new JSONObject());
        }
    }
}

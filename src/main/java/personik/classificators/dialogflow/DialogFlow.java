package personik.classificators.dialogflow;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import personik.classificators.domain.ClassificationResult;
import personik.classificators.domain.Entity;
import personik.classificators.domain.Intent;
import personik.classificators.domain.TrainingPhrase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class DialogFlow {
    private final static Logger log = LoggerFactory.getLogger(DialogFlow.class);

    private final DialogFlowConnector connector;
    private String project;

    private final static String PROXY_HOST = "dialogflow.proxy.host";
    private final static String PROXY_PORT = "dialogflow.proxy.port";
    private final static String PROXY_USER = "dialogflow.proxy.user";
    private final static String PROXY_PASS = "dialogflow.proxy.pass";


    public DialogFlow(String project) {

        this.connector = new DialogFlowConnector();
        this.project = project;
    }


    public ClassificationResult detectIntent(String text, String session) throws IOException {

        log.debug("Detect intent for text: {}", text );

        ClassificationResult classificationResult = null;
        JSONObject textNode = new JSONObject();
        textNode.put("text", text);
        textNode.put("languageCode", "ru");

        JSONObject root = new JSONObject();
        root.put("queryInput", new JSONObject().put("text", textNode));

        String url = "projects/" + project + "/agent/sessions/" + session + ":detectIntent";
        JSONObject jsonObject = connector.sendPostRequest(url, root, getKey());
        if (jsonObject == null) {
            // todo
            return null;
        }
        if (jsonObject.has("queryResult")) {

            String intentDispName = jsonObject.getJSONObject("queryResult").getJSONObject("intent").getString("displayName");
            String intentName = jsonObject.getJSONObject("queryResult").getJSONObject("intent").getString("name");
            classificationResult = new ClassificationResult(new Intent(intentName, intentDispName));
            JSONObject parameters = jsonObject.getJSONObject("queryResult").getJSONObject("parameters");
            for (String entityName : parameters.keySet()) {
                classificationResult.addEntity(new Entity(entityName, parameters.getString(entityName)));
            }
        } else {
            // todo
        }


        return classificationResult;
    }

    public void train() throws IOException {
        log.debug("Train!");
        connector.sendPostRequest("projects/" + project + "/agent:train", null, getKey());
    }


    public void intentsList() throws IOException {
        log.debug("Get intents list");
        JSONObject jsonObject = connector.sendGetRequest("projects/" + project + "/agent/intents", getKey(), null);
        log.debug("result is {}", jsonObject);

    }

    public Intent getIntent(String name) throws IOException {

        log.debug("Get Intent: {} ", name);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("languageCode", "ru");
        params.put("intentView", "INTENT_VIEW_FULL");

        JSONObject res = connector.sendGetRequest("projects/" + project + "/agent/intents/" + name, getKey(),params);
        if (res.has("name")) {
            return Intent.fromJson(res);
        } else {
            // todo - response doesn't contain intent
        }
        return null;

    }

    public void addTrainPhrase(String intentName, String phrase) throws IOException {

        log.debug("Add training phrase for intent: {}, phrase {}", intentName, phrase);
        Intent intent = getIntent(intentName);
        if (intent != null) {
            intent.addTrainingPhrase(new TrainingPhrase(UUID.randomUUID().toString(), "EXAMPLE", phrase));
            JSONObject jsonObject = connector.sendPatchRequest("projects/" + project + "/agent/intents/" + intentName, intent.toJson(), getKey());

            log.debug("Result of adding new train phrase: {}", jsonObject );

            // todo check jsonObject in response
            train();
        } else {
            // todo
        }
    }

    private String getKey() throws IOException {
        ServiceAccountCredentials serviceAccountCredentials = ServiceAccountCredentials.fromStream(new FileInputStream("/var/apps/dialogflow/newagent-aqmkfe-29416ba2cd2e.json"));
        GoogleCredentials scopedRequired = serviceAccountCredentials.createScoped(Collections.singletonList("https://www.googleapis.com/auth/dialogflow"));
        return scopedRequired.refreshAccessToken().getTokenValue();
    }
}

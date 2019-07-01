package personik.classificators.dialogflow;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mashape.unirest.http.Unirest;

import java.util.Map;


public class DialogFlowConnector {

    private final static Logger log = LoggerFactory.getLogger(DialogFlowConnector.class);
    private final static String DIALOGFLOW_API_V2BETA1 = "https://dialogflow.googleapis.com/v2beta1/";


    public DialogFlowConnector() {
    }

    public JSONObject sendPostRequest(String url,
                                      JSONObject body, String authToken) {

        HttpResponse response = null;
        try {

            if (body != null) {
                response = Unirest.
                        post(DIALOGFLOW_API_V2BETA1 + url).
                        header("Authorization", "Bearer " + authToken).
                        header("Content-Type", "application/json; charset=utf-8").
                        body(body).asString();
            } else {
                response = Unirest.
                        post(DIALOGFLOW_API_V2BETA1 + url).
                        header("Authorization", "Bearer " + authToken).
                        header("Content-Type", "application/json; charset=utf-8").asJson();
            }


            log.debug(response.getBody().toString());

            if (response.getStatus() == 200) {
                return new JSONObject(response.getBody().toString());

            } else {
                // todo
            }

        } catch (UnirestException e) {
            // TODO
            e.printStackTrace();
        }
        return null;

    }

    public JSONObject sendPatchRequest(String url,
                                       JSONObject body, String authToken) {

        HttpResponse response = null;
        try {
            response = Unirest.
                    patch(DIALOGFLOW_API_V2BETA1 + url).
                    header("Authorization", "Bearer " + authToken).
                    header("Content-Type", "application/json; charset=utf-8").
                    body(body).asString();

            log.debug(response.getBody().toString());

            if (response.getStatus() == 200) {
                return new JSONObject(response.getBody().toString());

            } else {
                // todo
            }

        } catch (UnirestException e) {
            // TODO
            e.printStackTrace();
        }
        return null;

    }

    public JSONObject sendGetRequest(String url, String authToken, Map<String, Object> queryParams) {

        HttpResponse response = null;
        try {
            response = Unirest.
                    get(DIALOGFLOW_API_V2BETA1 + url).
                    header("Authorization", "Bearer " + authToken).
                    header("Content-Type", "application/json; charset=utf-8").
                    queryString(queryParams).
                    asJson();

            log.debug(response.getBody().toString());

            if (response.getStatus() == 200) {
                return new JSONObject(response.getBody().toString());

            } else {
                // todo
            }

        } catch (UnirestException e) {
            // TODO
            e.printStackTrace();
        }
        return null;

    }
}

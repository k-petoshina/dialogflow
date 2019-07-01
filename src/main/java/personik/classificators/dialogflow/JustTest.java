package personik.classificators.dialogflow;


import java.io.IOException;

public class JustTest {

    public static void main(String[] args) {

        String projectName = "newagent-aqmkfe";
        String sessionId="aqwertyuiopasdfghjklzxcvbnmqwertyup";
//        String userInput = "Мои наушники сломались";
        String userInput = "Tomato are delicious";

        DialogFlow dialogFlow = new DialogFlow(projectName);
        try {
//            dialogFlow.detectIntent(userInput, sessionId);

//            dialogFlow.intentsList();
//            dialogFlow.getIntent("29b1656a-b34e-445f-ae5d-2eb3d40520d1");
            dialogFlow.addTrainPhrase("29b1656a-b34e-445f-ae5d-2eb3d40520d1", "Очень холодный ветер из кондиционера");
//            dialogFlow.addTrainPhrase("1f3084d2-e2f6-493d-8df2-973aa11b7a31", userInput);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

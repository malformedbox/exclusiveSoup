package tests.chatBot;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;

public class ChatBotValidator {
    public static boolean isResponseValid(String chatbotResponse, String expectedResponse) throws Exception {
        String apiUrl = "https://api-inference.huggingface.co/models/sentence-transformers/all-MiniLM-L6-v2";
        String apiKey = System.getProperty("huggingFaceApi");  // Replace with your Hugging Face API Key

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(apiUrl);
            request.setHeader("Authorization", "Bearer " + apiKey);
            request.setHeader("Content-Type", "application/json");

            JSONObject json = new JSONObject();
            JSONObject inputObject = new JSONObject();

            inputObject.put("source_sentence", chatbotResponse);
            inputObject.put("sentences", new JSONArray().put(expectedResponse));

            json.put("inputs", inputObject);

            System.out.println("Generated JSON: " + json.toString(2)); // Pretty-print for readability

            request.setEntity(new StringEntity(json.toString(), StandardCharsets.UTF_8));
            String response = EntityUtils.toString(client.execute(request).getEntity(), StandardCharsets.UTF_8);
            System.out.println("Raw API Response: " + response);
            try {
                // Parse the response as a JSONArray
                JSONArray jsonResponse = new JSONArray(response);

                // Extract the similarity score
                double similarityScore = jsonResponse.getDouble(0); // Extract first value from the array
                similarityScore = Math.round(similarityScore * 100.0) / 100.0;

                System.out.println("Similarity Score: " + similarityScore);
                return similarityScore >= 0.8; // Test passes if similarity is greater than 80%
            } catch (JSONException e) {
                System.err.println("Error parsing JSON response: " + e.getMessage());
                return false; // Fail the test if response is invalid
            }
        }
    }

    public static boolean isTextPositiveOrNegative(String chatbotResponse, String expectedResponse) throws Exception {
        String apiUrl = "https://api-inference.huggingface.co/models/sentence-transformers/all-MiniLM-L6-v2";
        String apiKey = System.getProperty("huggingFaceApi");  // Replace with your Hugging Face API Key

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(apiUrl);
            request.setHeader("Authorization", "Bearer " + apiKey);
            request.setHeader("Content-Type", "application/json");

            JSONObject json = new JSONObject();
            json.put("inputs", "I love this product!");

            System.out.println("Generated JSON: " + json.toString(2)); // Pretty-print for readability

            request.setEntity(new StringEntity(json.toString(), StandardCharsets.UTF_8));
            String response = EntityUtils.toString(client.execute(request).getEntity(), StandardCharsets.UTF_8);
            System.out.println("Raw API Response: " + response);
            try {
                // Parse the response as a JSONArray
                JSONArray jsonResponse = new JSONArray(response);

                // Extract the similarity score
                double similarityScore = jsonResponse.getDouble(0); // Extract first value from the array
                similarityScore = Math.round(similarityScore * 100.0) / 100.0;

                System.out.println("Similarity Score: " + similarityScore);
                return similarityScore >= 0.8; // Test passes if similarity is greater than 80%
            } catch (JSONException e) {
                System.err.println("Error parsing JSON response: " + e.getMessage());
                return false; // Fail the test if response is invalid
            }
        }
    }

    @Test(enabled = false)
    public void validateChatBotResponse() throws Exception {
        //Test case: Rude vs Polite. Result: similarity score of: 0.2749805748462677
//        String chatBotResponse = "Hey. What do you want?";
//        String expectedResponse = "Hello! How can I assist you";

        //Test case: Responses are similar. Result: similarity score of: 0.7975531220436096
        //Rounded and set >= 0.8 to qualify as similar
//        String chatBotResponse = "What is your account number?";
//        String expectedResponse = "Can you provide your account number?";

        //Test case: Responses are different. Result: similarity score of: 0.21540012955665588
//        String chatBotResponse = "Hey. What do you want?";
//        String expectedResponse = "Hello! How can I assist you";

        //Test case: Opposites in a similar sentence. Result: similarity score: 0.8683011531829834
//        String chatBotResponse = "Turn left.";
//        String expectedResponse = "Turn right.";

        //Test case: Directing, but with varying intensity. Result: similarity score: 0.9152421355247498
        String chatBotResponse = "You definitely have to walk 100 feet.";
        String expectedResponse = "It's recommended to walk 100 feet.";

        boolean isValid = isResponseValid(chatBotResponse, expectedResponse);

        Assert.assertTrue(isValid, "Chatbot response is incorrect.");
    }
}

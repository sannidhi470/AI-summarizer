package com.example.AI_summarizer.Service;

import com.example.AI_summarizer.Model.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AISummarizerService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public Response summarizeText(String text) {

        String prompt = buildPrompt(text);
        String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-4o-mini");

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "user", "content", prompt));

        body.put("messages", messages);
        body.put("temperature", 0.2);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(
                url,
                request,
                Map.class
        );

        Map<String, Object> responseBody = responseEntity.getBody();

        List<Map<String, Object>> choices =
                (List<Map<String, Object>>) responseBody.get("choices");

        Map<String, Object> message =
                (Map<String, Object>) choices.get(0).get("message");

        String content = (String) message.get("content");

        return parseAIResponse(content);
    }

    private String buildPrompt(String text) {
        return """
        You are an AI assistant that summarizes text and extracts action items.

        Return ONLY valid JSON in this format:
        {
          "summary": "string",
          "action_items": ["string", "string", "string"]
        }

        Text:
        """ + text;
    }

    private Response parseAIResponse(String content) {

        String summary = "";
        List<String> actions = new ArrayList<>();

        try {
            org.json.JSONObject json = new org.json.JSONObject(content);

            summary = json.getString("summary");

            org.json.JSONArray arr = json.getJSONArray("action_items");

            for (int i = 0; i < arr.length(); i++) {
                actions.add(arr.getString(i));
            }

        } catch (Exception e) {
            summary = "Parsing failed, raw output: " + content;
        }

        return new Response(summary, actions);
    }
}
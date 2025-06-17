package com.research.assistant.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.research.assistant.beans.GeminiResponseBean;
import com.research.assistant.beans.ResearchRequestBean;
import com.research.assistant.service.ResearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
@Service
public class ActionServiceImpl implements ResearchService {

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public ActionServiceImpl(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.build();
        this.objectMapper = objectMapper;
    }

    @Override
    public String processContent(ResearchRequestBean request) {
        String prompt = buildPrompt(request);

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        String response = webClient.post()
                .uri(geminiApiUrl + geminiApiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return extractTextFromResponse(response);
    }

    private String extractTextFromResponse(String response) {
        try {
            GeminiResponseBean geminiResponseBean = objectMapper.readValue(response, GeminiResponseBean.class);
            if (geminiResponseBean.getCandidates() != null && !geminiResponseBean.getCandidates().isEmpty()) {
                GeminiResponseBean.Candidate firstCandidate = geminiResponseBean.getCandidates().get(0);
                if (firstCandidate.getContent() != null &&
                        firstCandidate.getContent().getParts() != null &&
                        !firstCandidate.getContent().getParts().isEmpty()) {
                    return firstCandidate.getContent().getParts().get(0).getText();
                }
            }
            return "no valid response found";
        } catch (Exception e) {
            return "Error parsing: " + e.getMessage();
        }
    }

    private String buildPrompt(ResearchRequestBean request) {
        StringBuilder prompt = new StringBuilder();
        switch (request.getOperation()) {
            case "summarize":
                prompt.append("Provide a clear and concise summary of a prompt in a few sentences:\n\n");
                break;
            case "suggest":
                prompt.append("Based on the following content, suggest related topics");
                break;
            default:
                throw new IllegalArgumentException("Unknown request: " + request.getOperation());
        }
        prompt.append(request.getContent());
        return prompt.toString();
    }
}

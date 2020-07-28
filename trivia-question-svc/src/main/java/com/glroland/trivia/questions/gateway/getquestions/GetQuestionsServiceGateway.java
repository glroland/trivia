package com.glroland.trivia.questions.gateway.getquestions;

import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class GetQuestionsServiceGateway 
{
    @Value("${getQuestionsServiceGatewayUrl}")
    private String url;

    public TriviaQuestions getTriviaQuestions(int categoryId, int numQuestions)
    {
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder
            .fromUriString(url)
            .queryParam("category", Integer.toString(categoryId))
            .queryParam("type", "multiple")
            .queryParam("amount", Integer.toString(numQuestions));

        TriviaQuestions triviaCategories = restTemplate.getForObject(
            builder.toUriString(), TriviaQuestions.class);

        return triviaCategories;
    }    
}

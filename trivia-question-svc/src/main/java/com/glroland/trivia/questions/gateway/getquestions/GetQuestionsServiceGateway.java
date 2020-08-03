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
            .queryParam("type", "multiple")
            .queryParam("amount", Integer.toString(numQuestions));

        if (categoryId > 0)
        {
            builder.queryParam("category", Integer.toString(categoryId));
        }

        TriviaQuestions triviaCategories = restTemplate.getForObject(
            builder.toUriString(), TriviaQuestions.class);

        return triviaCategories;
    }    
}

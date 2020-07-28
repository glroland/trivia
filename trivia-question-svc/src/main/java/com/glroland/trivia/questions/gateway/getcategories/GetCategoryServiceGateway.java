package com.glroland.trivia.questions.gateway.getcategories;

import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class GetCategoryServiceGateway 
{
    @Value("${getCategoryServiceGatewayUrl}")
    private String url;

    public TriviaCategories getTriviaCategories()
    {
        RestTemplate restTemplate = new RestTemplate();

        TriviaCategories triviaCategories = restTemplate.getForObject(
            url, TriviaCategories.class);

        return triviaCategories;
    }    
}

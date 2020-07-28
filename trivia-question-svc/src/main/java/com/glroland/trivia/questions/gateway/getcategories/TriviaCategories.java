package com.glroland.trivia.questions.gateway.getcategories;

import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TriviaCategories {
    
    @JsonProperty("trivia_categories")
    List<TriviaCategory> triviaCategories = new ArrayList<TriviaCategory>();

    public List<TriviaCategory> getTriviaCategories() {
        return triviaCategories;
    }

    public void setTriviaCategories(List<TriviaCategory> triviaCategories) {
        this.triviaCategories = triviaCategories;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        if (triviaCategories != null)
        {
            boolean firstItem = true;
            for (TriviaCategory triviaCategory : triviaCategories)
            {
                if (!firstItem)
                    builder.append(",\n");
                builder.append(triviaCategory.toString());
                firstItem = false;
            }            
        }
        builder.append("\n}");
        return builder.toString();
  }
}

package com.glroland.trivia.questions.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import com.glroland.trivia.questions.gateway.getcategories.GetCategoryServiceGateway;
import com.glroland.trivia.questions.gateway.getcategories.TriviaCategories;
import com.glroland.trivia.questions.gateway.getcategories.TriviaCategory;
import com.glroland.trivia.questions.gateway.getquestions.GetQuestionsServiceGateway;
import com.glroland.trivia.questions.gateway.getquestions.TriviaQuestions;
import com.glroland.trivia.questions.gateway.getquestions.TriviaQuestion;

@RestController
public class TriviaQuestionController {

	private static final Logger log = LoggerFactory.getLogger(TriviaQuestionController.class);

	@Autowired
	private GetCategoryServiceGateway getCategoryServiceGateway;

	@Autowired
	private GetQuestionsServiceGateway getQuestionsServiceGateway;

    @GetMapping("/categories")
    @CrossOrigin(origins = "*")
    public List<Category> categories()
    {
        TriviaCategories triviaCategories = getCategoryServiceGateway.getTriviaCategories();
        
        ArrayList<Category> results = new ArrayList<Category>();
        if ((triviaCategories != null) && (triviaCategories.getTriviaCategories() != null))
        {
            for (TriviaCategory triviaCategory : triviaCategories.getTriviaCategories())
            {
                Category category = new Category();
                category.setId(triviaCategory.getId());
                category.setName(triviaCategory.getName());
                results.add(category);
            }
        }

        return results;
    }

    @GetMapping("/questions")
    @CrossOrigin(origins = "*")
    public List<Question> questions(@RequestParam(value = "categoryId", defaultValue = "0") int categoryId, @RequestParam(value = "numQuestions", defaultValue = "10") int numQuestions)
    {
        TriviaQuestions triviaQuestions = getQuestionsServiceGateway.getTriviaQuestions(categoryId, numQuestions);

        ArrayList<Question> results = new ArrayList<Question>();
        if ((triviaQuestions != null) && (triviaQuestions.getQuestions() != null))
        {
            for (TriviaQuestion triviaQuestion : triviaQuestions.getQuestions())
            {
                Question question = new Question();
                question.setQuestion(triviaQuestion.getQuestion());
                question.setDifficulty(triviaQuestion.getDifficulty());
                
                ArrayList<Answer> answers = new ArrayList<Answer>();

                Answer correctOne = new Answer();
                correctOne.setCorrectFlag(true);
                correctOne.setValue(triviaQuestion.getCorrectAnswer());
                answers.add(correctOne);

                for (String incorrectAnswer : triviaQuestion.getIncorrectAnswer())
                {
                    Answer incorrectOne = new Answer();
                    incorrectOne.setCorrectFlag(false);
                    incorrectOne.setValue(incorrectAnswer);
                    answers.add(incorrectOne);
                }
                
                Collections.shuffle(answers);
                question.setAnswers(answers);

                results.add(question);
            }
        }

        return results;
    }
}

package com.revati.quizapp.service;


import com.revati.quizapp.dao.QuestionDao;
import com.revati.quizapp.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
       return questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
      return  questionDao.findByCategory(category);
    }


    public String addQuestion(Question question) {
      questionDao.save(question);
      return "Success";
    }

    public ResponseEntity<String> updateQuestion(Integer id, Question question) {
        if(questionDao.existsById(id)){
            question.setId(id);
            return new ResponseEntity<>("Success: Question Updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error: Question ID not found",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        try{
            questionDao.deleteById(id);
            return new ResponseEntity<>("Success: Question Deleted",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error: Could not delete",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

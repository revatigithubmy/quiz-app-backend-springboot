package com.revati.quizapp.service;


import com.revati.quizapp.dao.QuestionDao;
import com.revati.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> addQuestion(Question question) {
      questionDao.save(question);
      return new ResponseEntity<>("success",HttpStatus.CREATED);
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

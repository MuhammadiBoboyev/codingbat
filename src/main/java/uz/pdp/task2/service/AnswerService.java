package uz.pdp.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task2.entity.*;
import uz.pdp.task2.payload.AnswerDto;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnswerRepository answerRepository;

    public List<Answer> getAnswers() {
        return answerRepository.findAll();
    }

    public Answer getAnswerById(int id) {
        return answerRepository.findById(id).orElse(null);
    }

    public ApiResponse addAnswer(AnswerDto answerDto) {
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent())
            return new ApiResponse("user not found", false);
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("task not found", false);
        if (!answerDto.isCorrect())
            return new ApiResponse("answer is not correct", false);
        answerRepository.save(new Answer(
                answerDto.getAnswer(),
                answerDto.isCorrect(),
                optionalUser.get(),
                optionalTask.get()
        ));
        return new ApiResponse("success full added", true);
    }

    public ApiResponse editAnswer(int id, AnswerDto answerDto) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent())
            return new ApiResponse("answer not found", false);
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent())
            return new ApiResponse("user not found", false);
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("task not found", false);
        if (!answerDto.isCorrect())
            return new ApiResponse("answer is not correct", false);
        Answer answer = optionalAnswer.get();
        answer.setAnswer(answerDto.getAnswer());
        answer.setCorrect(answerDto.isCorrect());
        answer.setTask(optionalTask.get());
        answer.setUser(optionalUser.get());
        answerRepository.save(answer);
        return new ApiResponse("success full edited", true);
    }

    public ApiResponse deleteAnswer(int id) {
        if (!answerRepository.existsById(id))
            return new ApiResponse("answer not found", false);
        answerRepository.deleteById(id);
        return new ApiResponse("success full deleted", true);
    }
}

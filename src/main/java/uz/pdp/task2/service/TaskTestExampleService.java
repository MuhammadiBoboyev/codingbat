package uz.pdp.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task2.entity.Task;
import uz.pdp.task2.entity.TaskTestExample;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.payload.TaskTestExampleDto;
import uz.pdp.task2.repository.TaskRepository;
import uz.pdp.task2.repository.TaskTestExampleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskTestExampleService {
    @Autowired
    TaskTestExampleRepository taskTestExampleRepository;

    @Autowired
    TaskRepository taskRepository;

    public List<TaskTestExample> getTaskTestExample() {
        return taskTestExampleRepository.findAll();
    }

    public TaskTestExample getTaskTestExampleById(int id) {
        return taskTestExampleRepository.findById(id).orElse(null);
    }

    public ApiResponse addTaskTestExample(TaskTestExampleDto taskTestExampleDto) {
        Optional<Task> optionalTask = taskRepository.findById(taskTestExampleDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("task not found", false);
        taskTestExampleRepository.save(new TaskTestExample(taskTestExampleDto.getText(), optionalTask.get()));
        return new ApiResponse("success full added", true);
    }

    public ApiResponse editTaskTestExample(int id, TaskTestExampleDto taskTestExampleDto) {
        Optional<TaskTestExample> optionalTaskTestExample = taskTestExampleRepository.findById(id);
        if (!optionalTaskTestExample.isPresent())
            return new ApiResponse("task test example not found", false);
        Optional<Task> optionalTask = taskRepository.findById(taskTestExampleDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("task not found", false);
        TaskTestExample taskTestExample = optionalTaskTestExample.get();
        taskTestExample.setText(taskTestExampleDto.getText());
        taskTestExample.setTask(optionalTask.get());
        taskTestExampleRepository.save(taskTestExample);
        return new ApiResponse("success full edited", true);
    }

    public ApiResponse deleteTaskTestExample(int id) {
        if (!taskTestExampleRepository.existsById(id))
            return new ApiResponse("TaskTestExample not found", false);
        taskTestExampleRepository.deleteById(id);
        return new ApiResponse("success full deleted", true);
    }
}

package uz.pdp.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task2.entity.Category;
import uz.pdp.task2.entity.Task;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.payload.TaskDto;
import uz.pdp.task2.repository.CategoryRepository;
import uz.pdp.task2.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(int id) {
        return taskRepository.findById(id).orElse(null);
    }

    public ApiResponse addTask(TaskDto taskDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new ApiResponse("category not found", false);
        taskRepository.save(new Task(
                taskDto.getName(),
                taskDto.getText(),
                taskDto.isHasStar(),
                taskDto.getSolution(),
                taskDto.getHint(),
                taskDto.getMethod(),
                optionalCategory.get()
        ));
        return new ApiResponse("success full added", true);
    }

    public ApiResponse editTask(int id, TaskDto taskDto) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent())
            return new ApiResponse("task not found", false);
        Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new ApiResponse("category not found", false);
        Task task = optionalTask.get();
        task.setCategory(optionalCategory.get());
        task.setHint(taskDto.getHint());
        task.setName(taskDto.getName());
        task.setHasStar(taskDto.isHasStar());
        task.setMethod(taskDto.getMethod());
        task.setSolution(taskDto.getSolution());
        task.setText(taskDto.getText());
        taskRepository.save(task);
        return new ApiResponse("success full edited", true);
    }

    public ApiResponse deleteTask(int id) {
        if (!taskRepository.existsById(id))
            return new ApiResponse("task not found", false);
        taskRepository.deleteById(id);
        return new ApiResponse("success full deleted", true);
    }
}

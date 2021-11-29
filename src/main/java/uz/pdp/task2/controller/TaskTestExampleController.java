package uz.pdp.task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task2.entity.TaskTestExample;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.payload.TaskTestExampleDto;
import uz.pdp.task2.service.TaskTestExampleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TaskTestExampleController {

    @Autowired
    TaskTestExampleService taskTestExampleService;

    @GetMapping
    public ResponseEntity<List<TaskTestExample>> getTaskTestExample() {
        return ResponseEntity.status(HttpStatus.OK).body(taskTestExampleService.getTaskTestExample());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskTestExample> getTaskTestExampleById(@PathVariable int id) {
        TaskTestExample taskTestExample = taskTestExampleService.getTaskTestExampleById(id);
        return ResponseEntity.status(taskTestExample == null ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(taskTestExample);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addTaskTestExample(@Valid @RequestBody TaskTestExampleDto taskTestExampleDto) {
        ApiResponse apiResponse = taskTestExampleService.addTaskTestExample(taskTestExampleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editTaskTestExample(@PathVariable int id, @Valid @RequestBody TaskTestExampleDto taskTestExampleDto) {
        ApiResponse apiResponse = taskTestExampleService.editTaskTestExample(id, taskTestExampleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTaskTestExample(@PathVariable int id) {
        ApiResponse apiResponse = taskTestExampleService.deleteTaskTestExample(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(apiResponse);
    }
}
package com.taskboard.taskboardbackend.Controller;

import com.taskboard.taskboardbackend.DTO.TaskRequest;
import com.taskboard.taskboardbackend.DTO.TaskResponse;
import com.taskboard.taskboardbackend.Service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards/{boardId}/tasks")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTasks(@PathVariable Long boardId) {
        return ResponseEntity.ok(taskService.getByBoard(boardId));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@PathVariable Long boardId,
                                               @RequestBody TaskRequest request) {
        return ResponseEntity.ok(taskService.create(boardId, request));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> delete(@PathVariable Long boardId,
                                       @PathVariable Long taskId) {
        taskService.delete(taskId);
        return ResponseEntity.noContent().build();
    }
}
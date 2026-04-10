package com.taskboard.taskboardbackend.Service;

import com.taskboard.taskboardbackend.DTO.TaskRequest;
import com.taskboard.taskboardbackend.DTO.TaskResponse;
import com.taskboard.taskboardbackend.Model.Board;
import com.taskboard.taskboardbackend.Model.Task;
import com.taskboard.taskboardbackend.Repository.BoardRepository;
import com.taskboard.taskboardbackend.Repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final BoardRepository boardRepository;

    public TaskResponse create(Long boardId, TaskRequest request){
        Board board= boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board Not found"));
        Task task= Task.builder()
                .title(request.title())
                .description(request.description())
                .status(request.status())
                .board(board)
                .build();
        taskRepository.save(task);
        return new TaskResponse(task.getId(), task.getTitle(),task.getDescription(),task.getStatus());
    }

    public List<TaskResponse> getByBoard(Long boardId){
        return taskRepository.findByBoardId(boardId)
                .stream()
                .map(t -> new TaskResponse(t.getId(),t.getTitle(),t.getDescription(),t.getStatus()))
                .toList();
    }

    public void delete(Long taskId){
        taskRepository.deleteById(taskId);
    }
}

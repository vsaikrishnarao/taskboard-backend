package com.taskboard.taskboardbackend.Controller;

import com.taskboard.taskboardbackend.DTO.BoardRequest;
import com.taskboard.taskboardbackend.DTO.BoardResponse;
import com.taskboard.taskboardbackend.Model.Board;
import com.taskboard.taskboardbackend.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
//@CrossOrigin(origins="http://127.0.0.1:5173")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<List<BoardResponse>> getAll(){
        return ResponseEntity.ok(boardService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(boardService.getById(id));
    }

    @PostMapping
    public ResponseEntity<BoardResponse> create(@RequestBody BoardRequest request){
        return ResponseEntity.ok(boardService.create(request));
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> delete(@PathVariable Long boardId){
        boardService.delete(boardId);
        return ResponseEntity.noContent().build();
    }
}

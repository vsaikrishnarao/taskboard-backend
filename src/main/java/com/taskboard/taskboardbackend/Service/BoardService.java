package com.taskboard.taskboardbackend.Service;

import com.taskboard.taskboardbackend.DTO.BoardRequest;
import com.taskboard.taskboardbackend.DTO.BoardResponse;
import com.taskboard.taskboardbackend.Model.Board;
import com.taskboard.taskboardbackend.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardResponse create(BoardRequest request){
        String name = request.name().trim();

        String slug = name
                .toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .replaceAll("\\s+", "");
        if(boardRepository.existsBySlug(slug)){
            throw new RuntimeException("Board with this name already exists");
        }
        Board board= Board.builder().name(request.name()).slug(slug).build();
        boardRepository.save(board);
        return new BoardResponse(board.getId(),board.getName());
    }

    public List<BoardResponse> getAll(){
        return  boardRepository.findAll()
                .stream()
                .map(b -> new BoardResponse(b.getId(),b.getName()))
                .toList();
    }

    public BoardResponse getById(Long id){
        Board board= boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board Not Found"));
        return new BoardResponse(board.getId(), board.getName());
    }

    public void delete(Long id){
        boardRepository.deleteById(id);
    }
}

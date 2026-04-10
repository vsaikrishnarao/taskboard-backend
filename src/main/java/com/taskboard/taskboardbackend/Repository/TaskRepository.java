package com.taskboard.taskboardbackend.Repository;

import com.taskboard.taskboardbackend.Model.Board;
import com.taskboard.taskboardbackend.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
     List<Task> findByBoardId(Long boardId);
}

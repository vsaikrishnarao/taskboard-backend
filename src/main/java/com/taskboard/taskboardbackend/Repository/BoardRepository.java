package com.taskboard.taskboardbackend.Repository;

import com.taskboard.taskboardbackend.Model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
    Boolean existsBySlug(String slug);
}

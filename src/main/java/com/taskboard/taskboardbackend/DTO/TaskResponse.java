package com.taskboard.taskboardbackend.DTO;

import com.taskboard.taskboardbackend.Enum.TaskStatus;

public record TaskResponse(Long id, String title, String description, TaskStatus status) {
}

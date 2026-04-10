package com.taskboard.taskboardbackend.DTO;

import com.taskboard.taskboardbackend.Enum.TaskStatus;

public record TaskRequest(String title, String description, TaskStatus status) {
}

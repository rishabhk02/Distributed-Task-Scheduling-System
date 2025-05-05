package com.distributed_system.task_scheduler.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDTO {
    private Long workerId;
    private String name;
    private String status;
    private LocalDateTime lastHeartbeat;
    private Integer capacity;
    private Integer currentLoad;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

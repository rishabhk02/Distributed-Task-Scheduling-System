package com.distributed_system.task_scheduler.repository;

import com.distributed_system.task_scheduler.entity.Task;
import com.distributed_system.task_scheduler.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByCreatedById(Long userId, Pageable pageable);

    Page<Task> findByStatus(TaskStatus status, Pageable pageable);

    Page<Task> findByWorkerId(Long workerId, Pageable pageable);

    @Query("SELECT t From Task t WHERE t.status = :status AND t.scheduledTime <= :now")
    List<Task> findTaskDueForExecution(TaskStatus status, LocalDateTime now);

    List<Task> findByWorkerIdAndStatus(Long workerId, TaskStatus status);

    @Query("SELECT t FROM Task t WHERE t.status = :status AND t.failureCount < :maxRetries")
    List<Task> findFailedTasksEligibleForRetry(TaskStatus status, int maxRetries);
}

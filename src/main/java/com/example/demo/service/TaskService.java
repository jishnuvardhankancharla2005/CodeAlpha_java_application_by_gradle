package com.example.demo.service;

import com.example.demo.model.TaskItem;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TaskService {

    private final Map<String, TaskItem> taskStore = new ConcurrentHashMap<>();
    private final Counter tasksCreatedCounter;
    private final Counter tasksCompletedCounter;
    private final Counter errorCounter;
    private final Timer taskProcessingTimer;
    private final AtomicInteger activeTaskGauge;

    public TaskService(MeterRegistry meterRegistry) {
        this.tasksCreatedCounter = Counter.builder("app_tasks_created_total")
                .description("Total number of tasks created")
                .register(meterRegistry);

        this.tasksCompletedCounter = Counter.builder("app_tasks_completed_total")
                .description("Total number of tasks completed")
                .register(meterRegistry);

        this.errorCounter = Counter.builder("app_simulated_errors_total")
                .description("Total number of simulated application errors")
                .register(meterRegistry);

        this.taskProcessingTimer = Timer.builder("app_task_processing_duration_seconds")
                .description("Duration of task processing workloads")
                .publishPercentiles(0.5, 0.9, 0.95, 0.99)
                .register(meterRegistry);

        this.activeTaskGauge = meterRegistry.gauge("app_tasks_active_count", new AtomicInteger(0));

        // Seed initial tasks
        createTask(new TaskItem(UUID.randomUUID().toString(), "Setup CI/CD Pipeline", "Configure Jenkinsfile", "COMPLETED", "HIGH"));
        createTask(new TaskItem(UUID.randomUUID().toString(), "Configure Monitoring", "Setup Prometheus & Grafana", "IN_PROGRESS", "HIGH"));
    }

    public List<TaskItem> getAllTasks() {
        return new ArrayList<>(taskStore.values());
    }

    public Optional<TaskItem> getTaskById(String id) {
        return Optional.ofNullable(taskStore.get(id));
    }

    public TaskItem createTask(TaskItem task) {
        if (task.getId() == null || task.getId().isBlank()) {
            task.setId(UUID.randomUUID().toString());
        }
        taskStore.put(task.getId(), task);
        tasksCreatedCounter.increment();
        activeTaskGauge.incrementAndGet();
        return task;
    }

    public Optional<TaskItem> updateTask(String id, TaskItem updatedTask) {
        TaskItem existing = taskStore.get(id);
        if (existing == null) {
            return Optional.empty();
        }
        existing.setTitle(updatedTask.getTitle());
        existing.setDescription(updatedTask.getDescription());
        existing.setPriority(updatedTask.getPriority());

        if (!"COMPLETED".equalsIgnoreCase(existing.getStatus()) && "COMPLETED".equalsIgnoreCase(updatedTask.getStatus())) {
            tasksCompletedCounter.increment();
            activeTaskGauge.decrementAndGet();
        }
        existing.setStatus(updatedTask.getStatus());
        return Optional.of(existing);
    }

    public boolean deleteTask(String id) {
        TaskItem removed = taskStore.remove(id);
        if (removed != null) {
            activeTaskGauge.decrementAndGet();
            return true;
        }
        return false;
    }

    public Map<String, Object> simulateWorkload(int durationMs) {
        return taskProcessingTimer.record(() -> {
            try {
                long start = System.currentTimeMillis();
                // Simulate CPU load / delay
                Thread.sleep(Math.max(10, Math.min(durationMs, 2000)));
                long elapsed = System.currentTimeMillis() - start;
                return Map.of(
                        "status", "success",
                        "simulatedDurationMs", elapsed,
                        "timestamp", System.currentTimeMillis()
                );
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Workload interrupted", e);
            }
        });
    }

    public void recordSimulatedError() {
        errorCounter.increment();
    }
}

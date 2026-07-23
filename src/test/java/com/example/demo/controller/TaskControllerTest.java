package com.example.demo.controller;

import com.example.demo.model.TaskItem;
import com.example.demo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    public void testGetAllTasks() throws Exception {
        TaskItem item = new TaskItem("1", "Test Task", "Desc", "PENDING", "HIGH");
        given(taskService.getAllTasks()).willReturn(List.of(item));

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Task"));
    }

    @Test
    public void testCreateTask() throws Exception {
        TaskItem item = new TaskItem("10", "New Task", "Description", "PENDING", "LOW");
        given(taskService.createTask(any(TaskItem.class))).willReturn(item);

        String json = """
                {
                    "title": "New Task",
                    "description": "Description",
                    "status": "PENDING",
                    "priority": "LOW"
                }
                """;

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("10"));
    }

    @Test
    public void testSimulateErrorEndpoint() throws Exception {
        mockMvc.perform(get("/api/tasks/simulate/error"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("Simulated internal server error for monitoring test"));
    }
}

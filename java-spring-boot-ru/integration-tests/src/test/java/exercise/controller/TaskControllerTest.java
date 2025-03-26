package exercise.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;
    private Task task;
    private HashMap<String, String> data;

    @BeforeEach
    public void setUp() {
        task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .ignore(Select.field(Task::getCreatedAt))
                .ignore(Select.field(Task::getUpdatedAt))
                .set(Select.field(Task::getTitle), faker.lorem().word())
                .set(Select.field(Task::getDescription), faker.lorem().sentence(6))
                .create();
        taskRepository.save(task);
        data = new HashMap<>();
        data.put("title", "task title");
        data.put("description", "task description");
    }

    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    //    Просмотр конкретной задачи
    @Test
    public void testShow() throws Exception {
        MockHttpServletRequestBuilder request = get("/tasks/1");
        ResultActions result = mockMvc.perform(request).andExpect(status().isOk());
    }

    //    Создание новой задачи
    @Test
    public void testCreate() throws Exception {
        MockHttpServletRequestBuilder request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));
        MockHttpServletResponse response = mockMvc.perform(request).andExpect(status().isCreated()).andReturn().getResponse();
        String content = response.getContentAsString();
        Task responsedTask = om.readValue(content, Task.class);
        Task expected = taskRepository.findById(responsedTask.getId()).get();
        assertEquals(expected.getTitle(), responsedTask.getTitle());
        assertEquals(expected.getDescription(), responsedTask.getDescription());
    }

    //    Обновление существующей задачи
    @Test
    public void testUpdate() throws Exception {
        long id = task.getId();
        MockHttpServletRequestBuilder request = put("/tasks/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));
        mockMvc.perform(request).andExpect(status().isOk());
        Task expected = taskRepository.findById(id).get();
        assertEquals(expected.getTitle(), "task title");
        assertEquals(expected.getDescription(), "task description");
    }

    //    Удаление задачи
    @Test
    public void testDelete() throws Exception {
        long id = task.getId();
        MockHttpServletRequestBuilder request = delete("/tasks/" + id);
        mockMvc.perform(request).andExpect(status().isOk());
        assertThat(taskRepository.findById(id).isEmpty()).isTrue();
    }
    // END
}

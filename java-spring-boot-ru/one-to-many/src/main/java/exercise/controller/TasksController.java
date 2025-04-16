package exercise.controller;

import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.model.Task;
import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDTO> index() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(t -> taskMapper.map(t))
                .toList();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO show(@PathVariable Long id) {
        Task task = taskRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Task with id = " + id + "not found"));
        return taskMapper.map(task);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@RequestBody TaskCreateDTO taskData) {
        Task task = taskMapper.map(taskData);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    @PutMapping(path = "/{id}")
    public TaskDTO update(@RequestBody TaskUpdateDTO taskData, @PathVariable Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id = " + id + "not found"));
        //User связан с Task связью один ко многим (т.е. у одной задачи может быть только один исполнитель, а у одного исполнителя может быть несколько задач).
        //Логику добавления и удаления связей будет выполнять User, т.к. он хранит коллекцию связей с Task.
        //При обновлении задачи нам нужно изменить уже назначенного исполнителя на нового, иными словами, добавить новому исполнителю связь с задачей.
        //Значит нам нужно получить экземпляр User, на которого мы назначим задачу.
        User user = userRepository.findById(taskData.getAssigneeId())
                .orElseThrow(() -> new ResourceNotFoundException("User with id = " + id + "not found"));
        //Добавляет текущему User задачу в список задач и устанавливает текущего User в качестве исполнителя для текущей задачи.
        //Task имеет связь с User как ManyToOne (т.е. у одной задачи может быть только один исполнитель, а у одного исполнителя может быть несколько задач).
        //Следовательно, метод addTask перезапишет уже существующую связь между User и Task.
        user.addTask(task);
        taskMapper.update(taskData, task);
        taskRepository.save(task);
        System.out.println(task.toString());
        return taskMapper.map(task);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id = " + id + "not found"));
        User user = userRepository.findById(task.getAssignee().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User with id = " + id + "not found"));
        user.removeTask(task);
        taskRepository.deleteById(id);
    }
    // END
}

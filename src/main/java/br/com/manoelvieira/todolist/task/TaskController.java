package br.com.manoelvieira.todolist.task;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired // Para o Spring gerenciar a instância do repositório
  private ITaskRepository taskRepository;

  @PostMapping("/")
  public TaskModel create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
    System.out.println("Chegou no controller");
    var idUser = request.getAttribute("idUser"); // Obtendo da request
    taskModel.setIdUser((UUID) idUser); // Setando user ID
    var task = this.taskRepository.save(taskModel);
    return task;
  }
}

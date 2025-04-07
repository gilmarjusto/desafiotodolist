package com.desafiotodolist.desafiotodolist.services;

import com.desafiotodolist.desafiotodolist.domains.PriorityType;
import com.desafiotodolist.desafiotodolist.domains.Task;
import com.desafiotodolist.desafiotodolist.dtos.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.desafiotodolist.desafiotodolist.repositories.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceTask {

    private final TaskRepository taskRepository;

    public ServiceTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public ResponseEntity<List<TaskDto>> listAllTasks() {

        List<Task> tasks = new ArrayList<>();
        tasks = taskRepository.findAll();

        return ResponseEntity.ok().body(converterResponse(tasks));
    }

    private List<TaskDto> converterResponse(List<Task> tasks) {
        List<TaskDto> taskDtos = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            TaskDto taskDto = new TaskDto(
                                    tasks.get(i).getId(),
                                    tasks.get(i).getName(),
                                    tasks.get(i).getDescription(),
                                    tasks.get(i).isRealized(),
                                    tasks.get(i).getPriorityType());
            taskDtos.add(taskDto);
        }
        return taskDtos;
    }

    public void add(TaskDto request) {
        Task task = converterDTO(request);
        taskRepository.save(task);
    }

    public Task converterDTO(TaskDto request) {

        Long id = request.getId();
        String name = request.getNome();
        String descricao = request.getDescricao();
        PriorityType priorityType = request.getPrioridade();
        Boolean realized = request.isRealizado();
        return new Task(id, name, descricao, realized, priorityType);
    }

    public Boolean validateRequest(TaskDto request) {
        String name = request.getNome();
        String descricao = request.getDescricao();
        PriorityType priorityType = request.getPrioridade();

       // if (!name.matches("[A-Z][a-z]")){
        if (name.trim().length() < 3){
            System.out.println("Nomex = " + name);
            System.out.println(" <<< ServiceTask >>>    *** Erro no nome *** ");
            return false;
        }
        if(descricao.trim().isBlank()){
          System.out.println(" <<< ServiceTask >>>    *** Erro na descricao *** ");
          return false;
         }
        if((priorityType != PriorityType.ALTA) && (priorityType != PriorityType.MEDIA) && (priorityType != PriorityType.BAIXA)){
             System.out.println(" <<< ServiceTask >>>    *** Erro na prioridade *** ");
             return false;
        }
    return true;
    }

    public void deleteById(Long id) throws Exception{
        System.out.println("########################## deleteById #########################");
        Optional<Task> task = taskRepository.findById(id);

        System.out.println("#### MOSTRA O TASK.GET() = ");
        //System.out.println(task.get());

        long qtd = task.stream().count();
        System.out.println("############# deleteById ###### QTD ACHADA = " + qtd + "  ###################");

        if(task.isPresent()){
            System.out.println("############  deleteById  ############## Achou um ID #########################");
            taskRepository.delete(task.get());
        } else{
            System.out.println("#############  deleteById  ############# NAO Achou um ID. DEU EXCEPTION #########################");
            throw new Exception("Erro == Não achou task pelo ID.");
        }
    }
}

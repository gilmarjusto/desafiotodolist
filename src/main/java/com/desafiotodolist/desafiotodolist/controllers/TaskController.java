package com.desafiotodolist.desafiotodolist.controllers;

import com.desafiotodolist.desafiotodolist.domains.Task;
import com.desafiotodolist.desafiotodolist.dtos.TaskDto;
import com.desafiotodolist.desafiotodolist.services.ServiceTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private ServiceTask serviceTask;

    @GetMapping
    public ResponseEntity<List<TaskDto>> listAllTasks(){
        return serviceTask.listAllTasks();
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addTask(@RequestBody TaskDto request){
        if(serviceTask.validateRequest(request)){
            serviceTask.add(request);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTask(@RequestBody TaskDto request){
        try {
            serviceTask.deleteById(request.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Tarefa Não Encontrada");
        }
        return ResponseEntity.ok().build();
    }


}

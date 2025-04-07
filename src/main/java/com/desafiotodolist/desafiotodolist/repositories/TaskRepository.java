package com.desafiotodolist.desafiotodolist.repositories;

import com.desafiotodolist.desafiotodolist.domains.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}

package com.desafiotodolist.desafiotodolist.domains;

import jakarta.persistence.*;

@Table
@Entity
public class Task {

    @Id
    //@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private boolean realized;
    private PriorityType priorityType;

    public Task() {
    }

    public Task(Long id, String name, String description, boolean realized, PriorityType priorityType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.realized = realized;
        this.priorityType = priorityType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRealized() {
        return realized;
    }

    public void setRealized(boolean realized) {
        this.realized = true;
    }

    public PriorityType getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(PriorityType priorityType) {
        this.priorityType = priorityType;
    }
}

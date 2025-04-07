package com.desafiotodolist.desafiotodolist.dtos;

import com.desafiotodolist.desafiotodolist.domains.PriorityType;

public class TaskDto {
    private Long id;
    private String nome;
    private String descricao;
    private boolean realizado;
    private PriorityType prioridade;

    public TaskDto(Long id, String nome, String descricao, boolean realizado, PriorityType prioridade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.realizado = realizado;
        this.prioridade = prioridade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public PriorityType getPrioridade() {
        return prioridade;
    }
}

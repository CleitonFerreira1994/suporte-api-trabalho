package com.example.demo.model;

public class Chamado {
    private String id;
    private String titulo;
    private String solicitante;
    private String setor;
    private Prioridade prioridade;
    private Status status;

    public Chamado() {}

    public Chamado(String id, String titulo, String solicitante, String setor, Prioridade prioridade, Status status) {
        this.id = id;
        this.titulo = titulo;
        this.solicitante = solicitante;
        this.setor = setor;
        this.prioridade = prioridade;
        this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getSolicitante() { return solicitante; }
    public void setSolicitante(String solicitante) { this.solicitante = solicitante; }
    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }
    public Prioridade getPrioridade() { return prioridade; }
    public void setPrioridade(Prioridade prioridade) { this.prioridade = prioridade; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
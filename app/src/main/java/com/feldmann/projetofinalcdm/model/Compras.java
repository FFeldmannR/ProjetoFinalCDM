package com.feldmann.projetofinalcdm.model;

public class Compras {
    private int id;
    private String nomeLista;
    private String nomeItem;
    private String quantidade;
    private int completed;
    //
    public Compras(int id, String nomeLista, String nomeItem, String quantidade, int completed) {
        this.id = id;
        this.nomeLista = nomeLista;
        this.nomeItem = nomeItem;
        this.quantidade = quantidade;
        this.completed = completed;
    }
    //
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    //
    public String getNomeLista() { return nomeLista; }
    public void setNomeLista(String nomeLista) { this.nomeLista = nomeLista; }
    //
    public String getNomeItem() { return nomeItem; }
    public void setNomeItem(String nomeItem) { this.nomeItem = nomeItem; }
    //
    public String getQuantidade() { return quantidade; }
    public void setQuantidade(String quantidade) { this.quantidade = quantidade; }
    //
    public int isCompleted() { return completed; }
    public void setCompleted(int completed) { this.completed = completed; }
    //
}//fim classe

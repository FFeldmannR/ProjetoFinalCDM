package com.feldmann.projetofinalcdm.model;

public class Compras {
    private int id;
    private String nomeItem;
    private String quantidade;
    private boolean completed;
    //
    public Compras(int id, String nomeItem, String quantidade, boolean completed) {
        this.id = id;
        this.nomeItem = nomeItem;
        this.quantidade = quantidade;
        this.completed = completed;
    }
    //
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    //
    public String getNomeItem() { return nomeItem; }
    public void setNomeItem(String nomeItem) { this.nomeItem = nomeItem; }
    //
    public String getQuantidade() { return quantidade; }
    public void setQuantidade(String quantidade) { this.quantidade = quantidade; }
    //
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    //
}//fim classe

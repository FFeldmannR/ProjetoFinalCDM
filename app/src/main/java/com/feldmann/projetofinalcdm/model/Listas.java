package com.feldmann.projetofinalcdm.model;

public class Listas {
    private int id;
    private String nomeLista;
    //
    public Listas(int id, String nomeLista) {
        this.id = id;
        this.nomeLista = nomeLista;
    }
    //
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    //
    public String getNomeLista() { return nomeLista; }
    public void setNomeLista(String nomeLista) { this.nomeLista = nomeLista; }
}


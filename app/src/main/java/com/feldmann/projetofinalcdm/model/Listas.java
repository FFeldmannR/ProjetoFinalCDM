package com.feldmann.projetofinalcdm.model;
//
public class Listas {
    private int id;
    private String donoLista;
    private String nomeLista;
    private int progressoLista;
    //
    public Listas ( int id, String donoLista, String nomeLista, int progressoLista ) {
        this.id = id;
        this.donoLista = donoLista;
        this.nomeLista = nomeLista;
        this.progressoLista = progressoLista;
    }
    //
    public int getId () { return id; }
    public void setId ( int id ) { this.id = id; }
    //
    public String getDonoLista () { return donoLista; }
    public void setDonoLista ( String donoLista ) { this.donoLista = donoLista; }
    //
    public String getNomeLista () { return nomeLista; }
    public void setNomeLista ( String nomeLista ) { this.nomeLista = nomeLista; }
    //
    public int getProgressoLista () { return progressoLista; }
    public void setProgressoLista ( int progressoLista ) { this.progressoLista = progressoLista; }
    //
}//fim classe


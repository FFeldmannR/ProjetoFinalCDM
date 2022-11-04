package com.feldmann.projetofinalcdm.model;

public class ItemList {
    private int id;
    private String nomeLista;
    private boolean check[];

    public ItemList(int id, String nomeLista, boolean[] check) {
        this.id = id;
        this.nomeLista = nomeLista;
        this.check = check;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    //
    public String getNomeLista() { return nomeLista; }
    public void setNomeLista(String nomeLista) { this.nomeLista = nomeLista; }
    //
    public boolean[] getCheck() { return check; }
    public void setCheck(boolean[] check) { this.check = check; }
}

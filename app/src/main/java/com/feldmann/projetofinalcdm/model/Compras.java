package com.feldmann.projetofinalcdm.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Compras implements Parcelable {
    private int id;
    private String donoLista;
    private String nomeLista;
    private String nomeItem;
    private String quantidade;
    private int completed;
    //
    public Compras(int id, String donoLista, String nomeLista, String nomeItem, String quantidade, int completed) {
        this.id = id;
        this.donoLista = donoLista;
        this.nomeLista = nomeLista;
        this.nomeItem = nomeItem;
        this.quantidade = quantidade;
        this.completed = completed;
    }
    //
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    //
    public String getDonoLista() { return donoLista; }
    public void setDonoLista(String donoLista) {this.donoLista = donoLista;}
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
    //PARCELABLE METHODS
    protected Compras(Parcel in) {
        id = in.readInt();
        donoLista = in.readString();
        nomeLista = in.readString();
        nomeItem = in.readString();
        quantidade = in.readString();
        completed = in.readInt();
    }
    public static final Creator<Compras> CREATOR = new Creator<Compras>() {
        @Override
        public Compras createFromParcel(Parcel in) {
            return new Compras(in);
        }

        @Override
        public Compras[] newArray(int size) {
            return new Compras[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(donoLista);
        dest.writeString(nomeLista);
        dest.writeString(nomeItem);
        dest.writeString(quantidade);
        dest.writeInt(completed);
    }
    //
}//fim classe

package com.feldmann.projetofinalcdm.repository;

import com.feldmann.projetofinalcdm.model.Lista;
import java.util.ArrayList;
import java.util.List;

public class ListasRepository {
    private List<Lista> listas;
    private static ListasRepository instance = null;

    public ListasRepository() {
        if (instance == null){
            listas = new ArrayList<>();
        }
    }

    public static ListasRepository getInstance(){
        instance = new ListasRepository();
        return instance;
    }

    public List<Lista> getListas() {
        return listas;
    }
}
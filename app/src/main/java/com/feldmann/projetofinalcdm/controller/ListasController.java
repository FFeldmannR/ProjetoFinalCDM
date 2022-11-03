package com.feldmann.projetofinalcdm.controller;

import android.content.Context;
import android.content.Intent;

import com.feldmann.projetofinalcdm.views.ListaDeCompras;

public class ListasController implements Controller.controllerListas{
    private Context context;

    public ListasController(Context context) {
        this.context = context;
    }

    @Override
    public void paraListaDeCompras() {
        Intent in = new Intent(context, ListaDeCompras.class);
        context.startActivity(in);
    }
}

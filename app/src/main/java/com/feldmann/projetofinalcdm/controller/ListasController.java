package com.feldmann.projetofinalcdm.controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import com.feldmann.projetofinalcdm.views.ListaDeCompras;

public class ListasController implements Controller.controllerListas{
    private Context context;

    public ListasController(Context context) {
        this.context = context;
    }

    @Override
    public void paraListaDeCompras(ImageButton imgBtn) {
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, ListaDeCompras.class);
                context.startActivity(in);
            }//fim onClick
        });//fim listener
    }//fim metodo

}//fim class

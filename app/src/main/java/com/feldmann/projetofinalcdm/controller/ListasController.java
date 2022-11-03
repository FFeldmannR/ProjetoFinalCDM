package com.feldmann.projetofinalcdm.controller;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ImageButton;
import com.feldmann.projetofinalcdm.repository.ListasRepository;
import com.feldmann.projetofinalcdm.views.ListaDeCompras;

public class ListasController implements Controller.controllerListas{
    private Context context;
    private SQLiteDatabase sqlWrite;

    public ListasController(Context context, SQLiteDatabase sqlWrite) {
        this.context = context;
        this.sqlWrite = sqlWrite;
    }

    @Override
    public void paraListaDeCompras(ImageButton imgBtn) {
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues ctv = new ContentValues();
                ctv.put("nome", "lista "+ ListasRepository.getListas().size()+1 );
                sqlWrite.insert("listas", null, ctv);
                Intent in = new Intent(context, ListaDeCompras.class);
                context.startActivity(in);
            }//fim onClick
        });//fim listener
    }//fim metodo

}//fim class

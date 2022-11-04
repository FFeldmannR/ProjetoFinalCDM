package com.feldmann.projetofinalcdm.controller;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.feldmann.projetofinalcdm.adapter.AdapterListas;
import com.feldmann.projetofinalcdm.repository.ListasRepository;
import com.feldmann.projetofinalcdm.views.ListaDeCompras;
import com.feldmann.projetofinalcdm.views.ListasActivity;

public class ListasController implements Controller.controllerListas{
    private Context context;
    private SQLiteDatabase sqlWrite;

    public ListasController(Context context, SQLiteDatabase sqlWrite) {
        this.context = context;
        this.sqlWrite = sqlWrite;
    }

    @Override
    public void addList(ImageButton imgBtn) {
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues ctv = new ContentValues();
                try{
                    ctv.put("nome", "lista "+ ( (Integer.valueOf(ListasRepository.getListas().size())) + 1) );
                    sqlWrite.insert("listas", null, ctv);
                    try{
                        // resetando activity ListasActivity
                        Intent in = new Intent(context, ListasActivity.class);
                        context.startActivity(in);
                    }catch (Exception e){
                        Log.d("EXCEPTION", "falha ao mudar de activity:\n"+e.getMessage() );
                    }
                }catch (Exception e){
                    Log.d("EXCEPTION", "falha ao adicionar no banco:\n"+e.getMessage() );
                }
            }//fim onClick
        });//fim listener
    }//fim metodo

    @Override
    public void setAdapterListas(RecyclerView rv) {
        AdapterListas adapter = new AdapterListas(ListasRepository.getListas());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(context) );
    }

}//fim class

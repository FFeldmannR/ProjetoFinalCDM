package com.feldmann.projetofinalcdm.controller;
//
import android.content.ContentValues;
import android.view.View;
import android.widget.ImageButton;
import androidx.recyclerview.widget.RecyclerView;
//
public class ListasController implements Controller.controllerListas{
    @Override
    public void addToDB(ImageButton imgBtn, String donoLista, String nomeLista) {
        //
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                ContentValues ctv = new ContentValues();
                ctv.put("donoLista", donoLista);
                ctv.put("nomeLista", nomeLista);
            }
        });
    }

    @Override
    public void setAdapterListas(RecyclerView rv) {
        //
    }
}

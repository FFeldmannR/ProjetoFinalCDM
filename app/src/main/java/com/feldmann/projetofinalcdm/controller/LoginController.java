package com.feldmann.projetofinalcdm.controller;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.feldmann.projetofinalcdm.views.ListaActivity;

public class LoginController implements Controller.controllerLogin{
    @Override
    public void paraTelaLista(Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(), ListaActivity.class);
                v.getContext().startActivity(in);
            }
        });
    }
}

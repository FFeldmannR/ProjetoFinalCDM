package com.feldmann.projetofinalcdm.controller;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.feldmann.projetofinalcdm.views.CadastroActivity;

public class LoginController implements Controller.controllerLogin{
    private Controller.msg msg;

    public LoginController(Controller.msg msg) {
        this.msg = msg;
    }

    @Override
    public void cadastrarUser(Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(), CadastroActivity.class);
                v.getContext().startActivity(in);
            }
        });
    }

    @Override
    public void verificaUser(Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void paraTelaLista(EditText etLogin, EditText etSenha) {
    }
}
package com.feldmann.projetofinalcdm.controller;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.feldmann.projetofinalcdm.views.LoginActivity;

public class CadastroController implements Controller.controllerCadastro{
    private Controller.msg msg;
    private EditText nome, senha;

    public CadastroController(Context context) {
        this.msg = new MsgController(context, this.getClass().getName().toString() );
    }

    @Override
    public void getCampos(EditText etNome, EditText etSenha) {
        this.nome = etNome;
        this.senha = etSenha;
    }

    @Override
    public void addToDB(Button btnCadastrar, SQLiteDatabase sqlWrite) {
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues ctv = new ContentValues();
                try{
                    //
                    String nomeUser = nome.getText().toString();
                    ctv.put("nome", nomeUser );
                    ctv.put("senha", senha.getText().toString() );
                    sqlWrite.insert("users", null, ctv);
                    msg.messageToast(nomeUser + " ADICIONADO");
                    Intent in = new Intent(v.getContext(), LoginActivity.class);
                    in.putExtra("NOMEUSER", nomeUser);
                    v.getContext().startActivity(in);
                }catch (Exception e){
                    msg.messageToast("ERRO AO ADICIONAR USUARIO");
                    msg.logD("ERRO!!! "+e.getMessage() );
                }
            }
        });
    }
}

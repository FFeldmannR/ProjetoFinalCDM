package com.feldmann.projetofinalcdm.controller;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroController implements Controller.controllerCadastro{
    private Controller.msg msg;
    private EditText nome, senha;

    public CadastroController(Controller.msg msg) {
        this.msg = msg;
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
                    ctv.put("nome", nome.getText().toString() );
                    ctv.put("senha", senha.getText().toString() );
                    sqlWrite.insert("users", null, ctv);
                    msg.messageToast(nome.getText() + " ADICIONADO");
                }catch (Exception e){
                    msg.logD("ERRO!!! "+e.getMessage() );
                }
            }
        });
    }
}

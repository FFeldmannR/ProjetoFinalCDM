package com.feldmann.projetofinalcdm.controller;
//
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.feldmann.projetofinalcdm.repository.*;
import com.feldmann.projetofinalcdm.views.*;
//
public class CadastroController implements Controller.controllerCadastro{
    private Controller.msg msg;
    private Context context;
    //
    public CadastroController(Context context) {
        this.msg = new MsgController(context, this.getClass().getName() );
        this.context = context;
    }
    @Override public void addItemToDB(String usuarioLogado, String nomeLista, String nomeItem, String qntdItem, int completed) {
        ComprasRepository.createItem( usuarioLogado, nomeLista, nomeItem, qntdItem, completed );
    }
    @Override public void addUserToDB( String nomeUser, String senhaUser, TextView tvCadastroIncorreto ) {
        UserRepository.createUserinDB( nomeUser, senhaUser, tvCadastroIncorreto );
    }//fim addToDB
    @Override public void addListToDB( String donoLista, String nomeLista ) {
        ListasRepository.createList( donoLista, nomeLista );
        Intent in = new Intent( context, ListaActivity.class );
        in.putExtra("NOMEUSER", donoLista );
        context.startActivity(in);
    }
}//fim class

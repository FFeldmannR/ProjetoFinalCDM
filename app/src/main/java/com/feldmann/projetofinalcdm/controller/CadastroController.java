package com.feldmann.projetofinalcdm.controller;
//
import android.content.Context;
import android.content.Intent;
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
        Intent in = new Intent( context, ListadeCompras.class );
        in.putExtra("USUARIO", usuarioLogado );
        in.putExtra("NOMELISTA", nomeLista );
        context.startActivity(in);
    }
    @Override public void addUserToDB( String nomeUser, String senhaUser ) {
        UserRepository.createUserinDB( nomeUser, senhaUser );
        Intent in = new Intent( context, LoginActivity.class );
        in.putExtra("NOMEUSER", nomeUser );
        context.startActivity(in);
    }//fim addToDB
    @Override public void addListToDB( String donoLista, String nomeLista ) {
        ListasRepository.createList( donoLista, nomeLista );
        Intent in = new Intent( context, ListaActivity.class );
        in.putExtra("NOMEUSER", donoLista );
        context.startActivity(in);
    }
}//fim class

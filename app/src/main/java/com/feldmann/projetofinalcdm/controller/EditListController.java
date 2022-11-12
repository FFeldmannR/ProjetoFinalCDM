package com.feldmann.projetofinalcdm.controller;

import android.content.Context;
import android.content.Intent;
import com.feldmann.projetofinalcdm.repository.ListasRepository;
import com.feldmann.projetofinalcdm.views.ListaActivity;

public class EditListController implements Controller.controllerEditList{
    private Context context;
    private Controller.msg msg;

    public EditListController(Context context) {
        this.context = context;
        this.msg = new MsgController( context, this.getClass().getName() );
    }
    @Override public void updateList( String donoLista, String nomeLista, String novoNomeLista ) {
        ListasRepository.updateList( donoLista, nomeLista, novoNomeLista );
        msg.messageToast("Nome da lista '"+nomeLista+"' alterado para '"+novoNomeLista+"'" );
        Intent intent = new Intent( context, ListaActivity.class );
        intent.putExtra("NOMEUSER", donoLista );
        context.startActivity(intent);
    }//fim updateList
}//fim classe

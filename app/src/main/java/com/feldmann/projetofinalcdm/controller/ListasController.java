package com.feldmann.projetofinalcdm.controller;

import android.content.Context;
import android.content.Intent;
import com.feldmann.projetofinalcdm.repository.ListasRepository;
import com.feldmann.projetofinalcdm.views.ListaActivity;
import com.feldmann.projetofinalcdm.views.ListadeCompras;

public class ListasController implements Controller.controllerEditList{
    private Context context;
    private Controller.msg msg;

    public ListasController(Context context) {
        this.context = context;
        this.msg = new MsgController( context, this.getClass().getName() );
    }
    @Override public void updateList( String donoLista, String nomeLista, String novoNomeLista ) {
        ListasRepository.updateList( donoLista, nomeLista, novoNomeLista );
        msg.logD("Nome da lista '"+nomeLista+"' alterado para '"+novoNomeLista+"'" );
        Intent intent = new Intent( context, ListadeCompras.class );
        intent.putExtra("USUARIO", donoLista );
        intent.putExtra("NOMELISTA", novoNomeLista );
        context.startActivity(intent);
    }//fim updateList
}//fim classe

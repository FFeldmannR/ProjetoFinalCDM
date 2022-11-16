package com.feldmann.projetofinalcdm.controller;
//
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.feldmann.projetofinalcdm.repository.ListasRepository;
import com.feldmann.projetofinalcdm.views.ListaActivity;
import com.feldmann.projetofinalcdm.views.ListadeCompras;
//
public class ListasController implements Controller.controllerEditList{
    private Context context;
    private Controller.msg msg;
    //
    public ListasController(Context context) {
        this.context = context;
        this.msg = new MsgController( context, this.getClass().getName() );
    }
    @Override public void updateList(String donoLista, String nomeLista, String novoNomeLista, TextView tvNomeListaErrado) {
        ListasRepository.updateList( donoLista, nomeLista, novoNomeLista, tvNomeListaErrado );
        msg.logD("Nome da lista '"+nomeLista+"' alterado para '"+novoNomeLista+"'" );
    }//fim updateList
    @Override public void deleteList( String donoLista, String nomeLista ) {
        ListasRepository.deleteList( donoLista, nomeLista );
        Intent intent = new Intent( context, ListaActivity.class );
        intent.putExtra("NOMEUSER", donoLista );
        context.startActivity(intent);
    }
}//fim classe

package com.feldmann.projetofinalcdm.controller;
//
import android.app.Activity;
import android.content.Context;
import androidx.recyclerview.widget.*;
import com.feldmann.projetofinalcdm.adapters.*;
import com.feldmann.projetofinalcdm.model.*;
import java.util.List;
//
public class AdapterController implements Controller.controllerAdapters {
    private final Context context;
    private final Controller.msg msg;
    //
    public AdapterController ( Context context ) {
        this.context = context;
        this.msg = new MsgController ( context, this.getClass().getName() );
    }//fim construtor
    @Override public void setAdapterItemList ( RecyclerView rv, List<Compras> compras, String lista ) {
        msg.logD ("setAdapterListas" );
        ComprasAdapter comprasAdapter = new ComprasAdapter ( context, compras, lista );
        rv.setAdapter ( comprasAdapter );
        rv.setLayoutManager ( new LinearLayoutManager( context ) );
    }//fim setAdapterItemList
    @Override public void setAdapterLists (RecyclerView rv, List<Listas> listas, String usuarioLogado ) {
        msg.logD("setAdapterListas" );
        ListaAdapter listaAdapter = new ListaAdapter ( listas, usuarioLogado );
        rv.setAdapter ( listaAdapter );
        rv.setLayoutManager ( new LinearLayoutManager ( context ) );
    }
}//fim classe

package com.feldmann.projetofinalcdm.controller;
//
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.feldmann.projetofinalcdm.adapters.ComprasAdapter;
import com.feldmann.projetofinalcdm.model.Compras;
import java.util.List;
//
public class AdapterController implements Controller.controllerAdapters{
    private final Context context;
    private final Controller.msg msg;
    //
    public AdapterController(Context context) {
        this.context = context;
        this.msg = new MsgController(context, this.getClass().getName().toString() );
    }//fim construtor
    @Override public void setAdapterItemList(RecyclerView rv, List<Compras> comprasList,
                                          SQLiteDatabase sqlWrite, String lista){
        msg.logD("setAdapterListas");
        ComprasAdapter comprasAdapter = new ComprasAdapter( comprasList, sqlWrite, lista );
        rv.setAdapter(comprasAdapter);
        rv.setLayoutManager( new LinearLayoutManager( context ) );
    }//fim setAdapterItemList
}//fim classe

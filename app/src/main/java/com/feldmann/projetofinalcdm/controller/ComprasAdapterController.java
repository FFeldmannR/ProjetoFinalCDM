package com.feldmann.projetofinalcdm.controller;
//
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import androidx.recyclerview.widget.RecyclerView;
import com.feldmann.projetofinalcdm.model.Compras;
//
public class ComprasAdapterController implements Controller.controllerComprasAdapter{
    @Override
    public void selectList(Compras objCompras){
        Log.d("SELECT_LIST","_id | nomeLista | nomeItem | quantidade | completed\n"+
                "("+objCompras.getId()+") "+
                objCompras.getNomeLista()+" | "+
                objCompras.getNomeItem()+" | "+
                objCompras.getQuantidade()+" | "+
                objCompras.isCompleted() );
    }
    //
    @Override
    public void itemClick(RecyclerView.ViewHolder holder,
                          SQLiteDatabase sqlWrite,
                          CheckBox cb, String nomeItem,
                          Compras objCompras, String nomeListaAtual){
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("teste", "item clicado");
                buttonEffect(v.getContext(), holder);
                updateSQL(sqlWrite, cb, nomeListaAtual, nomeItem, objCompras);
                selectList(objCompras);
            }//fim onClick
        });//fim clickListener
    }//fim metodo itemClick
    //
    @Override
    public void buttonEffect(Context context, RecyclerView.ViewHolder holder){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
            holder.itemView.setBackgroundResource(outValue.resourceId);
        }
    }//fim buttonEffect
    private void updateSQL(SQLiteDatabase sqlWrite,
                           CheckBox cb, String nomeListaAtual, String nomeItem,
                           Compras objCompras){
        try{
            if (cb.isChecked()){ //se esta marcado, mude para 0
                cb.setChecked(false);
                objCompras.setCompleted(0);
                sqlWrite.execSQL(
                        "UPDATE compras"+
                                " SET completed="+0+
                                " WHERE nomeLista='"+nomeListaAtual+"'"+
                                " AND nomeItem='"+nomeItem+"'"
                );
            }else{//se NÃO esta marcado, mude para 1
                cb.setChecked(true);
                objCompras.setCompleted(1);
                sqlWrite.execSQL(
                        "UPDATE compras"+
                                " SET completed="+1+
                                " WHERE nomeLista='"+nomeListaAtual+"'"+
                                " AND nomeItem='"+nomeItem+"'"
                );
            }
        }catch (Exception e){
            Log.d("itemClick", "FALHA AO ATUALIZAR NO BANCO");
            Log.d("itemClick", ""+e.getMessage());
        }
    }
}

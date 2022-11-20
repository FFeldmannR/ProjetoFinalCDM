package com.feldmann.projetofinalcdm.controller;
//
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.*;
import android.view.View;
import android.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import com.feldmann.projetofinalcdm.model.Compras;
import com.feldmann.projetofinalcdm.repository.DBListas;
import com.feldmann.projetofinalcdm.views.EditarItemActivity;
//
public class ComprasAdapterController implements Controller.controllerComprasAdapter {
    private DBListas db;
    //
    public ComprasAdapterController ( Context context ) { this.db = new DBListas ( context ); }
    //
    @Override public void updateCheckbox ( RecyclerView.ViewHolder holder, CheckBox checkBox,
                                         String nomeListaAtual, Compras compras ) {
        if ( compras.isCompleted() == 1 ) {
            checkBox.setChecked (true);
        } else { checkBox.setChecked (false); }
        //
        checkBox.setOnClickListener ( new View.OnClickListener() {
            @Override public void onClick ( View v ) {
                buttonEffect ( v.getContext(), holder );
                updateSQL ( checkBox, nomeListaAtual, compras );
            }//fim onClick
        });//fim clickListener
    }//fim updateCheckbox
    @Override public void clickInfoItemToEdit ( LinearLayout infoItem, RecyclerView.ViewHolder holder, Compras compras ) {
        infoItem.setOnClickListener ( new View.OnClickListener() {
            @Override public void onClick ( View v ) {
                buttonEffect ( v.getContext(), holder );
                Intent intent = new Intent ( v.getContext(), EditarItemActivity.class );
                intent.putExtra ("OBJCOMPRAS", compras );
                selectList ( compras );
                v.getContext().startActivity ( intent );
            }//fim onClick
        });//fim clickListener
    }//fim updateCheckbox
    @Override public void selectList ( Compras objCompras ) {
        Log.d ("SELECT_LIST","_id | nomeLista | nomeItem | quantidade | completed\n"+
                "("+objCompras.getId()+") "+
                objCompras.getNomeLista()+" | "+
                objCompras.getNomeItem()+" | "+
                objCompras.getQuantidade()+" | "+
                objCompras.isCompleted() );
    }//fim selectList
    private void buttonEffect ( Context context, RecyclerView.ViewHolder holder ) {
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ) {
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute ( android.R.attr.selectableItemBackground, outValue, true );
            holder.itemView.setBackgroundResource ( outValue.resourceId );
        }
    }//fim buttonEffect
    private void updateSQL ( CheckBox cb, String nomeListaAtual, Compras objCompras ) {
        String tag = "UPDATE";
        try{
            if ( cb.isChecked() ) { //se esta marcado, mude para 1
                //cb.setChecked(false);
                objCompras.setCompleted(1);
                db.getWritableDatabase().execSQL(
                        "UPDATE compras"+
                        " SET completed="+1+
                        " WHERE nomeLista='"+nomeListaAtual+"'"+
                        " AND nomeItem='"+objCompras.getNomeItem()+"'"
                );
                Log.d ( tag, "checkBox atualizado no banco para 1" );
            }else{//se NÃO esta marcado, mude para 0
                //cb.setChecked(true);
                objCompras.setCompleted(0);
                db.getWritableDatabase().execSQL(
                        "UPDATE compras"+
                        " SET completed="+0+
                        " WHERE nomeLista='"+nomeListaAtual+"'"+
                        " AND nomeItem='"+objCompras.getNomeItem()+"'"
                );
                Log.d ( tag, "checkBox atualizado no banco para 0" );
            }//fim if else
        }catch ( Exception e ) {
            Log.d ( tag, "FALHA AO ATUALIZAR NO BANCO" );
            Log.d ( tag, "" + e.getMessage() );
        }//fim try catch
    }//fim updateSQL
}//fim classe

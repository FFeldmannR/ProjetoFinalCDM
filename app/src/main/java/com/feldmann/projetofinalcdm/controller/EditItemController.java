package com.feldmann.projetofinalcdm.controller;
//
import android.content.Context;
import android.content.Intent;
import com.feldmann.projetofinalcdm.model.Compras;
import com.feldmann.projetofinalcdm.repository.ComprasRepository;
import com.feldmann.projetofinalcdm.views.ListadeCompras;
//
public class EditItemController implements Controller.controllerEditItem{
    private final Context context;
    private final Controller.msg msg;
    //
    public EditItemController(Context context) {
        this.context = context;
        msg = new MsgController(context, this.getClass().getName() );
    }
    @Override public void updateItem(String novoNomeItem, String novaQuantidade, Compras objCompras) {
        if (novoNomeItem == null || novaQuantidade == null){
            msg.messageToast("Um dos campos está vazio");
        }else{
            ComprasRepository.updateItem( novoNomeItem, novaQuantidade, objCompras.getId() ); //METODO UPDATE ITEM
            this.voltaParaListadeCompras( objCompras );
        }//fim if else
    }//fim metodo updateItem
    @Override public void deleteItem(Compras objCompras) {
        ComprasRepository.deleteItem( objCompras );
        this.voltaParaListadeCompras( objCompras );
    }//fim metodo deleteItem
    private void voltaParaListadeCompras(Compras objCompras){
        Intent in = new Intent(context, ListadeCompras.class);
        in.putExtra("USUARIO", objCompras.getDonoLista() );
        in.putExtra("NOMELISTA", objCompras.getNomeLista() );
        context.startActivity(in);
    }//fim voltaParaListadeCompras
}//fim class

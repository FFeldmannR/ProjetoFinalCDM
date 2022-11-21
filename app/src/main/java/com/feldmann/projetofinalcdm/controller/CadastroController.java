package com.feldmann.projetofinalcdm.controller;
//
import android.widget.TextView;
import com.feldmann.projetofinalcdm.adapters.*;
import com.feldmann.projetofinalcdm.model.*;
import com.feldmann.projetofinalcdm.repository.*;
//
public class CadastroController implements Controller.controllerCadastro {
    public CadastroController() {}
    @Override public void addUserToDB ( String nomeUser, String senhaUser, TextView tvCadastroIncorreto ) {
        UserRepository.createUserinDB ( nomeUser, senhaUser, tvCadastroIncorreto );
    }//fim addUserToDB
    @Override public void addListToDB ( String donoLista, String nomeLista, ListaAdapter adapter ) {
        ListasRepository.createList ( donoLista, nomeLista );
        adapter.addList( new Listas( (ListasRepository.getListas().size()+1), donoLista, nomeLista, 0 ) );
    }//fim addListToDB
    @Override public void addItemToDB ( String usuarioLogado, String nomeLista, String nomeItem, String qntdItem, int completed, ComprasAdapter adapter ) {
        ComprasRepository.createItem ( usuarioLogado, nomeLista, nomeItem, qntdItem, completed );
        adapter.addItem( new Compras( (ComprasRepository.getCompras().size()+1), usuarioLogado, nomeLista, nomeItem, qntdItem, completed) );
    }//fim addItemToDB
}//fim class

package com.feldmann.projetofinalcdm.controller;
//
import android.app.Activity;
import android.content.Context;
import android.widget.*;
import androidx.recyclerview.widget.RecyclerView;

import com.feldmann.projetofinalcdm.adapters.ComprasAdapter;
import com.feldmann.projetofinalcdm.model.*;
import java.util.List;
//
public class Controller {
    public interface controllerInstance {
        public void instanceController ();
    }
    public interface view {
        public Activity getActivity ();
        public Context getContext ();
        public void selectTableDB ( String nomeTabela );
    }
    public interface msg {
        public void messageToast ( String msg );
        public void logD ( String msg );
    }
    public interface controllerDataBase {
        public String criarTabelaUsers ();
        public String criarTabelaListas ();
        public String criarTabelaCompras ();
    }
    public interface controllerLogin {
        public void Login ( String login, String senha, TextView tvIncorreto );
    }
    public interface controllerCadastro {
        public void addItemToDB ( String usuarioLogado, String nomeLista,
                                String nomeItem, String qntdItem,
                                int completed, ComprasAdapter adapter );
        public void addUserToDB ( String nomeUser, String senhaUser, TextView tvCadastroIncorreto );
        public void addListToDB ( String donoLista, String nomeLista );
    }
    public interface controllerComprasAdapter {
        public void selectList ( Compras objCompras );
        public void updateCheckbox ( RecyclerView.ViewHolder holder, CheckBox checkBox,
                                    String nomeListaAtual, Compras compras );
        public void clickInfoItemToEdit ( LinearLayout infoItem, RecyclerView.ViewHolder holder, Compras compras );
    }
    public interface controllerEditItem {
        public void updateItem ( String novoNomeItem, String novaQuantidade, Compras objCompras );
        public void deleteItem ( Compras objCompras );
    }
    public interface controllerEditList {
        public void updateList ( String donoLista, String nomeLista, String novoNomeLista, TextView tvNomeListaErrado );
        public void deleteList ( String donoLista, String nomeLista );
    }
    public interface controllerAdapters {
        public void setAdapterItemList ( RecyclerView rv, List<Compras> compras, String lista );
        public void setAdapterLists ( RecyclerView rv, List<Listas> listas, String usuarioLogado );
    }
    public interface controllerUsers {
        public void updateUser ( String nomeUser, String novoNomeUser, TextView tvUserIncorreto );
        public void deleteUser ( String nomeUser );
    }
}//fim classe

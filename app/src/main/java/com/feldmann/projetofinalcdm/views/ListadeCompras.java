package com.feldmann.projetofinalcdm.views;
//
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.adapters.ComprasAdapter;
import com.feldmann.projetofinalcdm.controller.*;
import com.feldmann.projetofinalcdm.repository.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
public class ListadeCompras extends AppCompatActivity implements Controller.controllerInstance{
    private Controller.msg msg;
    private Controller.view view;
    private Controller.controllerAdapters adapters;
    private Controller.controllerCadastro cadastro;
    private String usuarioLogado, nomeLista;
    //
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_compras);
        this.instanceController();
        msg.logD("onCreate");
    }//fim onCreate
    @Override protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        this.toolBar("Lista: "+nomeLista);
        //
        ComprasRepository.getInstanceCompras( view.getContext(), nomeLista, usuarioLogado );
            //METODO PARA IR PARA ACTIVITY DE CRIAR ITEM
        ((ImageButton) findViewById(R.id.imgBtnConfigList)).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent in = new Intent( view.getContext(), EditListActivity.class );
                in.putExtra("USUARIOLOGADO", usuarioLogado);
                in.putExtra("NOMELISTA", nomeLista);
                view.getContext().startActivity(in);
            }
        });
        ((FloatingActionButton) findViewById(R.id.addNewItem)).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                /*
                cadastro.addItemToDB( usuarioLogado, nomeLista,
                        ((EditText) findViewById(R.id.etNomeItemAdd)).getText().toString(),
                        ((EditText) findViewById(R.id.etQntdItemAdd)).getText().toString(),
                        0 , ((ComprasAdapter)((RecyclerView) findViewById(R.id.RVCompras)).getAdapter()) );
                */
            }
        });
        adapters.setAdapterItemList(
                (RecyclerView) findViewById(R.id.RVCompras),
                ComprasRepository.getCompras(),
                nomeLista );

        view.selectTableDB( "compras" );
    }//fim onResume
    @Override protected void onDestroy() {
        super.onDestroy();
        msg.logD("onDestroy");
    }//fim onDestroy
    @Override public void instanceController() {
        this.view = new ViewController(this );
        this.msg = new MsgController(view.getContext(), this.getClass().getName() );
        this.adapters = new AdapterController( view.getContext() );
        this.cadastro = new CadastroController();
        usuarioLogado = getIntent().getStringExtra("USUARIO");
        nomeLista = getIntent().getStringExtra("NOMELISTA");
    }//fim instanceController

    private void popUpDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder( view.getContext() );
        //AlertDialog dialog;
        View view = getLayoutInflater().inflate(R.layout.popup, null);
        // insert nome item
        // insert qntd item
        // insert valor item
        dialogBuilder.setView( view );
        dialogBuilder.create().show();

        //btn salvar
        //btn cancelar
        ((FrameLayout) findViewById(R.id.FLbtnCancelarItemDialog)).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                //
                dialogBuilder.can
            }
        });
    }//

            // METODOS PARA OS BOTOES VOLTAR
    private void toolBar(String title){
        setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }//fim toolBar()
    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, ListaActivity.class);
            intent.putExtra("NOMEUSER", usuarioLogado);
            startActivity(intent);
            finish();
        }//fim switch
        return true;
    }//fimonOptionsItemSelected
    @Override public void onBackPressed() {
        try {
            Intent intent = new Intent(this, ListaActivity.class);
            intent.putExtra("NOMEUSER", usuarioLogado);
            startActivity( intent );
            finishAffinity();
        }catch (Exception e){
            msg.logD("FALHA AO VOLTAR\nERRO::: "+e.getMessage());
        }//fim try catch
    }//fim onBackPressed
}//fim class
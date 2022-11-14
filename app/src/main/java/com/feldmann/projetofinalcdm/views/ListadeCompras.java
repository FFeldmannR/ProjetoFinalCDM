package com.feldmann.projetofinalcdm.views;
//
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.*;
import com.feldmann.projetofinalcdm.repository.*;
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
        this.toolBar();
        usuarioLogado = getIntent().getStringExtra("USUARIO");
        nomeLista = getIntent().getStringExtra("NOMELISTA");
        setTitle(nomeLista);
        //
        ComprasRepository.getInstanceCompras( view.getContext(), nomeLista, usuarioLogado );
            //METODO PARA IR PARA ACTIVITY DE CRIAR ITEM
        ((ImageButton) findViewById(R.id.imgBtnConfigList)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent( view.getContext(), EditListActivity.class );
                in.putExtra("USUARIOLOGADO", usuarioLogado);
                in.putExtra("NOMELISTA", nomeLista);
                view.getContext().startActivity(in);
            }
        });
        ((ImageButton) findViewById(R.id.imgBtnAddItem)).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                cadastro.addItemToDB( usuarioLogado, nomeLista,
                        ((EditText) findViewById(R.id.etNomeItemAdd)).getText().toString(),
                        ((EditText) findViewById(R.id.etQntdItemAdd)).getText().toString(),
                        0 );
            }//fim onClick
        });//fim clickListener
        adapters.setAdapterItemList(
                (RecyclerView) findViewById(R.id.RVCompras),
                ComprasRepository.getCompras(),
                nomeLista
        );

        view.selectTableDB( "compras" );
    }//fim onResume
    @Override protected void onDestroy() {
        super.onDestroy();
        msg.logD("onDestroy");
    }//fim onDestroy
    @Override public void instanceController() {
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), this.getClass().getName() );
        this.adapters = new AdapterController( view.getContext() );
        this.cadastro = new CadastroController(view.getContext() );
    }//fim instanceController
            // METODOS PARA OS BOTOES VOLTAR
    private void toolBar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }//fim toolBar()
    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ( item.getItemId() ) {
            case android.R.id.home:
                Intent intent = new Intent(this, ListaActivity.class);
                intent.putExtra("NOMEUSER", usuarioLogado);
                startActivity( intent );
                finish();
                break;
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
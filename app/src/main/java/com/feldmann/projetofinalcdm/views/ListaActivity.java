package com.feldmann.projetofinalcdm.views;
//
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageButton;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.adapters.ListaAdapter;
import com.feldmann.projetofinalcdm.controller.*;
import com.feldmann.projetofinalcdm.repository.ListasRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
public class ListaActivity extends AppCompatActivity implements Controller.controllerInstance{
    private Controller.msg msg;
    private Controller.view view;
    private Controller.controllerCadastro cadastro;
    private Controller.controllerAdapters adapters;
    private String usuario;
    //
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listas);
        this.instanceController();
        usuario = getIntent().getStringExtra("NOMEUSER");
        msg.logD("onCreate");
    }//fim onCreate
    @Override protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        this.toolBar( usuario );

        ListasRepository.getInstanceListas( view.getContext(), usuario );

        ((ImageButton) findViewById(R.id.imgBtnConfigUser)).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent in = new Intent ( view.getContext(), EditUserActivity.class );
                in.putExtra("USUARIOLOGADO", usuario);
                view.getContext().startActivity( in );
            }
        });
        ((FloatingActionButton) findViewById(R.id.addNewList)).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                cadastro.addListToDB(
                        usuario, "Lista "+( ( ListasRepository.getListas().size() )+1),
                        ((ListaAdapter)((RecyclerView) findViewById(R.id.RVListas)).getAdapter())
                );
            }
        });
        adapters.setAdapterLists( (RecyclerView) findViewById(R.id.RVListas), ListasRepository.getListas(), usuario );
        //
        view.selectTableDB( "listas" );
    }//fim onResume
    @Override protected void onDestroy() {
        super.onDestroy();
        msg.logD("onDestroy");
    }//fim onDestroy
    @Override public void instanceController() {
        this.view = new ViewController(this, this);
        this.msg = new MsgController( view.getContext(), this.getClass().getName() );
        this.cadastro = new CadastroController();
        this.adapters = new AdapterController( view.getContext() );
    }//fim instanceController
            // METODOS PARA OS BOTOES VOLTAR
    private void toolBar(String title){
        setTitle( title );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }//fim toolBar()



    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finishAffinity();
        }//fim switch
        return true;
    }//fim onOptionsItemSelected
    @Override public void onBackPressed() {
        try {
            Intent intent = new Intent(this, LoginActivity.class );
            startActivity( intent );
            finishAffinity();
        }catch (Exception e){
            msg.logD("FALHA AO VOLTAR\nERRO::: "+e.getMessage() );
        }//fim try catch
    }//fim onBackPressed
}//fim class
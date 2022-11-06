package com.feldmann.projetofinalcdm.views;
//
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageButton;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.MsgController;
import com.feldmann.projetofinalcdm.controller.ViewController;
import com.feldmann.projetofinalcdm.repository.DBListas;
import com.feldmann.projetofinalcdm.repository.ListasRepository;
//
public class ListaActivity extends AppCompatActivity implements Controller.controllerInstance{
    private Controller.msg msg;
    private Controller.view view;
    private DBListas db;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listas);
        this.instanceController();
        db = new DBListas(view.getContext());
        msg.logD("onCreate");
    }//fim onCreate

    @Override
    protected void onResume() {
        super.onResume();
        msg.logD("onResume");
        ListasRepository.getInstanceListas(view.getContext(), db.getWritableDatabase() );
        ListasRepository.addToDB(
                ((ImageButton) findViewById(R.id.imgBtnAddList)),
                getIntent().getStringExtra("NOMEUSER"),
                getNomeLista(),//tamanho db
                db.getWritableDatabase()
        );
        //
        ListasRepository.setAdapterListas( (RecyclerView) findViewById(R.id.RVListas) );
        //
    }//fim onResume
    @Override
    public void instanceController() {
        this.view = new ViewController(this, this);
        this.msg = new MsgController(view.getContext(), this.getClass().getName().toString() );
    }//fim instanceController
    private String getNomeLista(){
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT * FROM listas", null);
        return "Lista "+( Integer.valueOf( ( cursor.getCount() )+1 ).toString() );
    }
}//fim class
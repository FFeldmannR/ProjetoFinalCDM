package com.feldmann.projetofinalcdm.repository;
//
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import com.feldmann.projetofinalcdm.controller.Controller;
import com.feldmann.projetofinalcdm.controller.DataBaseController;

//
public class DBListas extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "listas.db";
    private static final int VERSAO_BANCO = 1;
    private Controller.controllerDataBase dataBase;
    //
    public DBListas(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
        Log.d("DBListas", "Construtor");
        this.dataBase = new DataBaseController();
    }//fim construtor
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DBListas", "onCreate");
        db.execSQL( dataBase.criarTabelaUsers() );
        db.execSQL( dataBase.criarTabelaListas() );
        db.execSQL( dataBase.criarTabelaCompras() );
    }//fim onCreate
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DBListas", "onUpgrade");
    }//fim onUpgrade
}//fim class

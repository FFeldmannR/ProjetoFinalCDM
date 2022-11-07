package com.feldmann.projetofinalcdm.adapters;
//
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.model.Compras;
import java.util.List;
//
public class ComprasAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //
    private List<Compras> compras;
    private SQLiteDatabase sqlWrite;
    //
    public ComprasAdapter(List<Compras> compras, SQLiteDatabase sqlWrite) {
        this.compras = compras;
        this.sqlWrite = sqlWrite;
    }
    //
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutViewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_item_layout, parent, false);
        return new ComprasViewHolder(layoutViewHolder);
    }
    //
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Compras objCompras = compras.get(position);
        //
        ((TextView)((ComprasViewHolder) holder).view.findViewById(R.id.tvNomeItem)).setText( objCompras.getNomeItem() );
        ((TextView)((ComprasViewHolder) holder).view.findViewById(R.id.tvQuantidade)).setText( objCompras.getQuantidade() );
        //checkbox
            if ( objCompras.isCompleted().equals("1") ){
                ((CheckBox)((ComprasViewHolder) holder).view.findViewById(R.id.checkBoxItem)).setChecked(true);
            }else{
                ((CheckBox)((ComprasViewHolder) holder).view.findViewById(R.id.checkBoxItem)).setChecked(false);
            }
            this.itemClick(holder, objCompras.getNomeLista() );
        //
    }
    //
    @Override
    public int getItemCount() {
        return compras.size();
    }
    //
    private void itemClick(RecyclerView.ViewHolder holder, String nomeLista){
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = ((CheckBox)((ComprasViewHolder) holder).view.findViewById(R.id.checkBoxItem));
                try{
                    buttonEffect(v.getContext(), holder);
                }catch (Exception e){
                    Log.d("itemClick", "FALHA NO EFEITO");
                }
                try{
                    if (cb.isChecked()){
                        sqlWrite.execSQL("UPDATE TABLE compras SET completed = '0' WHERE nomeLista = "+nomeLista );
                    }else{
                        sqlWrite.execSQL("UPDATE TABLE compras SET completed = '1' WHERE nomeLista = "+nomeLista );
                    }
                }catch (Exception e){
                    Log.d("itemClick", "FALHA AO ATUALIZAR NO BANCO");
                }
                try{
                    if (cb.isChecked()){
                        cb.setChecked(false);
                    }else{
                        cb.setChecked(true);
                    }
                }catch (Exception e){
                    Log.d("itemClick", "FALHA AO MARCAR CHECKBOX");
                }
                //Log.d("teste click", )
            }
        });
    }
    //
    private void buttonEffect(Context context, RecyclerView.ViewHolder holder){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
            holder.itemView.setBackgroundResource(outValue.resourceId);
        }
    }//fim buttonEffect
    //
}// fim classe
//
//
class ComprasViewHolder extends RecyclerView.ViewHolder{
    public View view;
    public ComprasViewHolder(@NonNull View itemView){
        super(itemView);
        view = itemView;
    }
}
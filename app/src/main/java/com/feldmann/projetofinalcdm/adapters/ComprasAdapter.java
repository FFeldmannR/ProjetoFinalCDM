package com.feldmann.projetofinalcdm.adapters;

import android.content.Context;
import android.os.Build;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.model.Compras;
import com.feldmann.projetofinalcdm.model.Listas;

import java.util.List;

public class ComprasAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //
    private List<Compras> compras;
    //
    public ComprasAdapter(List<Compras> compras) {
        this.compras = compras;
    }
    //
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutViewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_item_layout, parent, false);
        return new ListaViewHolder(layoutViewHolder);
    }
    //
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Compras objCompras = compras.get(position);
        //
        //nome item
        ((TextView)((ListaViewHolder) holder).view.findViewById(R.id.tvnome)).setText( objCompras.getNomeItem() );
        //quantidade
        //checkbox
        //
    }
    //
    @Override
    public int getItemCount() {
        return compras.size();
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
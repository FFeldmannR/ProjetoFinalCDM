package com.feldmann.projetofinalcdm.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.model.Lista;
import com.feldmann.projetofinalcdm.views.ListaDeCompras;

import java.util.List;

public class AdapterListas  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Lista> listas;

    public AdapterListas(List<Lista> listas) {
        this.listas = listas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutViewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_lista_layout, parent, false);
        return new ListasViewHolder(layoutViewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Lista objLista = listas.get(position);
        ((TextView)((ListasViewHolder) holder).itemView.findViewById(R.id.tvNomeLista)).setText( objLista.getNome() );
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ao clicar no item...
                Intent in = new Intent(v.getContext(), ListaDeCompras.class);
                in.putExtra("NOMELISTA", objLista.getNome() );
                v.getContext().startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listas.size();
    }
}
class ListasViewHolder extends RecyclerView.ViewHolder{
    private View view;
    public ListasViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
    }
}
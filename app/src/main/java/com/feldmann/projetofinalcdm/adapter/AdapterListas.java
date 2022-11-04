package com.feldmann.projetofinalcdm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.model.Lista;

import java.util.List;

public class AdapterListas  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Lista> listas;

    public AdapterListas(List<Lista> listas) {
        this.listas = listas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutViewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_item_layout, parent, false);
        return new ListasViewHolder(layoutViewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
class ListasViewHolder extends RecyclerView.ViewHolder{
    private View view;
    public ListasViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
    }
}
package com.feldmann.projetofinalcdm.adapters;
//
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.feldmann.projetofinalcdm.R;
import com.feldmann.projetofinalcdm.model.Listas;
import java.util.List;
//
public class ListaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //
    private List<Listas> listas;
    //
    public ListaAdapter(List<Listas> listas) {
        this.listas = listas;
    }
    //
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutViewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_lista_layout, parent, false);
        return new ListaViewHolder(layoutViewHolder);
    }
    //
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Listas objListas = listas.get(position);
        //
        ((TextView)((ListaViewHolder) holder).view.findViewById(R.id.tvNomeLista)).setText( objListas.getNomeLista() );
        //
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonEffect(v.getContext(), holder);
                Toast.makeText(v.getContext(), objListas.getNomeLista()+" clicada", Toast.LENGTH_SHORT).show();
            }
        });
        //
    }
    //
    @Override
    public int getItemCount() {
        return listas.size();
    }
    //
    private void buttonEffect(Context context, RecyclerView.ViewHolder holder){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
            holder.itemView.setBackgroundResource(outValue.resourceId);
        }
    }
}
class ListaViewHolder extends RecyclerView.ViewHolder{
    public View view;
    public ListaViewHolder(@NonNull View itemView){
        super(itemView);
        view = itemView;
    }
}
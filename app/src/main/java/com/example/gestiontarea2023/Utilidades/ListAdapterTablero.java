package com.example.gestiontarea2023.Utilidades;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gestiontarea2023.Model.Tablero;
import com.example.gestiontarea2023.R;
import com.example.gestiontarea2023.View.DetalleTableroActivity;
import java.util.List;

public class ListAdapterTablero extends RecyclerView.Adapter<ListAdapterTablero.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Tablero> listaTableros;
    private Context context;
    private Activity activity;
    public ListAdapterTablero(List<Tablero> itemList, Context context){
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.listaTableros = itemList;
        this.activity = (Activity) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_tablero,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tablero tablero = listaTableros.get(position);
        holder.txt_titulo.setText(tablero.getNombre_tablero());
        holder.txt_cantidad_tareas.setText("Tareas(s): " + (tablero.getTareas() != null ? tablero.getTareas().size() : 0));
        holder.bindData(tablero);
    }

    @Override
    public int getItemCount() {
        return listaTableros.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_titulo,txt_cantidad_tareas;
        ViewHolder(View holder) {
            super(holder);
            txt_titulo = holder.findViewById(R.id.titulo_tablero);
            txt_cantidad_tareas = holder.findViewById(R.id.cantidad_tareas);
        }
        void bindData(Tablero tablero){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, DetalleTableroActivity.class).putExtra("tablero",tablero));
                    activity.overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
                }
            });
        }
    }
    public void setItems(List<Tablero> items){
        listaTableros = items;
        notifyDataSetChanged();
    }
}

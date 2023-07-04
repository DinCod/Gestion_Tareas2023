package com.example.gestiontarea2023.Utilidades;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gestiontarea2023.Model.Tarea;
import com.example.gestiontarea2023.R;
import com.example.gestiontarea2023.View.DetalleTareaActivity;
import java.util.List;

public class ListAdapterTarea extends RecyclerView.Adapter<ListAdapterTarea.ViewHolder>{

    private LayoutInflater layoutInflater;
    private List<Tarea>  listaTareas;
    private Context context;
    private Activity activity;

    public ListAdapterTarea(List<Tarea> itemList, Context context){
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.listaTareas = itemList;
        this.activity = (Activity) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_tarea, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tarea tarea  = listaTareas.get(position);
        holder.txt_titulo.setText(tarea.getTitulo_tarea());
        holder.txt_descripcion_tarea.setText(tarea.getDescripcion());
        holder.txt_estado_tarea.setText(tarea.getEstado_tarea());
        String estadoTarea = tarea.getEstado_tarea();
        int color_estado=0;
        if (estadoTarea.equals("No iniciada")) {
            color_estado = Color.RED;
        } else if (estadoTarea.equals("En curso")) {
            color_estado = Color.rgb(255, 165, 0);
        } else if (estadoTarea.equals("Completada")) {
            color_estado = Color.rgb(19, 173, 9);
        }
        holder.txt_estado_tarea.setTextColor(color_estado);
        holder.binData(tarea);
    }


    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    public void setItems(List<Tarea> items){
        listaTareas = items;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_titulo,txt_estado_tarea, txt_descripcion_tarea;
        ViewHolder(View view){
            super(view);
            txt_titulo      = (TextView) view.findViewById(R.id.titulo_tarea);
            txt_estado_tarea = (TextView) view.findViewById(R.id.estado_tarea);
            txt_descripcion_tarea = (TextView) view.findViewById(R.id.descripcion_tarea);
        }
        void binData(Tarea tarea){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, DetalleTareaActivity.class).putExtra("tarea",tarea));
                    activity.overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
                }
            });
        }
    }
}

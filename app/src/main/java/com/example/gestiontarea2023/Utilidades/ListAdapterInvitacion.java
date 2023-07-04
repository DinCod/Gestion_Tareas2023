package com.example.gestiontarea2023.Utilidades;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gestiontarea2023.Model.Invitacion;
import com.example.gestiontarea2023.R;
import java.util.List;

public class ListAdapterInvitacion extends RecyclerView.Adapter<ListAdapterInvitacion.ViewHolder>{
    private LayoutInflater layoutInflater;
    private List<Invitacion> listInvitacion;
    private Context context;
    private Activity activity;

    public ListAdapterInvitacion(List<Invitacion> itemList, Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.listInvitacion = itemList;
        this.activity = (Activity) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_invitacion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Invitacion invitacion = listInvitacion.get(position);
        holder.txt_usuario_invita.setText("Emisor: "+invitacion.getUsuario_invita());
        holder.txt_tablero_invitado.setText("Tablero: "+invitacion.getTablero_invitado());
        holder.bindData(invitacion);
    }

    @Override
    public int getItemCount() {
        return listInvitacion.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_usuario_invita, txt_tablero_invitado;
        ViewHolder(View holder) {
            super(holder);
            txt_usuario_invita = holder.findViewById(R.id.txt_usuario_invita);
            txt_tablero_invitado  = holder.findViewById(R.id.txt_tablero_invitado);
        }
        void bindData(Invitacion invitacion){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    public void setItems(List<Invitacion> items) {
        listInvitacion = items;
    }

}

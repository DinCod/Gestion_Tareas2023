package com.example.gestiontarea2023.View;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gestiontarea2023.Model.Comprobacion;
import com.example.gestiontarea2023.Model.Tarea;
import com.example.gestiontarea2023.R;
import com.example.gestiontarea2023.Utilidades.ListAdapterComprobacion;
import com.example.gestiontarea2023.ViewModel.ComprobacionViewModel;
import java.util.List;

public class ModalComprobacionDialog extends DialogFragment{

    private Context context;
    private ListAdapterComprobacion listAdapterComprobacion;
    private ComprobacionViewModel comprobacionViewModel;
    private Tarea tarea;
    private LinearLayout txt_informacion;
    private TextView comprobaciones_realizadas, total_comprobaciones;

    public ModalComprobacionDialog(Tarea tarea){
        this.tarea = tarea;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modal_comprobacion,container,false);
        setRetainInstance(true);
        txt_informacion = view.findViewById(R.id.txt_informacion);
        total_comprobaciones = view.findViewById(R.id.total_comprobaciones);
        comprobaciones_realizadas = view.findViewById(R.id.comprobaciones_realizadas);
        comprobacionViewModel  = new ComprobacionViewModel(context);
        comprobacionViewModel.ReturTarea(tarea);
        comprobacionViewModel.getListaComprobacionLiveData().observe(getViewLifecycleOwner(), new Observer<List<Comprobacion>>() {
            @Override
            public void onChanged(List<Comprobacion> comprobaciones) {
                listAdapterComprobacion = new ListAdapterComprobacion(comprobaciones,context, ModalComprobacionDialog.this);
                RecyclerView recyclerView = view.findViewById(R.id.lista_recicleview_comprobacion);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(listAdapterComprobacion);
                listAdapterComprobacion.setItems(comprobaciones);
                listAdapterComprobacion.notifyDataSetChanged();
                if(listAdapterComprobacion.getItemCount()<=0){
                    txt_informacion.setVisibility(View.VISIBLE);
                }
                comprobaciones_realizadas.setText(""+listAdapterComprobacion.getItemsRealizados(comprobaciones));
                total_comprobaciones.setText(""+listAdapterComprobacion.getItemCount());
            }
        });
        comprobacionViewModel.getEliminar().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean response) {
                if(response){
                    message("Comprobación eliminada");
                }
            }
        });
        comprobacionViewModel.getUpdate().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean response) {
                if(response){
                    message("Comprobación actualizada");
                }
            }
        });
        return view;
    }

    public void message(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        comprobacionViewModel.Lista();
    }

    public void Eliminar(Comprobacion comprobacion){
        comprobacionViewModel.Eliminar(comprobacion);
    }
    public void CheckComprobante(Comprobacion comprobacion){
        comprobacionViewModel.Actualizar(comprobacion);
    }

}

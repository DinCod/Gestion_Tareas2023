package com.example.gestiontarea2023.View;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.gestiontarea2023.Model.Tablero;
import com.example.gestiontarea2023.Model.Usuario;
import com.example.gestiontarea2023.R;
import com.example.gestiontarea2023.Utilidades.ListAdapterTablero;
import com.example.gestiontarea2023.ViewModel.TableroViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class TableroFragment extends Fragment implements View.OnClickListener {

    private Usuario usuario;
    private Context context;
    private FloatingActionButton btn_agregar_tablero;
    private TableroViewModel tableroViewModel;
    private Tablero tablero;
    private AlertDialog dialog;
    private ListAdapterTablero adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            usuario = (Usuario) getArguments().getSerializable("usuario");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tablero, container, false);
        getActivity().setTitle("Mi Tablero");
        btn_agregar_tablero = (FloatingActionButton) view.findViewById(R.id.btn_agregar_tablero);
        btn_agregar_tablero.setOnClickListener(this);
        tableroViewModel = new TableroViewModel(context);
        tableroViewModel.ReturnUsuario(usuario);
        tableroViewModel.getRegistro().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean response) {
                if(response){
                    message("Creando tablero...");
                    dialog.dismiss();
                }
            }
        });
        tableroViewModel.getListaTableroLiveData().observe(getViewLifecycleOwner(), new Observer<List<Tablero>>() {
            @Override
            public void onChanged(List<Tablero> tableros) {
                adapter = new ListAdapterTablero(tableros, context);
                RecyclerView recyclerView = view.findViewById(R.id.listRecycleView_tablero);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(adapter);
                adapter.setItems(tableros);
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        tableroViewModel.Lista();
    }
    public void message(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View event_click) {
        if(btn_agregar_tablero==event_click){ // https://www.youtube.com/watch?v=Kz9TkDY2sP8
            LayoutInflater inflater = getLayoutInflater();
            View alertLayout = inflater.inflate(R.layout.modal_agregar_tablero, null);
            EditText titulo = alertLayout.findViewById(R.id.dialog_titulo_tablero);
            AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
            alert.setTitle("Crear Nuevo Tablero");
            alert.setView(alertLayout);
            alert.setNegativeButton("Cancelar",null);
            alert.setPositiveButton( "Aceptar",null); //https://www.youtube.com/watch?v=veOZTvAdzJ8
            dialog = alert.create();
            dialog.show();
            Button positive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            positive.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View view) {
                    String titulo_tablero  = titulo.getText().toString().trim();
                    int id_usuario         = usuario.getId_usuario();
                    if(titulo_tablero.isEmpty() && id_usuario == 0){
                        message("Complete todos los campos");
                    }else{
                        tablero = new Tablero(id_usuario,titulo_tablero);
                        tableroViewModel.Registro(tablero);
                    }
                }});
        }
    }
}

package com.example.gestiontarea2023.View;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.gestiontarea2023.Model.Invitacion;
import com.example.gestiontarea2023.Model.Usuario;
import com.example.gestiontarea2023.R;
import com.example.gestiontarea2023.Utilidades.ListAdapterInvitacion;
import com.example.gestiontarea2023.ViewModel.InvitacionViewModel;
import java.util.List;

public class SegundoFragment extends Fragment {

    private Usuario usuario;
    private Context context;
    private InvitacionViewModel invitacionViewModel;
    private ListAdapterInvitacion listAdapterInvitacion;
    private TextView txt_resultado;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            usuario = (Usuario) getArguments().getSerializable("usuario");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_segundo, container, false);
        getActivity().setTitle("Mis Invitaciones");
        txt_resultado = view.findViewById(R.id.resultado);
        invitacionViewModel  = new InvitacionViewModel(context);
        invitacionViewModel.ReturnUsuario(usuario);
        invitacionViewModel.getListaInvitacionLiveData().observe(getViewLifecycleOwner(), new Observer<List<Invitacion>>() {
            @Override
            public void onChanged(List<Invitacion> invitacions) {
                listAdapterInvitacion = new ListAdapterInvitacion(invitacions,context);
                RecyclerView recyclerView = view.findViewById(R.id.listRecycleView_invitacion);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(listAdapterInvitacion);
                listAdapterInvitacion.setItems(invitacions);
                if(listAdapterInvitacion.getItemCount()<=0){
                    txt_resultado.setVisibility(View.VISIBLE);
                }
            }
        });
        return view;
    }

    public void message(String message){
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
        invitacionViewModel.Lista();
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        invitacionViewModel.Lista();
    }
}
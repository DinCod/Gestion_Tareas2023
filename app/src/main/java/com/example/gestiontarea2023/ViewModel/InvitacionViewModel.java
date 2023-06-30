package com.example.gestiontarea2023.ViewModel;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gestiontarea2023.Conexion.Servidor;
import com.example.gestiontarea2023.Model.Invitacion;
import java.util.HashMap;
import java.util.Map;

public class InvitacionViewModel {

    private Context context;
    private Servidor conexion = new Servidor();
    private MutableLiveData<Boolean> registro = new MutableLiveData<>();
    public LiveData<Boolean> getRegistro() {return registro;}

    public InvitacionViewModel(Context context){
        this.context = context;
    }

    public void Registro(Invitacion invitacion) {
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url = conexion.url_local() + "invitacion.php?accion=1";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("true")){
                            registro.setValue(true);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        registro.setValue(false);
                        Toast.makeText(context, "Error: "+ error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_usuario_emisor", String.valueOf(invitacion.getId_usuario_emisor()));
                params.put("id_usuario_receptor", String.valueOf(invitacion.getId_usuario_receptor()));
                params.put("id_tablero", String.valueOf(invitacion.getId_tablero()));
                params.put("tipo_invitacion", invitacion.getTipo_invitacion());
                return params;
            }
        };
        queue.add(stringRequest);
    }

}

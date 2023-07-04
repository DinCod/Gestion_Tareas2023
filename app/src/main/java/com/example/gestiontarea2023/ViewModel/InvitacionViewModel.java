package com.example.gestiontarea2023.ViewModel;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gestiontarea2023.Conexion.Servidor;
import com.example.gestiontarea2023.Model.Invitacion;
import com.example.gestiontarea2023.Model.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvitacionViewModel {

    private Context context;
    private Usuario usuario;
    private Invitacion invitacion;
    private Servidor conexion = new Servidor();
    private MutableLiveData<Boolean> registro = new MutableLiveData<>();
    public LiveData<Boolean> getRegistro() {return registro;}
    private MutableLiveData<List<Invitacion>> listaInvitacionLiveData = new MutableLiveData<>();
    public LiveData<List<Invitacion>> getListaInvitacionLiveData() { return listaInvitacionLiveData;}

    public Usuario ReturnUsuario(Usuario usuario){
        this.usuario = usuario;
        return  this.usuario;
    }

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

    public void Lista(){
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url = conexion.url_local() + "invitacion.php?accion=2&id_usuario_receptor=" + usuario.getId_usuario();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Invitacion> listInvitacion = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                invitacion = new Invitacion();
                                invitacion.setId_invitaciones(jsonObject.getInt("id_invitacion"));
                                invitacion.setId_usuario_emisor(jsonObject.getInt("id_usuario_emisor"));
                                invitacion.setId_usuario_receptor(jsonObject.getInt("id_usuario_receptor"));
                                invitacion.setId_tablero(jsonObject.getInt("id_tablero"));
                                invitacion.setEstado_invitacion(jsonObject.getString("estado_invitacion"));
                                invitacion.setFecha_aceptacion(jsonObject.getString("fecha_aceptacion"));
                                invitacion.setFecha_expiracion(jsonObject.getString("fecha_expiracion"));
                                invitacion.setTipo_invitacion(jsonObject.getString("tipo_invitacion"));
                                invitacion.setUsuario_invita(jsonObject.getString("usuario_invita"));
                                invitacion.setTablero_invitado(jsonObject.getString("tablero_invitado"));
                                listInvitacion.add(invitacion);
                            }
                            listaInvitacionLiveData.setValue(listInvitacion);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error en la conexión.", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonArrayRequest);
    }

}

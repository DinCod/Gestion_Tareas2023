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
import com.example.gestiontarea2023.Model.Tablero;
import com.example.gestiontarea2023.Model.Tarea;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TareaViewModel {
    private Tablero tablero;
    private Tarea   tarea;
    private Context context;
    private Servidor conexion = new Servidor();
    private MutableLiveData<List<Tarea>> listaTareaLiveData = new MutableLiveData<>();
    public LiveData<List<Tarea>> getListaTareaLiveData() { return listaTareaLiveData;}
    private MutableLiveData<Boolean> registro = new MutableLiveData<>();
    public LiveData<Boolean> getRegistro() {return registro;}
    private MutableLiveData<Boolean> editar = new MutableLiveData<>();
    public LiveData<Boolean> getEditar() {return editar;}
    public TareaViewModel(Context context){
        this.context = context;
    }
    public Tablero ReturnTablero(Tablero tablero){
        this.tablero = tablero;
        return this.tablero;
    }
    public Tarea ReturnTarea(Tarea tarea){
        this.tarea = tarea;
        return  this.tarea;
    }
    public void Lista(){
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url = conexion.url_local() + "tarea.php?accion=1&id_tablero=" + tablero.getId_tablero();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Tarea> listTarea = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                tarea = new Tarea();
                                tarea.setId_tarea(jsonObject.getInt("id_tarea"));
                                tarea.setId_tablero(jsonObject.getInt("id_tablero"));
                                tarea.setTitulo_tarea(jsonObject.getString("titulo_tarea"));
                                tarea.setDescripcion(jsonObject.getString("descripcion"));
                                tarea.setEstado_tarea(jsonObject.getString("estado_tarea"));
                                tarea.setFecha_ini(jsonObject.getString("fecha_ini"));
                                tarea.setFecha_fin(jsonObject.getString("fecha_fin"));
                                listTarea.add(tarea);
                            }
                            listaTareaLiveData.setValue(listTarea);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }},new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error en la conexión.", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonArrayRequest);
    }

    public void Registro(Tarea tarea) {
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url = conexion.url_local() + "tarea.php?accion=2";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("true")) {
                            registro.setValue(true);
                            Lista();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        registro.setValue(false);
                        Toast.makeText(context, "Error al registrar.", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_tablero", String.valueOf(tarea.getId_tablero()));
                params.put("titulo_tarea", tarea.getTitulo_tarea());
                params.put("descripcion", tarea.getDescripcion());
                params.put("estado_tarea", tarea.getEstado_tarea());
                params.put("fecha_ini", tarea.getFecha_ini());
                params.put("fecha_fin", tarea.getFecha_fin());
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void Editar(Tarea tarea) {
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url = conexion.url_local() + "tarea.php?accion=3";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("true")) {
                            editar.setValue(true);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        editar.setValue(false);
                        Toast.makeText(context, "Error al registrar.", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_tarea", String.valueOf(tarea.getId_tarea()));
                params.put("titulo_tarea", tarea.getTitulo_tarea());
                params.put("descripcion", tarea.getDescripcion());
                params.put("estado_tarea", tarea.getEstado_tarea());
                params.put("fecha_ini", tarea.getFecha_ini());
                params.put("fecha_fin", tarea.getFecha_fin());
                return params;
            }
        };
        queue.add(stringRequest);
    }

}

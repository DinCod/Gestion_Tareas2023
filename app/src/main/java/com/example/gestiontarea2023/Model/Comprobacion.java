package com.example.gestiontarea2023.Model;

import java.io.Serializable;

public class Comprobacion implements Serializable {
    private int id_comprobacion;
    private int id_tarea;
    private String titulo_comprobacion;
    private boolean estado_comprobante;
    private int estado;
    private String fecharegistro;
    private String fechaactualiza;
    private int id_usuarioregistro;
    private int id_usuarioactualiza;


    public Comprobacion(int id_tarea, String titulo_comprobacion, boolean estado_comprobante) {
        this.id_tarea = id_tarea;
        this.titulo_comprobacion = titulo_comprobacion;
        this.estado_comprobante = estado_comprobante;
    }

    public Comprobacion() {
    }

    public int getId_comprobacion() {
        return id_comprobacion;
    }

    public void setId_comprobacion(int id_comprobacion) {
        this.id_comprobacion = id_comprobacion;
    }

    public int getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(int id_tarea) {
        this.id_tarea = id_tarea;
    }

    public String getTitulo_comprobacion() {
        return titulo_comprobacion;
    }

    public void setTitulo_comprobacion(String titulo_comprobacion) {
        this.titulo_comprobacion = titulo_comprobacion;
    }

    public boolean getEstado_comprobante() {
        return estado_comprobante;
    }

    public void setEstado_comprobante(boolean estado_comprobante) {
        this.estado_comprobante = estado_comprobante;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(String fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public String getFechaactualiza() {
        return fechaactualiza;
    }

    public void setFechaactualiza(String fechaactualiza) {
        this.fechaactualiza = fechaactualiza;
    }

    public int getId_usuarioregistro() {
        return id_usuarioregistro;
    }

    public void setId_usuarioregistro(int id_usuarioregistro) {
        this.id_usuarioregistro = id_usuarioregistro;
    }

    public int getId_usuarioactualiza() {
        return id_usuarioactualiza;
    }

    public void setId_usuarioactualiza(int id_usuarioactualiza) {
        this.id_usuarioactualiza = id_usuarioactualiza;
    }
}

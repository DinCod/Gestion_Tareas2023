package com.example.gestiontarea2023.Model;

import java.io.Serializable;
public class Invitacion implements Serializable{

    private int id_invitaciones;
    private int id_usuario_emisor;
    private int id_usuario_receptor;
    private int id_tablero;
    private String estado_invitacion;
    private String fecha_aceptacion;
    private String fecha_expiracion;
    private String tipo_invitacion;
    private int estado;
    private String fecharegistro;
    private String fechaactualiza;
    private int id_usuarioregistro;
    private int id_usuarioactualiza;
    //datos agregados
    private String usuario_invita;
    private String tablero_invitado;

    public String getUsuario_invita() {
        return usuario_invita;
    }

    public void setUsuario_invita(String usuario_invita) {
        this.usuario_invita = usuario_invita;
    }

    public String getTablero_invitado() {
        return tablero_invitado;
    }

    public void setTablero_invitado(String tablero_invitado) {
        this.tablero_invitado = tablero_invitado;
    }

    public Invitacion(int id_usuario_emisor, int id_usuario_receptor, int id_tablero, String tipo_invitacion) {
        this.id_usuario_emisor = id_usuario_emisor;
        this.id_usuario_receptor = id_usuario_receptor;
        this.id_tablero = id_tablero;
        this.tipo_invitacion = tipo_invitacion;
    }

    public Invitacion() {
    }

    public int getId_invitaciones() {
        return id_invitaciones;
    }

    public void setId_invitaciones(int id_invitaciones) {
        this.id_invitaciones = id_invitaciones;
    }

    public int getId_usuario_emisor() {
        return id_usuario_emisor;
    }

    public void setId_usuario_emisor(int id_usuario_emisor) {
        this.id_usuario_emisor = id_usuario_emisor;
    }

    public int getId_usuario_receptor() {
        return id_usuario_receptor;
    }

    public void setId_usuario_receptor(int id_usuario_receptor) {
        this.id_usuario_receptor = id_usuario_receptor;
    }

    public int getId_tablero() {
        return id_tablero;
    }

    public void setId_tablero(int id_tablero) {
        this.id_tablero = id_tablero;
    }

    public String getEstado_invitacion() {
        return estado_invitacion;
    }

    public void setEstado_invitacion(String estado_invitacion) {
        this.estado_invitacion = estado_invitacion;
    }

    public String getFecha_aceptacion() {
        return fecha_aceptacion;
    }

    public void setFecha_aceptacion(String fecha_aceptacion) {
        this.fecha_aceptacion = fecha_aceptacion;
    }

    public String getFecha_expiracion() {
        return fecha_expiracion;
    }

    public void setFecha_expiracion(String fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }

    public String getTipo_invitacion() {
        return tipo_invitacion;
    }

    public void setTipo_invitacion(String tipo_invitacion) {
        this.tipo_invitacion = tipo_invitacion;
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

<?php

include_once("../GestorMySQL/conexion.php");

class invitacion extends conexion{

    var $id_invitacion;
    var $id_usuario_emisor;
    var $id_usuario_receptor;
    var $id_tablero;
    var $estado_invitacion;
    var $fecha_aceptacion;
    var $fecha_expiracion;
    var $tipo_invitacion;
    var $estado;
    var $fecharegistro;
    var $fechaactualiza;
    var $id_usuarioregistro;
    var $id_usuarioactualiza; 

    public function create(){
        $query="INSERT INTO invitacion VALUES(0, '$this->id_usuario_emisor', '$this->id_usuario_receptor', '$this->id_tablero',
        '$this->estado_invitacion','$this->fecha_aceptacion', '$this->fecha_expiracion', '$this->tipo_invitacion', 1, now(), now(), 1, 1)";
        $rs=mysqli_query($this->Mysql(),$query);
        mysqli_close($this->Mysql());
        return $rs;
    }

}
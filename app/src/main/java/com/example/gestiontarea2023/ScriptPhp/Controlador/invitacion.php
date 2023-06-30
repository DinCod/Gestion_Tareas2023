<?php 

header('Access-Control-Allow-Origin: *');
require_once("../Utilidades/StrCaracter.php");
include_once("../Modelo/invitacion.php");

$obj_invitacion = new invitacion();
$accion = $_REQUEST["accion"];

switch($accion){
    
    case 1:{
        $obj_invitacion->id_usuario_emisor = intval(strClean($_REQUEST["id_usuario_emisor"]));
        $obj_invitacion->id_usuario_receptor = intval(strClean($_REQUEST["id_usuario_receptor"]));
        $obj_invitacion->id_tablero = intval(strClean($_REQUEST["id_tablero"]));
        $obj_invitacion->estado_invitacion = "Pendiente";
        $obj_invitacion->fecha_expiracion = date('Y-m-d', strtotime('+4 days'));
        $obj_invitacion->tipo_invitacion  = strClean($_REQUEST["tipo_invitacion"]);
        $rs = $obj_invitacion->create();
        echo json_encode($rs);
        break;
    }
}

?>
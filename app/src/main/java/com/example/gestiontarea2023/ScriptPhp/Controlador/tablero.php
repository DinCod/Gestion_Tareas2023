<?php 

header('Access-Control-Allow-Origin: *');
require_once("../Utilidades/StrCaracter.php");
include_once("../Modelo/tablero.php");

$obj_tablero = new tablero();
$accion = $_REQUEST["accion"];

switch($accion){

    case 1:{
        $obj_tablero->id_usuario = intval(strClean($_REQUEST["id_usuario"]));
        $obj_tablero->nombre_tablero = strClean($_REQUEST["nombre_tablero"]);
        $rs = $obj_tablero->create();
        echo json_encode($rs);
        break;
    }
    case 2:{
        $obj_tablero->id_usuario = intval(strClean($_REQUEST["id_usuario"]));
        $rs = $obj_tablero->list();
        echo json_encode($rs);
        break;
    }
    case 3:{
        $obj_tablero->id_tablero     = intval(strClean($_REQUEST["id_tablero"]));
        $obj_tablero->nombre_tablero = strClean($_REQUEST["nombre_tablero"]);
        $rs = $obj_tablero->update();
        echo json_encode($rs);
        break;
    }
    case 4:{
        $obj_tablero->id_tablero  = intval(strClean($_REQUEST["id_tablero"]));
        $rs = $obj_tablero->count();
        echo json_encode($rs);
        break;
    }
}

?>
<?php 

header('Access-Control-Allow-Origin: *');
require_once("../Utilidades/StrCaracter.php");
include_once("../Modelo/tarea.php");

$obj_tarea = new tarea();
$accion = $_GET["accion"];

switch($accion){
    case 1:{
        $obj_tarea->id_tablero = intval(strClean($_REQUEST["id_tablero"]));
        $rs = $obj_tarea->list();
        echo json_encode($rs);
        break;
    }
    case 2:{
        $obj_tarea->id_tablero = intval(strClean($_REQUEST["id_tablero"]));
        $obj_tarea->titulo_tarea = strClean($_REQUEST["titulo_tarea"]);
        $obj_tarea->descripcion = strClean($_REQUEST["descripcion"]);
        $obj_tarea->estado_tarea = strClean($_REQUEST["estado_tarea"]);
        $obj_tarea->fecha_ini = strClean($_REQUEST["fecha_ini"]);
        $obj_tarea->fecha_fin = strClean($_REQUEST["fecha_fin"]);
        $rs = $obj_tarea->create();
        echo json_encode($rs);
        break;
    }
    case 3:{
        $obj_tarea->id_tarea= intval(strClean($_REQUEST["id_tarea"]));
        $obj_tarea->titulo_tarea = strClean($_REQUEST["titulo_tarea"]);
        $obj_tarea->descripcion = strClean($_REQUEST["descripcion"]);
        $obj_tarea->estado_tarea = strClean($_REQUEST["estado_tarea"]);
        $obj_tarea->fecha_ini = strClean($_REQUEST["fecha_ini"]);
        $obj_tarea->fecha_fin = strClean($_REQUEST["fecha_fin"]);
        $rs = $obj_tarea->update();
        echo json_encode($rs);
        break;
    }
}

?>
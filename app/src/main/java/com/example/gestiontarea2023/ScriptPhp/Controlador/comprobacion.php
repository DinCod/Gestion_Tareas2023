<?php 

header('Access-Control-Allow-Origin: *');
require_once("../Utilidades/StrCaracter.php");
include_once("../Modelo/comprobacion.php");

$obj_comprobacion = new comprobacion();
$accion = $_REQUEST["accion"];

switch($accion){

    case 1:{
        $obj_comprobacion->id_tarea = intval(strClean($_REQUEST["id_tarea"]));
        $obj_comprobacion->titulo_comprobacion = $_REQUEST["titulo_comprobacion"];
        $obj_comprobacion->estado_comprobante = $_REQUEST["estado_comprobante"];
        $rs = $obj_comprobacion->create();
        echo json_encode($rs);
        break;
    }
    case 2:{
        $obj_comprobacion->id_tarea = intval(strClean($_REQUEST["id_tarea"]));
        $rs = $obj_comprobacion->list();
        echo json_encode($rs);
        break;
    }
    case 3:{
        $obj_comprobacion->id_comprobacion = intval(strClean($_REQUEST["id_comprobacion"]));
        $rs = $obj_comprobacion->detele();
        echo json_encode($rs);
        break;
    }
    case 4:{
        $obj_comprobacion->id_comprobacion = intval(strClean($_REQUEST["id_comprobacion"]));
        $obj_comprobacion->estado_comprobante = strClean($_REQUEST["estado_comprobante"]);
        $rs = $obj_comprobacion->update();
        echo json_encode($rs);
        break;
    }
    
}

?>
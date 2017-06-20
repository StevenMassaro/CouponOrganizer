<?php
$id = $_POST["Id"];
$dir = 'uploads/';

$result = unlink($dir . $id);
echo $result;
?>
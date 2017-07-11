<?php
$dir = 'uploads/';
$files = scandir($dir);
$todayDate = date("Y-m-d");
$result = deleteExpiredCoupons($files, $dir, $todayDate);
echo $result;

function deleteExpiredCoupons($files, $dir, $todayDate){
	$numDeleted = 0;
	for($i = 2; $i < count($files); $i++){
		$str = explode("_", $files[$i]);
		$delete = isCouponExpired($todayDate, $str[1]);	
		
		if($delete){
			$tmp = unlink($dir . "$str[0]_$str[1]_$str[2]_$str[3]_$str[4]");
			if($tmp = TRUE){
				$numDeleted++;
			}
		}
	}
	return $numDeleted;
}

function isCouponExpired($todayDate, $fileDate){
	$_todayDate = explode("-", $todayDate);
	$_fileDate = explode("-", $fileDate);
	
	if($_todayDate[0] > $_fileDate[0]){
		return true;
	}
	else if($_todayDate[0] == $_fileDate[0] && $_todayDate[1] > $_fileDate[1]){
		return true;
	}
	else if($_todayDate[0] == $_fileDate[0] && $_todayDate[1] == $_fileDate[1] && $_todayDate[2] > $_fileDate[2]){
		return true;
	}
	else{
		return false;
	}
}
?>
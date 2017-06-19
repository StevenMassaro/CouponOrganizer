<?php
$target_dir = "uploads/";
$uploadOk = 1;

$merchant = $_POST["merchant"];
$expirationDate = $_POST["expirationDate"];
$deal = $_POST["deal"];
$notes = $_POST["notes"];

//$target_file = $target_dir . basename($_FILES["fileToUpload"]["name"]);
$fileExtension = pathinfo(basename($_FILES["fileToUpload"]["name"]),PATHINFO_EXTENSION);
$destinationFilename = $merchant . "_" . $expirationDate . "_" . $deal . "_" . $notes . "_." . $fileExtension;
$destinationFilename = validateFileName($destinationFilename);
$destinationFilePath = $target_dir . $destinationFilename;


// Check if file already exists
if (file_exists($destinationFilePath)) {
    echo "Sorry, " . $destinationFilePath . " already exists.";
    $uploadOk = 0;
}

// Check file size
if ($_FILES["fileToUpload"]["size"] > 5000000) {
    echo "Sorry, your file is too large.";
    $uploadOk = 0;
}

// Allow certain file formats
if($fileExtension != "jpg" && $fileExtension != "png" && $fileExtension != "jpeg"
&& $fileExtension != "gif" && $fileExtension != "pdf" ) {
    echo "Sorry, only JPG, JPEG, PNG, GIF and PDF files are allowed.";
    $uploadOk = 0;
}

// Check if $uploadOk is set to 0 by an error
if ($uploadOk == 0) {
    echo "Sorry, your file was not uploaded.";
// if everything is ok, try to upload file
} else {
    if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $destinationFilePath)) {
        echo "The file ". $destinationFilename . " has been uploaded.";
    } else {
        echo "Sorry, there was an error uploading your file.";
    }
}

function validateFileName($file){
	$file = mb_ereg_replace("([^\w\s\d\-_~,;\[\]\(\).])", '', $file);
	$file = mb_ereg_replace("([\.]{2,})", '', $file);
	return $file;
}
?>
<?php
ini_set('display_errors', 'On');
error_reporting(E_ALL);
error_reporting(E_ALL | E_STRICT);

include('_creds.php');

$target_dir = "uploads/";
$uploadOk = 1;

$merchant = $_POST["merchant"];
$expirationDate = $_POST["expirationDate"];
$deal = $_POST["deal"];
$notes = $_POST["notes"];

//$target_file = $target_dir . basename($_FILES["fileToUpload"]["name"]);
$fileExtension = pathinfo(basename($_FILES["fileToUpload"]["name"]),PATHINFO_EXTENSION);
$destinationFilename = $merchant . "_" . $expirationDate . "_" . $deal . "_" . $notes . "_." . $fileExtension;
$destinationFilename = rawurlencode($destinationFilename);
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
if ($uploadOk == 0)
{
    echo "Sorry, your file was not uploaded.";
// if everything is ok, try to upload file
}
else
{
    if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $destinationFilePath))
	{
        echo "The file ". $destinationFilename . " has been uploaded.";
		echo "<br><br>Creating event in Google Calendar...";
		$curl = curl_init();
		$_date = date('Y-m-d', strtotime($expirationDate . " +1 day"));

		curl_setopt_array($curl, array(
			CURLOPT_URL => "***REMOVED***",
			CURLOPT_RETURNTRANSFER => true,
			CURLOPT_ENCODING => "",
			CURLOPT_MAXREDIRS => 10,
			CURLOPT_TIMEOUT => 30,
			CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
			CURLOPT_CUSTOMREQUEST => "POST",
			CURLOPT_POSTFIELDS => "{\n    \"event_id\":\"" . $destinationFilename . "\",\n    \"summary\":\"" . $merchant . " coupon expires\",\n    \"description\":\"Deal: " . $deal . "\\nNotes: " . $notes . "\",\n    \"start\":\"" . $expirationDate . "\",\n    \"end\":\"" . $_date . "\",\n    \"tzid\":\"America/New_York\",\n    \"location\":{\n        \"description\":\"\"\n    }\n}",
			CURLOPT_HTTPHEADER => array(
			"authorization: Bearer " . $bearerToken . "",
			"cache-control: no-cache",
			"content-type: application/json",
			"postman-token: e8378bf1-a74e-71b7-efa4-b951674df3a5"
			),
		));

		$response = curl_exec($curl);
		$err = curl_error($curl);

		curl_close($curl);

		if ($err) {
			echo "<br><br>Possible event creation error!";
			echo "<br><br>cURL Error #:" . $err;
		}
		else {
			echo "<br><br>Event created.";
			echo $response;
		}
	}
}


echo "<br><br><a href=\"UploadCoupon.php\">Upload Coupon</a><br>";
echo "<a href=\"CouponListing.php\">Coupon Listing</a><br>";



include 'footer.php';

// function validateFileName($file){
	// $file = mb_ereg_replace("([^\w\s\d\-_~,;\[\]\(\).])", '', $file);
	// $file = mb_ereg_replace("([\.]{2,})", '', $file);
	// return $file;
// }
?>
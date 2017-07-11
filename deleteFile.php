<?php
//receive the filename from the coupon listing page
$id = $_POST["Id"];
$dir = 'uploads/';

include('_creds.php');

//delete the file
$result = unlink($dir . $id);
// echo $result;
if($result == 1){
	echo "File: " . $id . " successfully deleted.";
}
else{
	echo "File: " . $id . " not successfully deleted!";
}

//delete the event from Google calendar
echo "<br><br>Deleting event in Google Calendar...";

$curl = curl_init();

curl_setopt_array($curl, array(
	CURLOPT_URL => "https://api.cronofy.com/v1/calendars/" . $calendarId . "/events",
	CURLOPT_RETURNTRANSFER => true,
	CURLOPT_ENCODING => "",
	CURLOPT_MAXREDIRS => 10,
	CURLOPT_TIMEOUT => 30,
	CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
	CURLOPT_CUSTOMREQUEST => "DELETE",
	CURLOPT_POSTFIELDS => "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"event_id\"\r\n\r\n" . $id . "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--",
	CURLOPT_HTTPHEADER => array(
		"authorization: ***REMOVED***",
		"cache-control: no-cache",
		"content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW",
		"postman-token: 1a5a5b84-0412-13f7-ebd5-0165894b0d93"
	),
));

$response = curl_exec($curl);
$err = curl_error($curl);

curl_close($curl);

if ($err){
	echo "<br><br>Possible event deletion error!";
	echo "cURL Error #:" . $err;
}
else{
	echo "<br><br>Event deleted.";
	echo $response;
}

echo "<br><br><a href=\"UploadCoupon.php\">Upload Coupon</a><br>";
echo "<a href=\"CouponListing.php\">Coupon Listing</a><br>";

include 'footer.php';

?>
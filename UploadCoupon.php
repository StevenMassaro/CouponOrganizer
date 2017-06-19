<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Coupons Home</title>
  </head>
<body>
<h1>Coupons</h1>
<b>Home</b><br>

<form action="upload.php" method="post" enctype="multipart/form-data">
    Select coupon to upload:
    <input type="file" name="fileToUpload" id="fileToUpload"><br>
	Merchant: <input type="text" name="merchant" id="merchant" required><br>
	Expiration Date: <input type="date" name="expirationDate" id="expirationDate"><br>
	Deal: <input type="text" name="deal" id="deal" required><br>
	Notes: <input type="text" name="notes" id="notes"><br>
    <input type="submit" value="Upload coupon" name="submit">
</form>

<?php include 'footer.php'; ?>

</body>
</html>

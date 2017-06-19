<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Coupons Listing</title>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.5.2.js"></script>
	<style>
	table {
		border-collapse: collapse;
		text-align: left;
	}

	table, th, td {
		padding: 5px;
		border: 1px solid black;
	}
	</style>
	
  </head>
<body>
<h1>Coupons</h1>

<table id="myTable2">
    <thead id="headings">
        <tr>
            <th onclick="sortTable(0)" id="merchant">Merchant</th>
            <th onclick="sortTable(1)" id="expirationDate">Expiration Date</th>
            <th onclick="sortTable(2)" id="deal">Deal</th>
			<th onclick="sortTable(3)" id="notes">Notes</th>
        </tr>
    </thead>
    <tbody id="results">
        <!-- this will be auto-populated -->
		<?php
			ini_set('display_errors', 'On');
			error_reporting(E_ALL);
			error_reporting(E_ALL | E_STRICT);

			$dir = 'uploads/';
			$files = scandir($dir);

			showResults($files);

			function showResults($files){
				$str;
				$unencodedUrl;
				$encodedUrl;
				$html;
				
				for($i = 2; $i < count($files); $i++){
					echo "<tr>";
					$str = explode("_", $files[$i]);
					for($j = 0; $j < 4; $j++){
						echo "<td>";
						echo "$str[$j]";
						echo "</td>";
					}
					echo "<td>Delete</td>";
					echo "</tr>";
				}
			}
			?>
    </tbody>
</table>
<br><br>
<a href="UploadCoupon.php">Upload Coupon</a><br>

<?php include 'footer.php'; ?>

</body>
</html>

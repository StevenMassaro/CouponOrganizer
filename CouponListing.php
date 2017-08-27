<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Coupons Listing</title>
	<style>
	table {
		border-collapse: collapse;
		text-align: left;
	}

	table, th, td {
		padding: 5px;
		border: 1px solid black;
	}
	
	html, body, iframe {
		height: 100%;
	}
	iframe {
		height: 98%;
	}
	</style>
	
  </head>
<body>

<?php 
// ini_set('display_errors', 'On');
// error_reporting(E_ALL);
// error_reporting(E_ALL | E_STRICT);
?>

<h1>Coupons</h1>
<?php include 'garbageCollector.php'; ?> expired coupons have been deleted.<br><br>

		<?php
			$dir = 'uploads/';
			$files = scandir($dir);

			showResults($files);

			function showResults($files){
				if(count($files) > 2){
					echo "
					<iframe style=\"position: relative; width: 50%; border: none; float: right;\" name=\"side_coupon\" src=\"\" frameBorder=\"0\"></iframe>
					<table id=\"myTable2\">
							<thead id=\"headings\">
								<tr>
									<th onclick=\"sortTable(0)\" id=\"merchant\">Merchant</th>
									<th onclick=\"sortTable(1)\" id=\"expirationDate\">Expiration Date</th>
									<th onclick=\"sortTable(2)\" id=\"deal\">Deal</th>
									<th onclick=\"sortTable(3)\" id=\"notes\">Notes</th>
								</tr>
							</thead>
							<tbody id=\"results\">";
							
					for($i = 2; $i < count($files); $i++){
						echo "<tr>";
						$str = explode("_", $files[$i]);
						for($j = 0; $j < count($str)-1; $j++){
							if($j == 0){
								$unencodedUrl = $str[0] . "_" . $str[1] . "_" . $str[2] . "_" . $str[3] . "_" . $str[4];
								$encodedUrl = rawurlencode($unencodedUrl);
								$encodedUrl = "uploads/" . $encodedUrl;
								echo "<td>";
								echo "<a href=\"" . $encodedUrl . "\" target=\"side_coupon\">" . rawurldecode($str[0]);
								echo "</td>";
							}
							else{
								echo "<td>";
								echo rawurldecode($str[$j]);
								echo "</td>";
							}
							
						}
						$fileName = $str[0] . "_" . $str[1] . "_" . $str[2] . "_" . $str[3] . "_" . $str[4];
						$fileName = rawurlencode($fileName);
						echo "<td>
							<form method =\"POST\" action=\"deleteFile.php\">
								<input type=\"submit\" value=\"Delete\" />
								<input type=\"hidden\" name=\"Id\" value=\"" . $fileName . "\">
							</form>
						</td>";
						echo "</tr>";
					}
					
					echo "</tbody></table>";
				}
				else{
					echo "No coupons to display.";
				}
			}
			?>

<br><br>
<a href="UploadCoupon.php">Upload Coupon</a><br>

<?php include 'footer.php'; ?>

<script>
function sortTable(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("myTable2");
    switching = true;
    //Set the sorting direction to ascending:
    dir = "asc";
    /*Make a loop that will continue until
    no switching has been done:*/
    while (switching) {
        //start by saying: no switching is done:
        switching = false;
        rows = table.getElementsByTagName("TR");
        /*Loop through all table rows (except the
        first, which contains table headers):*/
        for (i = 1; i < (rows.length - 1) ; i++) {
            //start by saying there should be no switching:
            shouldSwitch = false;
            /*Get the two elements you want to compare,
            one from current row and one from the next:*/
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            /*check if the two rows should switch place,
            based on the direction, asc or desc:*/
            if (dir == "asc") {
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            } else if (dir == "desc") {
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            /*If a switch has been marked, make the switch
            and mark that a switch has been done:*/
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            //Each time a switch is done, increase this count by 1:
            switchcount++;
        } else {
            /*If no switching has been done AND the direction is "asc",
            set the direction to "desc" and run the while loop again.*/
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}

function clickExpirationDate(){
	document.getElementById("expirationDate").click();
}

</script>
</body>
</html>

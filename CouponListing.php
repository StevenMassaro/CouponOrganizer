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
<body onload="clickExpirationDate()">
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
					for($j = 0; $j < count($str)-1; $j++){
						if($j == 0){
							$unencodedUrl = "uploads/" . $str[0] . "_" . $str[1] . "_" . $str[2] . "_" . $str[3] . "_" . $str[4];
							$encodedUrl = htmlentities($unencodedUrl);
							echo "<td>";
							echo "<a href=\"" . $encodedUrl . "\">" . $str[0];
							echo "</td>";
						}
						else{
							echo "<td>";
							echo "$str[$j]";
							echo "</td>";
						}
						
					}
					echo "<td>
						<form method =\"POST\" action=\"deleteFile.php\">
							<input type=\"submit\" value=\"Delete\" />
							<input type=\"hidden\" name=\"Id\" value=\"$str[0]_$str[1]_$str[2]_$str[3]_$str[4]\">
						</form>
					</td>";
					echo "</tr>";
				}
			}
			?>
    </tbody>
</table>
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

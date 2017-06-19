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
    </tbody>
</table>
<br><br>
<a href="UploadCoupon.php">Upload Coupon</a><br>


<?php include 'footer.php'; ?>


<script>
/*var arr = [
    {
        merchant: 'Walmart',
        expirationDate: '2017-06-13',
        deal: '10% off',
		notes: ''
    },
    {
        merchant: 'Bestbuy',
        expirationDate: '2017-06-14',
        deal: 'free tv',
		notes: 'if you buy another tv'
    },
    {
        merchant: 'Circuitcity',
        expirationDate: '2016-07-08',
        deal: '$5 off',
		notes: ''
    }
];*/

<?php
$dir = 'uploads/';
$files = scandir($dir);
/*$array[];
$string;
for($i = 0; $i < count($files); $i++){
	$string = explode("_", $files[$i]);
	$array[] = array("merchant" => $string[0],
					"expirationDate" => $string[1],
					"deal" => $string[2],
					"notes" => $string[3]);
}*/	
$js_array = json_encode($files);
echo "var arr = ". $js_array . ";\n";
/*$js_array = json_encode($array);
echo "var arr = " . $js_array . ";\n";*/
?>
/*
$(function() {
    $('#headings th').click(function() {
        var id = $(this).attr('id');
        var asc = (!$(this).attr('asc')); // switch the order, true if not set
        
        // set asc="asc" when sorted in ascending order
        $('#headings th').each(function() {
            $(this).removeAttr('asc');
        });
        if (asc) $(this).attr('asc', 'asc');
        
        sortResults(id, asc);
    });
        
    showResults();
});

function sortResults(prop, asc) {
    arr = arr.sort(function(a, b) {
        if (asc) return (a[prop] > b[prop]);
        else return (b[prop] > a[prop]);
    });
    showResults();
}
*/
showResults();
function showResults () {
	var str;
	var unencodedUrl;
	var encodedUrl;
    var html = '';
	/*
    for (var e in arr) {
		str = e.split('_');
        html += '<tr>'
			+'<td>'+str[e].merchant+'</td>'
            //+'<td>'+arr[e].merchant+'</td>'
            // +'<td>'+arr[e].expirationDate+'</td>'
            // +'<td>'+arr[e].deal+'</td>'
			// +'<td>'+arr[e].notes+'</td>'
        +'</tr>';
    }*/
	
	for(var i = 2; i < arr.length; i++){
		html += '<tr>';
		str = arr[i].split('_');
		unencodedUrl = "uploads/"+str[0]+"_"+str[1]+"_"+str[2]+"_"+str[3]+"_"+str[4];
		encodedUrl = encodeURI(unencodedUrl);

		for(var j = 0; j < 4; j++){
			if(j==0){
				html += '<td><a href='+encodedUrl+'>'+str[j]+'</td>';
			}
			else{
				html += '<td>'+str[j]+'</td>';
			}
			
			
			
		}
		+'</tr>';
	}
    $('#results').html(html);
}
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
    for (i = 1; i < (rows.length - 1); i++) {
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
          shouldSwitch= true;
          break;
        }
      } else if (dir == "desc") {
        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
          //if so, mark as a switch and break the loop:
          shouldSwitch= true;
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
      switchcount ++; 
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
</script>

</body>
</html>

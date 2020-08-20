<?php 

	include_once "koneksi.php";
	// $server		= "localhost"; //sesuaikan dengan nama server
	// $user		= "root"; //sesuaikan username
	// $password	= ""; //sesuaikan password
	// $database	= "maps"; //sesuaikan target databese
	
	// $con = mysqli_connect($server, $user, $password, $database);
	// if (mysqli_connect_errno()) {
	// 	echo "Gagal terhubung MySQL: " . mysqli_connect_error();
	// }	

	$query = mysqli_query($con, "SELECT * FROM reseller ORDER BY nama_Reseller ASC");
	
	$json = '{"reseller": [';

	// bikin looping dech array yang di fetch
	while ($row = mysqli_fetch_array($query)){

	//tanda kutip dua (") tidak diijinkan oleh string json, maka akan kita replace dengan karakter `
	//strip_tag berfungsi untuk menghilangkan tag-tag html pada string 
		$char ='"';

		$json .= 
		'{
			"id_Reseller":"'.str_replace($char,'`',strip_tags($row['id_Reseller'])).'", 
			"nama_Reseller":"'.str_replace($char,'`',strip_tags($row['nama_Reseller'])).'",
			"alamat_Reseller":"'.str_replace($char,'`',strip_tags($row['alamat_Reseller'])).'",
			"telp_Reseller":"'.str_replace($char,'`',strip_tags($row['telp_Reseller'])).'",
			"website_Reseller":"'.str_replace($char,'`',strip_tags($row['website_Reseller'])).'",
			"latitude_Reseller":"'.str_replace($char,'`',strip_tags($row['latitude_Reseller'])).'",
			"longitude_Reseller":"'.str_replace($char,'`',strip_tags($row['longitude_Reseller'])).'"
		},';
	}

	// buat menghilangkan koma diakhir array
	$json = substr($json,0,strlen($json)-1);

	$json .= ']}';

	// print json
	echo $json;
	
	mysqli_close($con);
<?php 
	include_once "koneksi.php";
	
	$lat = $_GET["latitude_Reseller"];
	$lng = $_GET["longitude_Reseller"];	
	
	/* 10.0.2.2 adalah IP Address localhost EMULATOR ANDROID STUDIO,
        Ganti IP Address tersebut dengan IP Laptop Apabila di RUN di HP. HP dan Laptop harus 1 jaringan */
	//$url = "http://10.0.2.2/android/haversine/images/";
	
	// perhitungan haversine formula pada sintak SQL
	$query = mysqli_query($con, "SELECT id_Reseller, nama_Reseller,(6371 * ACOS(SIN(RADIANS(latitude_Reseller)) * SIN(RADIANS($lat)) + COS(RADIANS(longitude_Reseller - $lng)) * COS(RADIANS(latitude_Reseller)) * COS(RADIANS($lat)))) AS jarak FROM reseller HAVING jarak < 6371 ORDER BY jarak ASC");
	
	$json = array();
	
	$no = 0;
	
	while($row = mysqli_fetch_assoc($query)){
		$json[$no]['id_Reseller'] = $row['id_Reseller'];
		$json[$no]['nama_Reseller'] = $row['nama_Reseller'];
		//$json[$no]['gambar'] = $url.$row['gambar'];
		$json[$no]['jarak'] = $row['jarak'];
		
		$no++;
	}
	
	echo json_encode($json);
	
	mysqli_close($con);
	
?>
--
-- Database: `maps`
--

-- --------------------------------------------------------

--
-- Table structure for table `kota`
--

CREATE TABLE `kota` (
  `id_Kota` int(15) NOT NULL,
  `id_Provinsi` int(15) NOT NULL,
  `nama_Kota` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kota`
--

INSERT INTO `kota` (`id_Kota`, `id_Provinsi`, `nama_Kota`) VALUES
(601, 6, 'Bangkalan'),
(602, 6, 'Banyuwangi'),
(603, 6, 'Blitar'),
(604, 6, 'Bojonegoro'),
(605, 6, 'Bondowoso'),
(606, 6, 'Gresik'),
(607, 6, 'Jember'),
(608, 6, 'Jombang'),
(609, 6, 'Kediri'),
(610, 6, 'Lamongan'),
(611, 6, 'Lumajang'),
(612, 6, 'Madiun'),
(613, 6, 'Magetan'),
(614, 6, 'Malang'),
(615, 6, 'Mojokerto'),
(616, 6, 'Nganjuk'),
(617, 6, 'Ngawi'),
(618, 6, 'Pacitan'),
(619, 6, 'Pamekasan'),
(620, 6, 'Pasuruan'),
(621, 6, 'Ponorogo'),
(622, 6, 'Probolinggo'),
(623, 6, 'Sampang'),
(624, 6, 'Sidoarjo'),
(625, 6, 'Situbondo'),
(626, 6, 'Sumenep'),
(627, 6, 'Trenggalek'),
(628, 6, 'Tuban'),
(629, 6, 'Tulungagung'),
(630, 6, 'Kota Batu'),
(631, 6, 'Kota Blitar'),
(632, 6, 'Kota Kediri'),
(633, 6, 'Kota Madiun'),
(634, 6, 'Kota Malang'),
(635, 6, 'Kota Mojokerto'),
(636, 6, 'Kota Pasuruan'),
(637, 6, 'Kota Probolinggo'),
(638, 6, 'Kota Surabaya');

-- --------------------------------------------------------

--
-- Table structure for table `provinsi`
--

CREATE TABLE `provinsi` (
  `id_Provinsi` int(15) NOT NULL,
  `nama_Provinsi` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `provinsi`
--

INSERT INTO `provinsi` (`id_Provinsi`, `nama_Provinsi`) VALUES
(1, ' Jakarta'),
(2, 'Banten'),
(3, 'Jawa Barat'),
(4, 'Jawa Tengah'),
(5, 'DI Yogyakarta'),
(6, 'Jawa Timur');

-- --------------------------------------------------------

--
-- Table structure for table `reseller`
--

CREATE TABLE `reseller` (
  `id_Reseller` int(15) NOT NULL,
  `id_Kota` int(15) NOT NULL,
  `nama_Reseller` varchar(30) NOT NULL,
  `alamat_Reseller` text NOT NULL,
  `telp_Reseller` varchar(20) NOT NULL,
  `website_Reseller` varchar(25) NOT NULL,
  `latitude_Reseller` varchar(20) NOT NULL,
  `longitude_Reseller` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reseller`
--

INSERT INTO `reseller` (`id_Reseller`, `id_Kota`, `nama_Reseller`, `alamat_Reseller`, `telp_Reseller`, `website_Reseller`, `latitude_Reseller`, `longitude_Reseller`) VALUES
(60101, 601, 'Bangkalan Plaza 3,8', 'Jl. Halim Perdana Kusuma, Mlajah, Kec. Bangkalan, Kabupaten Bangkalan, Jawa Timur 69116', '0', 'reseller.com', '-7.050566', '112.743458'),
(60102, 601, 'Pasar Tradisional Bangkalan', 'Mlajah, Kec. Bangkalan, Kabupaten Bangkalan, Jawa Timur 69116\r\n', '0', 'reseller.com', '-7.050326', '112.742225'),
(60103, 601, 'Macaroni MANG ADE Bangkalan', 'Jl. Raya Gebang, Gebang, Kec. Bangkalan, Kabupaten Bangkalan, Jawa Timur 69115', '0', 'reseller.com', '-6.985939', '112.791167'),
(60201, 602, 'Sun East Mall', 'Jalan Diponegoro No. 11, Genteng, Genteng Kulon, Banyuwangi, Kabupaten Banyuwangi, Jawa Timur 68465\r\n', '0', 'reseller.com', '-8.364872', '114.147898'),
(60202, 602, 'Roxy Mall Banyuwangi', 'Penganjuran, Kec. Banyuwangi, Kabupaten Banyuwangi, Jawa Timur 68416', '0', 'reseller.com', '-8.218169', '114.369877'),
(60203, 602, 'Garage Shop banyuwangi', 'Gambiran, Kabupaten Banyuwangi, Jawa Timur 68486', '0', 'reseller.com', '-8.228881', '114.360303'),
(60301, 603, 'Blitar Town Square', 'Jalan Merdeka No.47, Kepanjen Lor, Kepanjenkidul, Kepanjen Lor, Kepanjenkidul, Kota Blitar, Jawa Timur 66117', '0', 'reseller.com', '-8.098902', '112.166795'),
(60302, 603, 'MAMMAH PRODUCTION', 'Jl. Ternate No.38, Klampok, Sananwetan, Kota Blitar, Jawa Timur 66136\r\n', '0', 'reseller.com', '-8.130471', '112.159600'),
(60303, 603, 'Toko Intan Jaya Sentul Blitar', 'Jl. Ir. Soekarno No.413, Sentul, Kepanjenkidul, Kota Blitar, Jawa Timur 66113\r\n', '0', 'reseller.com', '-8.067410', '112.189081'),
(60401, 604, 'KDS Mall Bojonegoro', 'Jl. Veteran No.90, Sukorejo, Kec. Bojonegoro, Kabupaten Bojonegoro, Jawa Timur 62115', '0', 'reseller.com', '-7.162782', '111.896958'),
(60402, 604, 'ISMA SWALAYAN DAN GROSIR PUSAT', 'Sumberrejo, Sumberejo, Kabupaten Bojonegoro, Jawa Timur 62191', '0', 'reseller.com', '-7.176597', '112.000440'),
(60403, 604, 'Grosir Bojonegoro', 'Rt:15/Rw:02,, Jl. Raya Desa Wedi, Wedi, Kapas, Kabupaten Bojonegoro, Jawa Timur 62181\r\n', '0', 'reseller.com', '-7.193304', '111.893431');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kota`
--
ALTER TABLE `kota`
  ADD PRIMARY KEY (`id_Kota`),
  ADD KEY `id_Provinsi` (`id_Provinsi`);

--
-- Indexes for table `provinsi`
--
ALTER TABLE `provinsi`
  ADD PRIMARY KEY (`id_Provinsi`);

--
-- Indexes for table `reseller`
--
ALTER TABLE `reseller`
  ADD PRIMARY KEY (`id_Reseller`),
  ADD KEY `id_Kota` (`id_Kota`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `reseller`
--
ALTER TABLE `reseller`
  MODIFY `id_Reseller` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60404;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `kota`
--
ALTER TABLE `kota`
  ADD CONSTRAINT `kota_ibfk_1` FOREIGN KEY (`id_Provinsi`) REFERENCES `provinsi` (`id_Provinsi`);

--
-- Constraints for table `reseller`
--
ALTER TABLE `reseller`
  ADD CONSTRAINT `reseller_ibfk_1` FOREIGN KEY (`id_Kota`) REFERENCES `kota` (`id_Kota`);


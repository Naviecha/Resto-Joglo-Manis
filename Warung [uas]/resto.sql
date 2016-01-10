-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 10, 2016 at 04:13 AM
-- Server version: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `resto`
--

-- --------------------------------------------------------

--
-- Table structure for table `gaji`
--

CREATE TABLE IF NOT EXISTS `gaji` (
  `id` int(4) NOT NULL,
  `nominal` double(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gaji`
--

INSERT INTO `gaji` (`id`, `nominal`) VALUES
(1000, 100),
(1111, 13),
(2222, 13),
(3333, 13),
(5555, 100);

-- --------------------------------------------------------

--
-- Table structure for table `manager`
--

CREATE TABLE IF NOT EXISTS `manager` (
  `id` int(4) NOT NULL,
  `password` text NOT NULL,
  `nama_depan` varchar(10) NOT NULL,
  `nama_belakang` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `manager`
--

INSERT INTO `manager` (`id`, `password`, `nama_depan`, `nama_belakang`) VALUES
(1000, 'admin', 'Bu', 'Rochman'),
(5555, 'admin', 'Ivan', 'Pandu');

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE IF NOT EXISTS `menu` (
  `id` int(4) NOT NULL,
  `nama` text NOT NULL,
  `harga` double NOT NULL,
  `tipe` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`id`, `nama`, `harga`, `tipe`) VALUES
(100, 'Nasi Putih', 1, 1),
(101, 'Nasi Goreng', 5, 1),
(102, 'Nasi Kuning', 3.5, 1),
(200, 'Marimas', 1.5, 2),
(201, 'Es Teh', 2.5, 2),
(202, 'Kopi', 2.5, 2),
(203, 'Nutri Sari', 2.5, 2),
(204, 'Pop Ice', 2.5, 2),
(300, 'Ayam', 7, 3),
(301, 'Telur', 5, 3),
(302, 'Lele', 7, 3),
(303, 'Bandeng', 7, 3),
(304, 'Perkedel', 1, 3),
(400, 'Sayur Pecel', 2, 4),
(401, 'Sayur Sop', 2, 4),
(402, 'Sayur Lodeh', 2, 4);

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE IF NOT EXISTS `staff` (
  `id` int(4) NOT NULL,
  `password` text NOT NULL,
  `nama_depan` varchar(10) NOT NULL,
  `nama_belakang` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`id`, `password`, `nama_depan`, `nama_belakang`) VALUES
(1111, 'intan', 'Mbak', 'Intan'),
(2222, 'diana', 'Mbak', 'Diana'),
(3333, 'supri', 'Mas', 'Supri');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `gaji`
--
ALTER TABLE `gaji`
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `manager`
--
ALTER TABLE `manager`
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

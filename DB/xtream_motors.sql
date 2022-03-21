-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 21, 2022 at 08:39 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `xtream_motors`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `no` int(11) NOT NULL,
  `userid` varchar(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `security_question` varchar(100) NOT NULL,
  `security_answer` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`no`, `userid`, `username`, `password`, `email`, `first_name`, `last_name`, `security_question`, `security_answer`) VALUES
(1, 'u00001', 'dinusha', 'dinusha', 'dinushawh@gmail.com', 'Dinusha', 'Weerakoon', '', ''),
(2, 'u00002', 'rashini', '123', 'rashini@gmail.com', 'Rashini', 'Kaveesha', '', ''),
(8, 'u00003', 'dinushawh', '1', 'dinuushax@outlook.ocm', 'Dinusha', 'Weerakoon', 'In what city were you born?', '1'),
(9, 'u00004', 'fdaf', 'hasi', 'fawfa', 'fawf', 'afawffafawf', 'In what city were you born?', 'cc');

-- --------------------------------------------------------

--
-- Table structure for table `vehical`
--

CREATE TABLE `vehical` (
  `no` int(11) NOT NULL,
  `vehical_id` varchar(20) NOT NULL,
  `brand` varchar(50) NOT NULL,
  `vehicle` varchar(50) NOT NULL,
  `engine` varchar(50) NOT NULL,
  `engine_code` varchar(50) NOT NULL,
  `torque` varchar(50) NOT NULL,
  `power` varchar(50) NOT NULL,
  `average_fuel_consumption` varchar(50) NOT NULL,
  `co2` varchar(50) NOT NULL,
  `weight` varchar(50) NOT NULL,
  `block_material_cylinder_head` varchar(50) NOT NULL,
  `compression_ratio` varchar(50) NOT NULL,
  `gearbox_type` varchar(50) NOT NULL,
  `tyre` varchar(50) NOT NULL,
  `traction_type` varchar(50) NOT NULL,
  `year_manufacture` varchar(50) NOT NULL,
  `km_travelled` varchar(50) NOT NULL,
  `fuel` varchar(50) NOT NULL,
  `owner` varchar(50) NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vehical`
--

INSERT INTO `vehical` (`no`, `vehical_id`, `brand`, `vehicle`, `engine`, `engine_code`, `torque`, `power`, `average_fuel_consumption`, `co2`, `weight`, `block_material_cylinder_head`, `compression_ratio`, `gearbox_type`, `tyre`, `traction_type`, `year_manufacture`, `km_travelled`, `fuel`, `owner`, `price`) VALUES
(2, 'v000002', 'Renault', 'Twingo', '1.2L 60 hp', 'D7F', '93/2500', '58-43/5250', '5.6', '130', '1000', 'cast iron /aluminium', '9.6', 'MT', '165/65 R14', '2wd', '2017', '0', 'Petrol', 'Dinusha Weerakoon', 7800000),
(5, 'v000003', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'q', 'Petrol', 'q', 10);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userid`,`email`),
  ADD UNIQUE KEY `no` (`no`);

--
-- Indexes for table `vehical`
--
ALTER TABLE `vehical`
  ADD PRIMARY KEY (`vehical_id`),
  ADD UNIQUE KEY `no` (`no`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `vehical`
--
ALTER TABLE `vehical`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

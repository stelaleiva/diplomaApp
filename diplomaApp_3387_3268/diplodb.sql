-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3320
-- Generation Time: May 20, 2023 at 06:07 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `diplodb`
--

-- --------------------------------------------------------

--
-- Table structure for table `applications`
--

CREATE TABLE `applications` (
  `app_id` int(11) NOT NULL,
  `diploma` varchar(50) NOT NULL,
  `diploma_id` int(11) NOT NULL,
  `selected` varchar(255) NOT NULL,
  `student` varchar(50) NOT NULL,
  `student_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `diploma`
--

CREATE TABLE `diploma` (
  `diploma_id` int(11) NOT NULL,
  `available` varchar(10) NOT NULL,
  `objectives` varchar(255) NOT NULL,
  `title` varchar(50) NOT NULL,
  `user` varchar(50) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `supervised`
--

CREATE TABLE `supervised` (
  `super_id` int(11) NOT NULL,
  `diploma` varchar(50) NOT NULL,
  `diploma_id` int(11) NOT NULL,
  `implem_grade` decimal(4,2) DEFAULT NULL,
  `overall_grade` decimal(4,2) DEFAULT NULL,
  `present_grade` decimal(4,2) DEFAULT NULL,
  `prof` varchar(50) NOT NULL,
  `prof_id` int(11) NOT NULL,
  `report_grade` decimal(4,2) DEFAULT NULL,
  `student` varchar(50) NOT NULL,
  `student_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `avg_grade` decimal(4,2) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `full_name` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `remain_courses` int(11) DEFAULT NULL,
  `role` varchar(45) NOT NULL,
  `speciality` text DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `years` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `applications`
--
ALTER TABLE `applications`
  ADD PRIMARY KEY (`app_id`),
  ADD KEY `key5` (`diploma`),
  ADD KEY `key6` (`student`);

--
-- Indexes for table `diploma`
--
ALTER TABLE `diploma`
  ADD PRIMARY KEY (`diploma_id`),
  ADD UNIQUE KEY `title` (`title`),
  ADD KEY `key1` (`user`);

--
-- Indexes for table `supervised`
--
ALTER TABLE `supervised`
  ADD PRIMARY KEY (`super_id`),
  ADD KEY `key2` (`diploma`),
  ADD KEY `key3` (`prof`),
  ADD KEY `key4` (`student`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `full_name` (`full_name`),
  ADD UNIQUE KEY `UK_k8d0f2n7n88w1a16yhua64onx` (`user_name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `applications`
--
ALTER TABLE `applications`
  MODIFY `app_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `diploma`
--
ALTER TABLE `diploma`
  MODIFY `diploma_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `supervised`
--
ALTER TABLE `supervised`
  MODIFY `super_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `applications`
--
ALTER TABLE `applications`
  ADD CONSTRAINT `key5` FOREIGN KEY (`diploma`) REFERENCES `diploma` (`title`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `key6` FOREIGN KEY (`student`) REFERENCES `users` (`full_name`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `diploma`
--
ALTER TABLE `diploma`
  ADD CONSTRAINT `key1` FOREIGN KEY (`user`) REFERENCES `users` (`full_name`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `supervised`
--
ALTER TABLE `supervised`
  ADD CONSTRAINT `key2` FOREIGN KEY (`diploma`) REFERENCES `diploma` (`title`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `key3` FOREIGN KEY (`prof`) REFERENCES `users` (`full_name`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `key4` FOREIGN KEY (`student`) REFERENCES `users` (`full_name`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

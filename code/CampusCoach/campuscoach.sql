-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2015-09-15 04:44:20
-- 服务器版本： 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `campuscoach`
--

-- --------------------------------------------------------

--
-- 表的结构 `coach`
--

CREATE TABLE IF NOT EXISTS `coach` (
  `coachID` int(11) NOT NULL,
  `phoneNumber` varchar(255) NOT NULL,
  `learnerID` int(11) NOT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `sportsName` varchar(255) DEFAULT NULL,
  `stateFlag` int(11) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `introduction` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `coach`
--

INSERT INTO `coach` (`coachID`, `phoneNumber`, `learnerID`, `realname`, `sportsName`, `stateFlag`, `avatar`, `introduction`) VALUES
(16, '1111', 15, '儿子', '打点', 0, NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `course`
--

CREATE TABLE IF NOT EXISTS `course` (
  `coachAvatar` varchar(255) DEFAULT NULL,
  `courseID` int(11) NOT NULL,
  `coachName` varchar(255) NOT NULL,
  `sportsName` varchar(255) NOT NULL,
  `time` varchar(255) NOT NULL,
  `place` varchar(255) NOT NULL,
  `maxNum` int(11) NOT NULL,
  `price` varchar(255) NOT NULL,
  `enrollNum` int(11) NOT NULL,
  `phoneNumber` varchar(255) NOT NULL,
  `coachID` int(11) NOT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  `stateFlag` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `course`
--

INSERT INTO `course` (`coachAvatar`, `courseID`, `coachName`, `sportsName`, `time`, `place`, `maxNum`, `price`, `enrollNum`, `phoneNumber`, `coachID`, `introduction`, `stateFlag`) VALUES
(NULL, 1, '儿子', '羽毛球', '2:30-4:50', '南体', 2, '10/小时', 0, '13211232321', 16, '1', 1),
(NULL, 2, '儿子', '羽毛球', '2:30-4:50', '南体', 2, '10/小时', 0, '13211232321', 16, '1', 1),
(NULL, 3, '儿子', '羽毛球', '2:30-4:50', '南体', 2, '10/小时', 0, '13211232321', 16, '23', 1);

-- --------------------------------------------------------

--
-- 表的结构 `coursesignup`
--

CREATE TABLE IF NOT EXISTS `coursesignup` (
  `courseID` int(11) NOT NULL,
  `learnerID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `coursesignup`
--

INSERT INTO `coursesignup` (`courseID`, `learnerID`) VALUES
(1, 15),
(8, 21);

-- --------------------------------------------------------

--
-- 表的结构 `learner`
--

CREATE TABLE IF NOT EXISTS `learner` (
  `learnerID` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `isCoach` int(11) NOT NULL DEFAULT '0',
  `avatar` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `realName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `learner`
--

INSERT INTO `learner` (`learnerID`, `username`, `password`, `isCoach`, `avatar`, `phoneNumber`, `realName`) VALUES
(14, 'asdf', '123456', 0, NULL, NULL, NULL),
(15, 'yanjiasen4', 'asdffdsa', 1, 'upload/15/yanjiasen4-2015-09-14-20-23-42.png', NULL, NULL),
(16, '111', '232', 0, NULL, NULL, NULL),
(17, 'dd.h', '123456', 0, NULL, NULL, NULL),
(18, 'a', 'a', 0, NULL, NULL, NULL),
(19, 'deephjs', '123', 0, NULL, NULL, NULL),
(20, '11', '11', 0, NULL, NULL, NULL),
(21, 's', 's', 0, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `reservation`
--

CREATE TABLE IF NOT EXISTS `reservation` (
  `reservationID` int(11) NOT NULL,
  `learnerPhone` varchar(255) NOT NULL,
  `sportsName` varchar(255) NOT NULL,
  `time` varchar(255) NOT NULL,
  `place` varchar(255) NOT NULL,
  `introduction` varchar(255) NOT NULL,
  `stateFlag` int(11) NOT NULL,
  `maxNum` int(11) NOT NULL,
  `price` varchar(255) NOT NULL,
  `learnerID` varchar(255) NOT NULL,
  `reservationName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `reservation`
--

INSERT INTO `reservation` (`reservationID`, `learnerPhone`, `sportsName`, `time`, `place`, `introduction`, `stateFlag`, `maxNum`, `price`, `learnerID`, `reservationName`) VALUES
(1, '1', '1', '1', '1', '1', 0, 1, '1', '15', '1');

-- --------------------------------------------------------

--
-- 表的结构 `reservationreceive`
--

CREATE TABLE IF NOT EXISTS `reservationreceive` (
  `coachID` int(11) NOT NULL,
  `coachName` varchar(255) NOT NULL,
  `reservationID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `test`
--

CREATE TABLE IF NOT EXISTS `test` (
  `a` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `coach`
--
ALTER TABLE `coach`
  ADD PRIMARY KEY (`coachID`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`courseID`);

--
-- Indexes for table `coursesignup`
--
ALTER TABLE `coursesignup`
  ADD PRIMARY KEY (`courseID`,`learnerID`);

--
-- Indexes for table `learner`
--
ALTER TABLE `learner`
  ADD PRIMARY KEY (`learnerID`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`reservationID`);

--
-- Indexes for table `reservationreceive`
--
ALTER TABLE `reservationreceive`
  ADD PRIMARY KEY (`coachID`,`reservationID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `coach`
--
ALTER TABLE `coach`
  MODIFY `coachID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `courseID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `learner`
--
ALTER TABLE `learner`
  MODIFY `learnerID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `reservationID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

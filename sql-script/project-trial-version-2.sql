DROP DATABASE  IF EXISTS `project_trial_version_2`;

CREATE DATABASE  IF NOT EXISTS `project_trial_version_2`;
USE `project_trial_version_2`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: http://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--

INSERT INTO `user` (username,password,first_name,last_name,email)
VALUES 
('john','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','John','Doe','john@luv2code.com'),
('mary','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Mary','Public','mary@luv2code.com');


--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `role` (name)
VALUES 
('ROLE_STUDENT'),('ROLE_MANAGER');

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`),

  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 1),
(2, 2);


----------------- Subject Directory -------------
--
-- Table structure for table `java`
--

DROP TABLE IF EXISTS `java`;

CREATE TABLE `java` (
  `id` int(11) NOT NULL AUTO_INCREMENT ,
  `question` varchar(255) NOT NULL,
  `optA` varchar(255) NOT NULL,
  `optB` varchar(255) NOT NULL,
  `optC` varchar(255) NOT NULL,
  `optD` varchar(255) NOT NULL,
  `correct_ans` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `java`
--

INSERT INTO `java` (question,optA,optB,optC,optD,correct_ans) 
VALUES 
	('Q1','A','B','C','D','D'),
	('Q2','A','B','C','D','C'),
	('Q3','A','B','C','D','B'),
	('Q4','A','B','C','D','A');

---------------------- User Answers -------------------------

create table `userAnswer`(
    `id` int NOT NULL AUTO_INCREMENT ,
	`userId` int default null,
	`question_id` int default null,
    `answer` varchar(50) default null,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
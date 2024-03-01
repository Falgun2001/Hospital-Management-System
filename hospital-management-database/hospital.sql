-- Table structure for table `medicines`
DROP DATABASE IF EXISTS hospital_management;
CREATE DATABASE hospital_management;
USE hospital_management;

DROP TABLE IF EXISTS `medicines`;

CREATE TABLE `medicines` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

-- Dumping data for table `medicines`

LOCK TABLES `medicines` WRITE;
INSERT INTO `medicines` VALUES (11,'Paclitaxel',14),(13,'Palifermin',12),(14,'Palonosetron hydrochloride',11),(15,'Daraprim',12.2);
UNLOCK TABLES;




-- Table structure for table `wards`

DROP TABLE IF EXISTS `wards`;

CREATE TABLE `wards` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `charges` double DEFAULT NULL,
  `availability` double DEFAULT NULL,
  `max_capacity` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

-- Dumping data for table `wards`

LOCK TABLES `wards` WRITE;
INSERT INTO `wards` VALUES (19,'A',1200,24,40),(38,'B',500,40,40),(39,'C',870,45,45);
UNLOCK TABLES;







-- Table structure for table `users`

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(1000) DEFAULT NULL,
  `cell_no` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `security_question` varchar(100) DEFAULT NULL,
  `security_answer` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;


-- Dumping data for table `users`

LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES (1,'falgun','padme','falgun@gmail.com','1234','+918793031484','admin','my pet name','donu'),(2,'aarti','ghagre','aarti@gmail.com','1234','+918793031484','reception','my pet name','ranu'),(3,'karan','barghare','karan@gmail.com','1234','+918793031484','doctor','my pet name','dd'),(4,'kiran','chopade','kiran@gmail.com','1234','+918793031484','patient','my pet name','donkey'),(5,'shubham','mhaske','shubham@gmail.com','1234','+918793031484','accountant','my pet name','panto'),(6,'humesh','patil','humesh@gmail.com','1234','+915637437832','patient','my pet name','pet'),(7,'lalit ','patil','lalit@gmail.com','1234','+918793031484','patient','my pet name','jerry'),(8,'prashant','bunde','prashant@gmail.com','1234','+918793031484','patient','my pet name','doru'),(9,'rushi','pawar','rushi@gmail.com','1234','+918793031484','doctor','my pet name','rajo');
UNLOCK TABLES;





-- Table structure for table `employees`

DROP TABLE IF EXISTS `employees`;

CREATE TABLE `employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `hire_date` date DEFAULT NULL,
  `salary` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`user_id`),
  CONSTRAINT `id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ;


-- Dumping data for table `employees`

LOCK TABLES `employees` WRITE;
INSERT INTO `employees` VALUES (101,1,'2021-12-23','2021-12-11',1000000),(102,2,'2021-12-24','2021-12-25',15000),(103,3,'2022-01-13','2022-01-21',86997),(104,5,'2022-01-20','2022-01-21',25000),(105,9,NULL,'2022-01-12',95997);
UNLOCK TABLES;





-- Table structure for table `doctors`

DROP TABLE IF EXISTS `doctors`;

CREATE TABLE `doctors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `emp_id` int DEFAULT NULL,
  `charges` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `doctor_emp_id_idx` (`emp_id`),
  CONSTRAINT `doctor_emp_id` FOREIGN KEY (`emp_id`) REFERENCES `employees` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Dumping data for table `doctors`

LOCK TABLES `doctors` WRITE;
INSERT INTO `doctors` VALUES (201,103,8698),(202,105,1498);
UNLOCK TABLES;





-- Table structure for table `patients`

DROP TABLE IF EXISTS `patients`;

CREATE TABLE `patients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `ward_id` int DEFAULT NULL,
  `doctor_id` int DEFAULT NULL,
  `date_of_admission` date DEFAULT NULL,
  `blood_group` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `prescription` varchar(2000) DEFAULT NULL,
  `bed_alloted` int DEFAULT NULL,
  `payment_status` varchar(45) DEFAULT NULL,
  `patient_problem` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`user_id`),
  KEY `pat_ward_id_idx` (`ward_id`),
  KEY `pat_doctor_id_idx` (`doctor_id`),
  CONSTRAINT `pat_doctor_id` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pat_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pat_ward_id` FOREIGN KEY (`ward_id`) REFERENCES `wards` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ;


-- Dumping data for table `patients`

LOCK TABLES `patients` WRITE;
INSERT INTO `patients` VALUES (301,4,39,201,'2022-01-12','O-',NULL,'26/1/2022->take xray and come on 28/1/2022 10.00am',11,'pending','high fever'),(302,6,38,201,'2022-01-13','AB+',NULL,'aaaaaaads',19,'pending','severe bleeding');
UNLOCK TABLES;





-- Table structure for table `medicines_assigned`

DROP TABLE IF EXISTS `medicines_assigned`;

CREATE TABLE `medicines_assigned` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pat_id` int DEFAULT NULL,
  `medicine_id` int DEFAULT NULL,
  `prescription` varchar(500) DEFAULT NULL,
  `medicine_qty` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `medicine_id_idx` (`medicine_id`),
  KEY `medicine_pat_id` (`pat_id`),
  CONSTRAINT `medicine_id` FOREIGN KEY (`medicine_id`) REFERENCES `medicines` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `medicine_pat_id` FOREIGN KEY (`pat_id`) REFERENCES `patients` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ;

-- Dumping data for table `medicines_assigned`


LOCK TABLES `medicines_assigned` WRITE;

UNLOCK TABLES;






-- Table structure for table `doctor_visits`

DROP TABLE IF EXISTS `doctor_visits`;

CREATE TABLE `doctor_visits` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pat_id` int DEFAULT NULL,
  `doctor_id` int DEFAULT NULL,
  `visits` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `doctot_visits_doctor_id_idx` (`doctor_id`),
  KEY `doctor_visit_pat_id` (`pat_id`),
  CONSTRAINT `doctor_visit_pat_id` FOREIGN KEY (`pat_id`) REFERENCES `patients` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `doctot_visits_doctor_id` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Dumping data for table `doctor_visits`

LOCK TABLES `doctor_visits` WRITE;
INSERT INTO `doctor_visits` VALUES (401,301,201,4),(402,302,201,3);
UNLOCK TABLES;

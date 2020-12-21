CREATE TABLE `sections` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `location_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh1cgipy8gux1p48omjujfpwto` (`location_id`),
  CONSTRAINT `FKh1cgipy8gux1p48omjujfpwto` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
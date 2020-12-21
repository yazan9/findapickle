CREATE TABLE `locations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `store_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrvriv44kjqrxwk5pux4p7b9ig` (`store_id`),
  CONSTRAINT `FKrvriv44kjqrxwk5pux4p7b9ig` FOREIGN KEY (`store_id`) REFERENCES `stores` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
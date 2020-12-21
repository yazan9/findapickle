CREATE TABLE `sections_items` (
  `section_id` bigint(20) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  KEY `FKlw9329jlkx0bhcjmsfrx4r2y4` (`item_id`),
  KEY `FKiv9hfvsaf5ia8k8xsb31b3c93` (`section_id`),
  CONSTRAINT `FKiv9hfvsaf5ia8k8xsb31b3c93` FOREIGN KEY (`section_id`) REFERENCES `sections` (`id`),
  CONSTRAINT `FKlw9329jlkx0bhcjmsfrx4r2y4` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
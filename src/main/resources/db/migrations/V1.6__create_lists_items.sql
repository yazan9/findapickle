CREATE TABLE `lists_items` (
  `shopping_list_id` bigint(20) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  KEY `FK4fdl8wodr1y06dtqjyr2eccll` (`item_id`),
  KEY `FKknog1ydbdgq3xlss6i4bv7qe8` (`shopping_list_id`),
  CONSTRAINT `FK4fdl8wodr1y06dtqjyr2eccll` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`),
  CONSTRAINT `FKknog1ydbdgq3xlss6i4bv7qe8` FOREIGN KEY (`shopping_list_id`) REFERENCES `shopping_lists` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
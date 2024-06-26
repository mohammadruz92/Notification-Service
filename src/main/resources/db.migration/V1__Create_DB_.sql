CREATE TABLE `language` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(3) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `address` (
  `branch_entity_id` bigint(20) NOT NULL,
  `city` varchar(64) DEFAULT NULL,
  `country` varchar(64) DEFAULT NULL,
  `state` varchar(64) DEFAULT NULL,
  `street` varchar(64) DEFAULT NULL,
  `zip_code` varchar(10) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  KEY `FKgghamk9krb258jba1jtvs5ryb` (`branch_entity_id`),
  CONSTRAINT `FKgghamk9krb258jba1jtvs5ryb` FOREIGN KEY (`branch_entity_id`) REFERENCES `branch_entity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `branch_entity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `latitude` int(11) DEFAULT NULL,
  `longitude` int(11) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKphshedjuwdqvi8kutyjsxp2hi` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
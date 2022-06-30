CREATE TABLE `tb_ratings` (
  `id_rating` bigint NOT NULL AUTO_INCREMENT,
  `imdbid` varchar(255) DEFAULT NULL,
  `rating` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id_rating`)
)
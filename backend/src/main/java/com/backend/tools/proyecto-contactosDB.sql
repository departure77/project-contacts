-- ---
-- Globals
-- ---

-- SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
-- SET FOREIGN_KEY_CHECKS=0;

-- ---
-- Table 'usuarios'
-- 
-- ---

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `id_usuario` INTEGER NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `surname` VARCHAR(50) NOT NULL,
  `mail` VARCHAR(50) NOT NULL,
  `verification_code` VARCHAR(6) NOT NULL,
  `verified_mail` bit NOT NULL,
  `creation_date` DATETIME NOT NULL,
  `id_contacto` INTEGER NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY (`username`, `mail`)
);

-- ---
-- Table 'contactos'
-- 
-- ---

DROP TABLE IF EXISTS `contactos`;

CREATE TABLE `contactos` (
  `id_contacto` INTEGER NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `surname` VARCHAR(50) NOT NULL,
  `phone_number` VARCHAR(50) NOT NULL,
  `id_usuario` INTEGER NOT NULL,
  PRIMARY KEY (`id_contacto`),
  UNIQUE KEY (`phone_number`)
);

-- ---
-- Foreign Keys
-- ---

ALTER TABLE `usuarios` ADD FOREIGN KEY (id_contacto) REFERENCES `contactos` (`id_contacto`);
ALTER TABLE `contactos` ADD FOREIGN KEY (id_usuario) REFERENCES `usuarios` (`id_usuario`);

-- ---
-- Table Properties-- ---
-- Globals
-- ---

-- SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
-- SET FOREIGN_KEY_CHECKS=0;

-- ---
-- Table 'usuarios'
-- 
-- ---

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `id_usuario` INTEGER NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `surname` VARCHAR(50) NOT NULL,
  `mail` VARCHAR(50) NOT NULL,
  `verification_code` VARCHAR(6) NOT NULL,
  `verified_mail` bit NOT NULL,
  `creation_date` DATETIME NOT NULL,
  `id_contacto` INTEGER NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY (`username`, `mail`)
);

-- ---
-- Table 'contactos'
-- 
-- ---

DROP TABLE IF EXISTS `contactos`;

CREATE TABLE `contactos` (
  `id_contacto` INTEGER NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `surname` VARCHAR(50) NOT NULL,
  `phone_number` VARCHAR(50) NOT NULL,
  `id_usuario` INTEGER NOT NULL,
  PRIMARY KEY (`id_contacto`),
  UNIQUE KEY (`phone_number`)
);

-- ---
-- Foreign Keys
-- ---

ALTER TABLE `usuarios` ADD FOREIGN KEY (id_contacto) REFERENCES `contactos` (`id_contacto`);
ALTER TABLE `contactos` ADD FOREIGN KEY (id_usuario) REFERENCES `usuarios` (`id_usuario`);

-- ---
-- Table Properties
-- ---

-- ALTER TABLE `usuarios` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `contactos` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ---
-- Test Data
-- ---

-- INSERT INTO `usuarios` (`id_usuario`,`username`,`password`,`name`,`surname`,`mail`,`verification_code`,`verified_mail`,`creation_date`,`id_contacto`) VALUES
-- ('','','','','','','','','','');
-- INSERT INTO `contactos` (`id_contacto`,`name`,`surname`,`phone_number`,`id_usuario`) VALUES
-- ('','','','','');
-- ---

-- ALTER TABLE `usuarios` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `contactos` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ---
-- Test Data
-- ---

-- INSERT INTO `usuarios` (`id_usuario`,`username`,`password`,`name`,`surname`,`mail`,`verification_code`,`verified_mail`,`creation_date`,`id_contacto`) VALUES
-- ('','','','','','','','','','');
-- INSERT INTO `contactos` (`id_contacto`,`name`,`surname`,`phone_number`,`id_usuario`) VALUES
-- ('','','','','');
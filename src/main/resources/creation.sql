CREATE DATABASE IF NOT EXISTS `test_data` CHARACTER SET utf8;
USE test_data;
CREATE TABLE IF NOT EXISTS test_data (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `UserData` VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

INSERT INTO `test_data`(`id`, `UserData`, `Password`) VALUES (1,'nedved198725','nedved1987');
INSERT INTO `test_data`(`id`, `UserData`, `Password`) VALUES (2,'nedved198725','nedved1987');
INSERT INTO `test_data`(`id`, `UserData`, `Password`) VALUES (3,'nedved198725','nedved1987');
INSERT INTO `test_data`(`id`, `UserData`, `Password`) VALUES (4,'nedved198725','nedved1987');
INSERT INTO `test_data`(`id`, `UserData`, `Password`) VALUES (5,'nedved198725','nedved1987');

CREATE TABLE IF NOT EXISTS `simpledata` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `Password` varchar(255) NOT NULL,
    `UserData` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

INSERT INTO `simpledata`(`id`, `Password`, `UserData`) VALUES (1,'nedved1987','nedved198725');
INSERT INTO `simpledata`(`id`, `Password`, `UserData`) VALUES (2,'nedved1987','nedved198725');
INSERT INTO `simpledata`(`id`, `Password`, `UserData`) VALUES (3,'nedved1987','nedved198725');
INSERT INTO `simpledata`(`id`, `Password`, `UserData`) VALUES (4,'nedved1987','nedved198725');
INSERT INTO `simpledata`(`id`, `Password`, `UserData`) VALUES (5,'nedved1987','nedved198725');

CREATE TABLE IF NOT EXISTS `test_DAO` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name_link` VARCHAR(255),
    `color` VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;


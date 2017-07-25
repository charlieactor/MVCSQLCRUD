-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema hikesdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `hikesdb` ;

-- -----------------------------------------------------
-- Schema hikesdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hikesdb` DEFAULT CHARACTER SET utf8 ;
USE `hikesdb` ;

-- -----------------------------------------------------
-- Table `Country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Country` ;

CREATE TABLE IF NOT EXISTS `Country` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `country_code` CHAR(3) NULL,
  `name` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Address` ;

CREATE TABLE IF NOT EXISTS `Address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(200) NULL,
  `city` VARCHAR(200) NULL,
  `state_province` VARCHAR(200) NULL,
  `country_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_address_to_country_idx` (`country_id` ASC),
  CONSTRAINT `fk_address_to_country`
    FOREIGN KEY (`country_id`)
    REFERENCES `Country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Hike`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Hike` ;

CREATE TABLE IF NOT EXISTS `Hike` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NULL,
  `difficulty` ENUM('Easy', 'Easy-Moderate', 'Moderate', 'Moderate-Hard', 'Hard', 'Hard-Expert', 'Expert') NULL,
  `length` FLOAT NULL,
  `distance_from_city` FLOAT NULL,
  `fact` VARCHAR(2000) NULL,
  `address_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_hike_to_address_idx` (`address_id` ASC),
  CONSTRAINT `fk_hike_to_address`
    FOREIGN KEY (`address_id`)
    REFERENCES `Address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Picture`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Picture` ;

CREATE TABLE IF NOT EXISTS `Picture` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `file_path` VARCHAR(500) NULL,
  `Hike_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Hike_id`),
  INDEX `fk_Picture_Hike1_idx` (`Hike_id` ASC),
  CONSTRAINT `fk_Picture_Hike1`
    FOREIGN KEY (`Hike_id`)
    REFERENCES `Hike` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO hikesuser@localhost;
 DROP USER hikesuser@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'hikesuser'@'localhost' IDENTIFIED BY 'hikesuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'hikesuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `Country`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikesdb`;
INSERT INTO `Country` (`id`, `country_code`, `name`) VALUES (1, 'USA', 'United States of America');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Address`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikesdb`;
INSERT INTO `Address` (`id`, `address`, `city`, `state_province`, `country_id`) VALUES (1, 'Bear Lake Trailhead, Bear Lake Rd', 'Estes Park', 'CO', 1);
INSERT INTO `Address` (`id`, `address`, `city`, `state_province`, `country_id`) VALUES (2, 'Co Hwy 115', 'Allenspark', 'CO', 1);
INSERT INTO `Address` (`id`, `address`, `city`, `state_province`, `country_id`) VALUES (3, '3550 S Raleigh St', 'Denver', 'CO', 1);
INSERT INTO `Address` (`id`, `address`, `city`, `state_province`, `country_id`) VALUES (4, 'Kinnikinic Rd', 'Boulder', 'CO', 1);
INSERT INTO `Address` (`id`, `address`, `city`, `state_province`, `country_id`) VALUES (5, 'Willow Creek Loop', 'Littleton', 'CO', 1);
INSERT INTO `Address` (`id`, `address`, `city`, `state_province`, `country_id`) VALUES (6, '92 Crawford Gulch Rd', 'Golden', 'CO', 1);
INSERT INTO `Address` (`id`, `address`, `city`, `state_province`, `country_id`) VALUES (7, 'Mt Evans Road', 'Evans', 'CO', 1);
INSERT INTO `Address` (`id`, `address`, `city`, `state_province`, `country_id`) VALUES (8, '30299 Buffalo Park Rd', 'Evergreen', 'CO', 1);
INSERT INTO `Address` (`id`, `address`, `city`, `state_province`, `country_id`) VALUES (9, '7599 Fall River Rd', 'Idaho Springs', 'CO', 1);
INSERT INTO `Address` (`id`, `address`, `city`, `state_province`, `country_id`) VALUES (10, '32391 Stagecoach Boulevard', 'Evergreen', 'CO', 1);
INSERT INTO `Address` (`id`, `address`, `city`, `state_province`, `country_id`) VALUES (DEFAULT, NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Hike`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikesdb`;
INSERT INTO `Hike` (`id`, `name`, `difficulty`, `length`, `distance_from_city`, `fact`, `address_id`) VALUES (1, 'Mills Lake', 'Moderate', 5.3, 77.6, 'Alberta Falls - 30 Foot Waterfall AND Mills Lake is surrounded by 13K foot peaks', 1);
INSERT INTO `Hike` (`id`, `name`, `difficulty`, `length`, `distance_from_city`, `fact`, `address_id`) VALUES (2, 'Ouzel Falls', 'Moderate', 5.4, 67.9, 'Ouzel Falls is a 40-foot waterfall that sits at an elevation of 9370 feet.', 2);
INSERT INTO `Hike` (`id`, `name`, `difficulty`, `length`, `distance_from_city`, `fact`, `address_id`) VALUES (3, 'Bear Creek Trail', 'Easy', 9.1, 9.4, 'Bear Creek Trail is an easy hike complete with picnic tables and charcoal grills', 3);
INSERT INTO `Hike` (`id`, `name`, `difficulty`, `length`, `distance_from_city`, `fact`, `address_id`) VALUES (4, 'Royal Arch Trail', 'Moderate-Hard', 3.4, 28.5, 'Royal Arch is often crowded in the summer', 4);
INSERT INTO `Hike` (`id`, `name`, `difficulty`, `length`, `distance_from_city`, `fact`, `address_id`) VALUES (5, 'Willow Creek Trail', 'Easy', 1.4, 24.2, 'There is a creek in which you can rest your feet before hiking onward.', 5);
INSERT INTO `Hike` (`id`, `name`, `difficulty`, `length`, `distance_from_city`, `fact`, `address_id`) VALUES (6, 'Raccoon Trail', 'Moderate', 2.5, 28.7, 'Raccoon Trail starts at panorama point - a scenic overlook with a 100 mile span view of the continental divide', 6);
INSERT INTO `Hike` (`id`, `name`, `difficulty`, `length`, `distance_from_city`, `fact`, `address_id`) VALUES (7, 'Pesman Trail', 'Moderate', 2.8, 48.4, 'This trail is located in the Mount Goliath Natural Area - which has trees as old as 1600 years of age', 7);
INSERT INTO `Hike` (`id`, `name`, `difficulty`, `length`, `distance_from_city`, `fact`, `address_id`) VALUES (8, 'Alder Three Sisters Trail', 'Moderate', 6.9, 30.5, 'This trail has great views of Mounts Evans and Bierstadt', 8);
INSERT INTO `Hike` (`id`, `name`, `difficulty`, `length`, `distance_from_city`, `fact`, `address_id`) VALUES (9, 'St. Mary\'s Glacier Loop', 'Easy-Moderate', 1.6, 44, 'Once you reach the lake you can continue to the glacier - where you\'re likely to see bighorn sheep mountain goats and elk', 9);
INSERT INTO `Hike` (`id`, `name`, `difficulty`, `length`, `distance_from_city`, `fact`, `address_id`) VALUES (10, 'Bergen Peak Trail', 'Hard', 9.4, 24.6, 'This trail covers 1700 feet in elevation gain and rewards with views of Pikes Peak Mount Evans and the Continental Divide', 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Picture`
-- -----------------------------------------------------
START TRANSACTION;
USE `hikesdb`;
INSERT INTO `Picture` (`id`, `file_path`, `Hike_id`) VALUES (1, 'http://43mbhp3aft5g3uc0tuhsk4a8.wpengine.netdna-cdn.com/wp-content/uploads/2016/04/mills-lake-rocky-mountain-national-park-steve-bratman-creative-commons-831x560.jpg', 1);
INSERT INTO `Picture` (`id`, `file_path`, `Hike_id`) VALUES (2, 'https://static1.squarespace.com/static/56c10efdd210b8f08fd1d37d/56c36a0c62cd94b81b2233a8/58497c2d9f74568ff2d3262f/1481210929682/Ouzel+Falls+%28683x1024%29.jpg', 2);
INSERT INTO `Picture` (`id`, `file_path`, `Hike_id`) VALUES (3, 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/Bear_Creek_in_Lair_o_the_Bear_Park.jpg/1200px-Bear_Creek_in_Lair_o_the_Bear_Park.jpg', 3);
INSERT INTO `Picture` (`id`, `file_path`, `Hike_id`) VALUES (4, 'http://www.danieljoderphotography.com/wp-content/uploads/2014/11/201411226323-ePre-Dawn-Glow-at-Royal-Arch.jpg', 4);
INSERT INTO `Picture` (`id`, `file_path`, `Hike_id`) VALUES (5, 'http://www.riderplanet-usa.com/atv/trails/photo/08fe646c143e4f56888aea529ca7e4a1.jpg', 5);
INSERT INTO `Picture` (`id`, `file_path`, `Hike_id`) VALUES (6, 'http://4.bp.blogspot.com/-gs-l-yIfbkE/U6Y6aMR5b_I/AAAAAAAAH8g/H1f-6XDQM1s/s1600/IMG_2220-001.JPG', 6);
INSERT INTO `Picture` (`id`, `file_path`, `Hike_id`) VALUES (7, 'http://1.bp.blogspot.com/__XOe1juJ7uo/SkVgvWAQR9I/AAAAAAAACRs/dgnCmoySU6w/s400/pesman_view_2.jpg', 7);
INSERT INTO `Picture` (`id`, `file_path`, `Hike_id`) VALUES (8, 'http://dayhikesneardenver.com/wp-content/uploads/2010/06/01-alderfer-three-sisters-park-header.jpg', 8);
INSERT INTO `Picture` (`id`, `file_path`, `Hike_id`) VALUES (9, 'http://images.summitpost.org/medium/112259.jpg', 9);
INSERT INTO `Picture` (`id`, `file_path`, `Hike_id`) VALUES (10, 'http://www.protrails.com/protrails/trails/Bergen%20-%20meadows%20-%20evans%201.jpg', 10);

COMMIT;


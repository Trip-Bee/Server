-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema tripbee
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tripbee
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tripbee` DEFAULT CHARACTER SET utf8 ;
USE `tripbee` ;

-- -----------------------------------------------------
-- Table `tripbee`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripbee`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(30) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `nickname` VARCHAR(30) NULL,
  `status` VARCHAR(20) NULL DEFAULT NULL,
  `role` VARCHAR(20) NULL DEFAULT NULL,
  `profile_image` VARCHAR(255) NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `tripbee`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripbee`.`post` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(30) NULL DEFAULT NULL,
  `category` VARCHAR(30) NULL DEFAULT NULL,
  `content` MEDIUMTEXT NULL DEFAULT NULL,
  `is_deleted` TINYINT NULL DEFAULT 0,
  `hit` BIGINT NULL DEFAULT 0,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `writer_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_post_user_idx` (`writer_id` ASC) VISIBLE,
  CONSTRAINT `fk_post_user`
    FOREIGN KEY (`writer_id`)
    REFERENCES `tripbee`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `tripbee`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripbee`.`comment` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(255) NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `post_id` BIGINT NOT NULL,
  `writer_id` BIGINT NOT NULL,
  `is_deleted` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_post1_idx` (`post_id` ASC) VISIBLE,
  INDEX `fk_comment_user1_idx` (`writer_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `tripbee`.`post` (`id`),
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`writer_id`)
    REFERENCES `tripbee`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `tripbee`.`sido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripbee`.`sido` (
  `code` INT NOT NULL,
  `name` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `tripbee`.`gugun`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripbee`.`gugun` (
  `code` INT NOT NULL,
  `name` VARCHAR(30) NULL DEFAULT NULL,
  `sido_code` INT NOT NULL,
  PRIMARY KEY (`code`, `sido_code`),
  INDEX `fk_gugun_sido1_idx` (`sido_code` ASC) VISIBLE,
  CONSTRAINT `fk_gugun_sido1`
    FOREIGN KEY (`sido_code`)
    REFERENCES `tripbee`.`sido` (`code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `tripbee`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripbee`.`image` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(255) NULL DEFAULT NULL,
  `post_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_image_post1_idx` (`post_id` ASC) VISIBLE,
  CONSTRAINT `fk_image_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `tripbee`.`post` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `tripbee`.`spot_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripbee`.`spot_type` (
  `id` INT NOT NULL,
  `name` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Dumping data for table `tripbee`.`spot_type`
-- -----------------------------------------------------
INSERT INTO `tripbee`.`spot_type` VALUES (12, '관광지'),(14, '문화시설'),(15, '축제공연행사'),(25, '여행코스'),(28, '레포츠'),(32, '숙박'),(38, '쇼핑'),(39, '음식점');


-- -----------------------------------------------------
-- Table `tripbee`.`spot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripbee`.`spot` (
	select content_id id, content_type_id type_id, title, addr1, addr2, zipcode, tel, first_image image, latitude, longitude, sido_code, gugun_code
    from attraction_info
);
ALTER TABLE `tripbee`.`spot` ADD CONSTRAINT PRIMARY KEY (`id`);
ALTER TABLE `tripbee`.`spot` ADD FOREIGN KEY (`type_id`) REFERENCES `tripbee`.`spot_type` (`id`);
ALTER TABLE `tripbee`.`spot` ADD FOREIGN KEY (`sido_code`) REFERENCES `tripbee`.`sido` (`code`);
ALTER TABLE `tripbee`.`spot` ADD FOREIGN KEY (`gugun_code`) REFERENCES `tripbee`.`gugun` (`code`);


-- -----------------------------------------------------
-- Table `tripbee`.`like`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripbee`.`like` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` BIGINT NOT NULL,
  `spot_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_like_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_like_spot1_idx` (`spot_id` ASC) VISIBLE,
  CONSTRAINT `fk_like_spot1`
    FOREIGN KEY (`spot_id`)
    REFERENCES `tripbee`.`spot` (`id`),
  CONSTRAINT `fk_like_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `tripbee`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `tripbee`.`theme`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripbee`.`theme` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `tripbee`.`plan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripbee`.`plan` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(30) NULL,
  `total_cost` BIGINT NULL DEFAULT 0,
  `start_date` DATE NULL DEFAULT (CURRENT_DATE),
  `end_date` DATE NULL DEFAULT (CURRENT_DATE),
  `head_count` INT NULL DEFAULT NULL,
  `hit` BIGINT NULL DEFAULT 0,
  `user_id` BIGINT NOT NULL,
  `theme_id` BIGINT NOT NULL,
  `is_deleted`  TINYINT  default 0,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_plan_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_plan_theme1_idx` (`theme_id` ASC) VISIBLE,
  CONSTRAINT `fk_plan_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `tripbee`.`user` (`id`),
  CONSTRAINT `fk_plan_theme1`
    FOREIGN KEY (`theme_id`)
    REFERENCES `tripbee`.`theme` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `tripbee`.`vehicle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripbee`.`vehicle` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `tripbee`.`plan_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripbee`.`plan_details` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order` INT NULL DEFAULT NULL,
  `cost` BIGINT NULL DEFAULT 0,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `plan_id` BIGINT NOT NULL,
  `spot_id` INT NOT NULL,
  `vehicle_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_plan_details_plan1_idx` (`plan_id` ASC) VISIBLE,
  INDEX `fk_plan_details_spot1_idx` (`spot_id` ASC) VISIBLE,
  INDEX `fk_plan_details_vehicle1_idx` (`vehicle_id` ASC) VISIBLE,
  CONSTRAINT `fk_plan_details_plan1`
    FOREIGN KEY (`plan_id`)
    REFERENCES `tripbee`.`plan` (`id`),
  CONSTRAINT `fk_plan_details_spot1`
    FOREIGN KEY (`spot_id`)
    REFERENCES `tripbee`.`spot` (`id`),
  CONSTRAINT `fk_plan_details_vehicle1`
    FOREIGN KEY (`vehicle_id`)
    REFERENCES `tripbee`.`vehicle` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Dumping data for table `gugun`
-- -----------------------------------------------------
INSERT INTO `gugun` VALUES
(1,'강남구',1),(1,'강화군',2),(1,'대덕구',3),(1,'남구',4),(1,'광산구',5),(1,'강서구',6),(1,'중구',7),(1,'세종특별자치시',8),(1,'가평군',31),(1,'강릉시',32),(1,'괴산군',33),(1,'공주시',34),(1,'경산시',35),(1,'거제시',36),(1,'고창군',37),(1,'강진군',38),(1,'남제주군',39),(2,'강동구',1),(2,'계양구',2),(2,'동구',3),(2,'달서구',4),(2,'남구',5),(2,'금정구',6),(2,'남구',7),(2,'고양시',31),(2,'고성군',32),(2,'단양군',33),(2,'금산군',34),(2,'경주시',35),(2,'거창군',36),(2,'군산시',37),(2,'고흥군',38),(2,'북제주군',39),(3,'강북구',1),(3,'미추홀구',2),(3,'서구',3),(3,'달성군',4),(3,'동구',5),(3,'기장군',6),(3,'동구',7),(3,'과천시',31),(3,'동해시',32),(3,'보은군',33),(3,'논산시',34),(3,'고령군',35),(3,'고성군',36),(3,'김제시',37),(3,'곡성군',38),(3,'서귀포시',39),(4,'강서구',1),(4,'남동구',2),(4,'유성구',3),(4,'동구',4),(4,'북구',5),(4,'남구',6),(4,'북구',7),(4,'광명시',31),(4,'삼척시',32),(4,'영동군',33),(4,'당진시',34),(4,'구미시',35),(4,'김해시',36),(4,'남원시',37),(4,'광양시',38),(4,'제주시',39),(5,'관악구',1),(5,'동구',2),(5,'중구',3),(5,'북구',4),(5,'서구',5),(5,'동구',6),(5,'울주군',7),(5,'광주시',31),(5,'속초시',32),(5,'옥천군',33),(5,'보령시',34),(5,'군위군',35),(5,'남해군',36),(5,'무주군',37),(5,'구례군',38),(6,'광진구',1),(6,'부평구',2),(6,'서구',4),(6,'동래구',6),(6,'구리시',31),(6,'양구군',32),(6,'음성군',33),(6,'부여군',34),(6,'김천시',35),(6,'마산시',36),(6,'부안군',37),(6,'나주시',38),(7,'구로구',1),(7,'서구',2),(7,'수성구',4),(7,'부산진구',6),(7,'군포시',31),(7,'양양군',32),(7,'제천시',33),(7,'서산시',34),(7,'문경시',35),(7,'밀양시',36),(7,'순창군',37),(7,'담양군',38),(8,'금천구',1),(8,'연수구',2),(8,'중구',4),(8,'북구',6),(8,'김포시',31),(8,'영월군',32),(8,'진천군',33),(8,'서천군',34),(8,'봉화군',35),(8,'사천시',36),(8,'완주군',37),(8,'목포시',38),(9,'노원구',1),(9,'옹진군',2),(9,'사상구',6),(9,'남양주시',31),(9,'원주시',32),(9,'청원군',33),(9,'아산시',34),(9,'상주시',35),(9,'산청군',36),(9,'익산시',37),(9,'무안군',38),
(10,'도봉구',1),(10,'중구',2),(10,'사하구',6),(10,'동두천시',31),(10,'인제군',32),(10,'청주시',33),(10,'성주군',35),(10,'양산시',36),(10,'임실군',37),(10,'보성군',38),(11,'동대문구',1),(11,'서구',6),(11,'부천시',31),(11,'정선군',32),(11,'충주시',33),(11,'예산군',34),(11,'안동시',35),(11,'장수군',37),(11,'순천시',38),(12,'동작구',1),(12,'수영구',6),(12,'성남시',31),(12,'철원군',32),(12,'증평군',33),(12,'천안시',34),(12,'영덕군',35),(12,'의령군',36),(12,'전주시',37),(12,'신안군',38),(13,'마포구',1),(13,'연제구',6),(13,'수원시',31),(13,'춘천시',32),(13,'청양군',34),(13,'영양군',35),(13,'진주시',36),(13,'정읍시',37),(13,'여수시',38),(14,'서대문구',1),(14,'영도구',6),(14,'시흥시',31),(14,'태백시',32),(14,'태안군',34),(14,'영주시',35),(14,'진해시',36),(14,'진안군',37),(15,'서초구',1),(15,'중구',6),(15,'안산시',31),(15,'평창군',32),(15,'홍성군',34),(15,'영천시',35),(15,'창녕군',36),(16,'성동구',1),(16,'해운대구',6),(16,'안성시',31),(16,'홍천군',32),(16,'계룡시',34),(16,'예천군',35),(16,'창원시',36),(16,'영광군',38),(17,'성북구',1),(17,'안양시',31),(17,'화천군',32),(17,'울릉군',35),(17,'통영시',36),(17,'영암군',38),(18,'송파구',1),(18,'양주시',31),(18,'횡성군',32),(18,'울진군',35),(18,'하동군',36),(18,'완도군',38),(19,'양천구',1),(19,'양평군',31),(19,'의성군',35),(19,'함안군',36),(19,'장성군',38),
(20,'영등포구',1),(20,'여주시',31),(20,'청도군',35),(20,'함양군',36),(20,'장흥군',38),(21,'용산구',1),(21,'연천군',31),(21,'청송군',35),(21,'합천군',36),(21,'진도군',38),(22,'은평구',1),(22,'오산시',31),(22,'칠곡군',35),(22,'함평군',38),(23,'종로구',1),(23,'용인시',31),(23,'포항시',35),(23,'해남군',38),(24,'중구',1),(24,'의왕시',31),(24,'화순군',38),(25,'중랑구',1),(25,'의정부시',31),(26,'이천시',31),(27,'파주시',31),(28,'평택시',31),(29,'포천시',31),(30,'하남시',31),(31,'화성시',31);


-- -----------------------------------------------------
-- Dumping data for table `sido`
-- -----------------------------------------------------
INSERT INTO `sido` VALUES (1,'서울'),(2,'인천'),(3,'대전'),(4,'대구'),(5,'광주'),(6,'부산'),(7,'울산'),(8,'세종특별자치시'),(31,'경기도'),(32,'강원도'),(33,'충청북도'),(34,'충청남도'),(35,'경상북도'),(36,'경상남도'),(37,'전라북도'),(38,'전라남도'),(39,'제주도');


DROP TABLE IF EXISTS `attraction_info`;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

create database giaohang;

CREATE TABLE `giaohang`.`useraccount` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `idCustomer` VARCHAR(45) NULL,
  `idStaff` VARCHAR(45) NULL,
  `status` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`));
ALTER TABLE `giaohang`.`useraccount` 
ADD UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE;

CREATE TABLE `giaohang`.`fee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
   `distance` INT DEFAULT 0,
  `mass` INT DEFAULT 0,
  `price` INT DEFAULT 0,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `giaohang`.`receipt` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idCustomer` VARCHAR(45) NULL,
  `idShip` VARCHAR(45) NULL,
   `idFee` VARCHAR(45) NULL,
  `date` DATETIME NULL,
  `origin` VARCHAR(45) NULL,
  `destination` VARCHAR(45) NULL,
  `duration` INT NULL,
  `status` INT NULL DEFAULT 0,
  `price` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `giaohang`.`invoice_details` (
  `receipt_id` VARCHAR(45) NOT NULL,
  `goods_id` VARCHAR(45) NOT NULL,
  `goods_name` VARCHAR(45) NOT NULL,
  `amount` INT NULL DEFAULT 0,
  `price` INT NULL,
  PRIMARY KEY (`receipt_id`, `goods_id`));
  
  CREATE TABLE `giaohang`.`goods` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `giaohang`.`places` (
  `idplaces` INT NOT NULL AUTO_INCREMENT,
  `origin` VARCHAR(150) NULL,
  `destination` VARCHAR(150) NULL,
  `distance` DECIMAL(4,2) NULL DEFAULT '0',
  `duration` INT NULL DEFAULT 0,
  PRIMARY KEY (`idplaces`));

CREATE TABLE `giaohang`.`customer` (
  `idcustomer` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `telephone` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `status` INT NULL,
  PRIMARY KEY (`idcustomer`),
  UNIQUE INDEX `telephone_UNIQUE` (`telephone` ASC) VISIBLE);

CREATE TABLE `giaohang`.`staff` (
  `idstaff` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `idmanager` VARCHAR(45) NULL,
  PRIMARY KEY (`idstaff`));
  
INSERT INTO `giaohang`.`staff` (`name`) VALUES ('A');
INSERT INTO `giaohang`.`staff` (`name`, `idmanager`) VALUES ('B', '1');
INSERT INTO `giaohang`.`staff` (`name`, `idmanager`) VALUES ('C', '1');

INSERT INTO `giaohang`.`useraccount` (`username`, `password`, `idCustomer`) VALUES ('abc', '123', '1');
INSERT INTO `giaohang`.`useraccount` (`username`, `password`, `idStaff`) VALUES ('admin', '123', '1');
  
INSERT INTO `giaohang`.`fee` (`name`, `distance`, `mass`, `price`) VALUES ('Normal', 6.5, '500', '23000');
INSERT INTO `giaohang`.`fee` (`name`, `distance`, `mass`, `price`) VALUES ('Normal', 11, '500', '25000');
INSERT INTO `giaohang`.`fee` (`name`, `distance`, `mass`, `price`) VALUES ('Express', 5, '500', '50000');
INSERT INTO `giaohang`.`fee` (`name`, `distance`, `mass`, `price`) VALUES ('Express', 9.75, '500', '55000');
INSERT INTO `giaohang`.`fee` (`name`, `distance`, `mass`, `price`) VALUES ('Normal', 6.5, '1000', '33000');
INSERT INTO `giaohang`.`fee` (`name`, `distance`, `mass`, `price`) VALUES ('Normal', 11, '1000', '35000');
INSERT INTO `giaohang`.`fee` (`name`, `distance`, `mass`, `price`) VALUES ('Express', 5, '1000', '80000');
INSERT INTO `giaohang`.`fee` (`name`, `distance`, `mass`, `price`) VALUES ('Express', 9.75, '1000', '85000');

INSERT INTO `giaohang`.`goods` (`name`) VALUES ('fragile');
INSERT INTO `giaohang`.`goods` (`name`) VALUES ('Normal');

INSERT INTO `giaohang`.`receipt` (`idCustomer`, `idShip`, `idFee`, `date`, `origin`, `destination`, `duration`, `status`) VALUES ('1', '1', '5','20220101', 'A', 'D', '3456', '1');
INSERT INTO `giaohang`.`receipt` (`idCustomer`, `idShip`, `idFee`, `date`, `origin`, `destination`, `duration`, `status`) VALUES ('1', '103', '5','20220310', 'C', 'D', '2085', '1');

INSERT INTO `giaohang`.`invoice_details` (`receipt_id`, `goods_id`, `goods_name`, `amount`) VALUES ('1', '1', 'L??? th???y tinh', '3');
INSERT INTO `giaohang`.`invoice_details` (`receipt_id`, `goods_id`, `goods_name`, `amount`) VALUES ('1', '2', 'K??? b??n', '1');
INSERT INTO `giaohang`.`invoice_details` (`receipt_id`, `goods_id`, `goods_name`, `amount`) VALUES ('2', '2', 'T??i li???u', '1');

INSERT INTO `giaohang`.`places` (`origin`, `destination`, `distance`, `duration`) VALUES ('22, 6 ??. L?? Th??c Ho???ch, Ph?? Th??? Ho??, T??n Ph??, Th??nh ph??? H??? Ch?? Minh, Vi???t Nam', '256 L?? V??n S???, Ph?????ng 1, T??n B??nh, Th??nh ph??? H??? Ch?? Minh, Vi???t Nam', '6.6', '1380');
INSERT INTO `giaohang`.`places` (`origin`, `destination`, `distance`, `duration`) VALUES ('189 H??a B??nh, Hi???p T??n, T??n Ph??, Th??nh ph??? H??? Ch?? Minh, Vi???t Nam', '4 Ph???m Ng???c Th???ch, B???n Ngh??, Qu???n 1, Th??nh ph??? H??? Ch?? Minh, Vi???t Nam', '10', '1680');
INSERT INTO `giaohang`.`places` (`origin`, `destination`, `distance`, `duration`) VALUES ('23 ???????ng Ngh??a H??a, Ph?????ng 6, T??n B??nh, Th??nh ph??? H??? Ch?? Minh, Vi???t Nam', 'Ph?????c Ki???n, Nh?? B??, Th??nh ph??? H??? Ch?? Minh, Vi???t Nam', '16.4', '2460');

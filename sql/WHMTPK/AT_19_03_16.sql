ALTER TABLE `tpkwhm`.`importproduct` DROP COLUMN `AdvanceBook` ;


ALTER TABLE `tpkwhm`.`importproduct` ADD COLUMN `SaleWarehouseID` BIGINT(20) NULL DEFAULT NULL,
ADD CONSTRAINT `FK_IP_SW`
FOREIGN KEY (`SaleWarehouseID` )
REFERENCES `tpkwhm`.`warehouse` (`WarehouseID` )
  ON DELETE SET NULL
  ON UPDATE NO ACTION
, ADD INDEX `FK_IP_SW_idx` (`SaleWarehouseID` ASC) ;


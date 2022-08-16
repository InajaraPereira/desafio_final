USE frescos;

INSERT INTO `frescos`.`in_bound_order` (`date`, `agent_id`, `section_id`)
 VALUES ('2022-08-12', '1', '1');
 
INSERT INTO `frescos`.`batch` (`batch_number`, `current_quantity`, 
`current_temperature`, `due_date`, `initial_quantity`, `manufacturing_date`, 
`manufacturing_time`, `minimum_temperature`, `adsense_id`) 
VALUES ('5', '200', '13', '2023-08-14', '200', '2022-04-27', 
'2022-01-03 13:15:30.000000', '0', '3');

INSERT INTO `frescos`.`batch` (`batch_number`, `current_quantity`, 
`current_temperature`, `due_date`, `initial_quantity`, `manufacturing_date`, 
`manufacturing_time`, `minimum_temperature`, `adsense_id`) 
VALUES ('5', '200', '13', '2023-08-14', '200', '2022-04-27', 
'2022-01-03 13:15:30.000000', '0', '4');

UPDATE `frescos`.`batch` SET `current_quantity` = '600' WHERE (`id` = '5');
UPDATE `frescos`.`batch` SET `current_quantity` = '600' WHERE (`id` = '6');
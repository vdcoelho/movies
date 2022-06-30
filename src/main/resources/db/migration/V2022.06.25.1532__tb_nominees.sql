create table tb_nominees (
    id_nominee int not null AUTO_INCREMENT,
    additional_info varchar(1000),
    category varchar(150),
    year_of_nominated varchar(15),
    nominated varchar(1000),
    won boolean,
  primary key (id_nominee))
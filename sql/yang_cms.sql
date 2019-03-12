create database yang_cms;

use yang_cms;

create table t_resource(
  id int(10) primary key auto_increment,
  name varchar(200),
  path varchar(300),
  create_time datetime,
  create_uid int(10),
  update_time datetime,
  update_uid int(10)
)
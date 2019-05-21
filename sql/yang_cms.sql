create database yang_cms;

use yang_cms;

create table t_resource(
  id int(10) primary key auto_increment,
  name varchar(200),
  path varchar(300),
  create_time datetime,
  create_uid int(10),
  update_time datetime,
  update_uid int(10),
  `type` varchar(10),
  size int(10),
  status tinyint(1),
  md5 varchar(16)
)

drop synonym NC_PROJ_PREFIX;
drop table NC_PROJ_PREFIX;
--委托单前缀表
create  table NC_PROJ_PREFIX 
(
   PK_SAFE_TYPE         CHAR(20 CHAR)        not null,
   NC_SAFE_CODE         VARCHAR(20),
   NC_SAFE_NAME         VARCHAR(100),
   ISENABLE              SMALLINT,
   constraint PK_NC_PROJ_PREFIX primary key (PK_SAFE_TYPE)
);
select * from NC_PROJ_PREFIX for update;
--此表当前只有一行数据，值为A，其它随便，就不再写insert了





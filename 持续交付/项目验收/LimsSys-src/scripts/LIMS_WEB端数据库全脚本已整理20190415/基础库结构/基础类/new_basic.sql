--委托单前缀表
create table NC_PROJ_PREFIX 
(
   PK_SAFE_TYPE         CHAR(20 CHAR)        not null,
   NC_SAFE_CODE         VARCHAR(20),
   NC_SAFE_NAME         VARCHAR(100),
   ISENALE              SMALLINT,
   constraint PK_NC_PROJ_PREFIX primary key (PK_SAFE_TYPE)
);
--此表当前只有一行数据，值为A，其它随便，就不再写insert了

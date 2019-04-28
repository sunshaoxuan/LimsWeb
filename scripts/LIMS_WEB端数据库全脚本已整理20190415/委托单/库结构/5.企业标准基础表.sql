--企业标准基础表
alter table NC_BASEN_TYPE
   drop constraint FK_NC_BASEN_REFERENCE_NC_BASPR;

alter table NC_SAMPLE_INFO
   drop constraint FK_NC_SAMPL_REFERENCE_NC_BASEN;

drop table NC_BASEN_TYPE cascade constraints;

/*==============================================================*/
/* Table: NC_BASEN_TYPE                                         */
/*==============================================================*/
create table NC_BASEN_TYPE 
(
   PK_BASEN_TYPE        CHAR(20 CHAR)        not null,
   PK_BASPROD_TYPE      CHAR(20 CHAR),
   NC_BASEN_CODE        CHAR(200 CHAR),
   NC_BBASEN_NAME       VARCHAR2(200),
   VDEF1                VARCHAR2(200),
   VDEF2                VARCHAR2(200),
   VDEF3                VARCHAR2(200),
   VDEF4                VARCHAR2(200),
   VDEF5                VARCHAR2(200),
   constraint PK_NC_BASEN_TYPE primary key (PK_BASEN_TYPE)
);

alter table NC_BASEN_TYPE
   add constraint FK_NC_BASEN_REFERENCE_NC_BASPR foreign key (PK_BASPROD_TYPE)
      references NC_BASPROD_TYPE (PK_BASPROD_TYPE);
      
--
insert into NC_BASEN_TYPE
select substr(SYS_GUID(),1,20), 
(select nbt.pk_basprod_type from NC_BASPROD_TYPE nbt where nbt.nc_basprodtype_name = b.description),
RANK() over(order by b.name,b.description asc),b.name,'','','','',''
from (select distinct p.description ,p.name
from product p where p.removed = 'F') b;



--基础产品名称表

create table NC_BASPROD_NAME 
(
   PK_BASPROD_NAME      CHAR(20 CHAR)        not null,
   NC_BASPROD_CODE      CHAR(200 CHAR),
   NC_BASPROD_NAME      VARCHAR2(200),
   VDEF1                VARCHAR2(200),
   VDEF2                VARCHAR2(200),
   VDEF3                VARCHAR2(200),
   VDEF4                VARCHAR2(200),
   VDEF5                VARCHAR2(200),
   constraint PK_NC_BASPROD_NAME primary key (PK_BASPROD_NAME)
);

insert into NC_BASPROD_NAME
select distinct substr(SYS_GUID(),1,20),RANK() over(order by p.name asc) "序号",p.name,'','','','',''
from product p ;

--产品系列
create table NC_BASPROD_TYPE 
(
   PK_BASPROD_TYPE      CHAR(20 CHAR)        not null,
   NC_BASPRODTYPE_CODE  CHAR(200 CHAR),
   NC_BASPRODTYPE_NAME  VARCHAR2(200),
   VDEF1                VARCHAR2(200),
   VDEF2                VARCHAR2(200),
   VDEF3                VARCHAR2(200),
   VDEF4                VARCHAR2(200),
   VDEF5                VARCHAR2(200),
   constraint PK_NC_BASPROD_TYPE primary key (PK_BASPROD_TYPE)
);
insert into NC_BASPROD_TYPE
select substr(SYS_GUID(),1,20),RANK() over(order by description asc) "序号",description,'','','','','' 
from (
select distinct p.description
from product p );

----基础产品规格号表
create table NC_BASPROD_POINT 
(
   PK_BASPROD_POINT     CHAR(20 CHAR)        not null,
   NC_BASPRODPOINT_CODE CHAR(200 CHAR),
   NC_BASPRODPOINT_NAME VARCHAR2(200),
   VDEF1                VARCHAR2(200),
   VDEF2                VARCHAR2(200),
   VDEF3                VARCHAR2(200),
   VDEF4                VARCHAR2(200),
   VDEF5                VARCHAR2(200),
   constraint PK_NC_BASPROD_POINT primary key (PK_BASPROD_POINT)
);

insert into NC_BASPROD_POINT
select substr(SYS_GUID(),1,20),RANK() over(order by sampling_point asc) "序号",sampling_point,'','','','','' 
from 
(select distinct pg.sampling_point
from product_grade pg );

---结构类型
create table NC_BASPROD_STRUCT 
(
   PK_BASPROD_STRUCT    CHAR(20 CHAR)        not null,
   NC_BASPRODSTRUCT_CODE CHAR(200 CHAR),
   NC_BASPRODSTRUCT_NAME VARCHAR2(200),
   VDEF1                VARCHAR2(200),
   VDEF2                VARCHAR2(200),
   VDEF3                VARCHAR2(200),
   VDEF4                VARCHAR2(200),
   VDEF5                VARCHAR2(200),
   constraint PK_NC_BASPROD_STRUCT primary key (PK_BASPROD_STRUCT)
);
insert into NC_BASPROD_STRUCT
select substr(SYS_GUID(),1,20),RANK() over(order by grade asc) "序号",grade,'','','','','' 
from 
(select distinct pg.grade
from product_grade pg );

--触点类型
--select p.c_allowed_contact from product p where p.c_allowed_contact
create table NC_BASPROD_CONTACT 
(
   PK_BASPROD_CONTACT   CHAR(20 CHAR)        not null,
   NC_BASPRODCONTACT_CODE CHAR(200 CHAR),
   NC_BASPRODCONTACT_NAME VARCHAR2(200),
   VDEF1                VARCHAR2(200),
   VDEF2                VARCHAR2(200),
   VDEF3                VARCHAR2(200),
   VDEF4                VARCHAR2(200),
   VDEF5                VARCHAR2(200),
   constraint PK_NC_BASPROD_CONTACT primary key (PK_BASPROD_CONTACT)
);
insert into NC_BASPROD_CONTACT
select substr(SYS_GUID(),1,20),RANK() over(order by c_allowed_contact asc) "序号",c_allowed_contact,'','','','','' 
from (
select distinct p.c_allowed_contact
from product p );


--基础温度表
create table NC_BASPROD_TEMP 
(
   PK_BASPROD_TEMP      CHAR(20 CHAR)        not null,
   NC_BASPRODTEMP_CODE  CHAR(200 CHAR),
   NC_BASPRODTEMP_NAME  VARCHAR2(200),
   VDEF1                VARCHAR2(200),
   VDEF2                VARCHAR2(200),
   VDEF3                VARCHAR2(200),
   VDEF4                VARCHAR2(200),
   VDEF5                VARCHAR2(200),
   constraint PK_NC_BASPROD_TEMP primary key (PK_BASPROD_TEMP)
);

insert into NC_BASPROD_TEMP
select substr(SYS_GUID(),1,20),RANK() over(order by stage asc) "序号",stage,'','','','','' 
from (
select distinct pgs.stage
from prod_grade_STAGE pgs );

--select * from NC_BASPROD_TEMP

--------------------------------------------------
----删除重复数据

delete  NC_BASPROD_NAME u 
  where u.nc_basprod_name in (select u.nc_basprod_name from 
    NC_BASPROD_NAME u group by u.nc_basprod_name having count(u.nc_basprod_name) > 1) 
      and rowid not in (select min(rowid) from 
        NC_BASPROD_NAME u group by u.nc_basprod_name having count(u.nc_basprod_name )>1) ;

        
        


------------------------------------------------


-----------------------------------
--样品参照表
alter table NC_SAMPLE_INFO
   drop constraint FK_NC_SAMPL_REFERENCE_NC_FSPR;

alter table NC_SAMPLE_INFO
   drop constraint FK_NC_SAMPL_REFERENCE_NC_ESPR;

alter table NC_SAMPLE_INFO
   drop constraint FK_NC_SAMPL_REFERENCE_NC_CDSPR;

alter table NC_SAMPLE_INFO
   drop constraint FK_NC_SAMPL_REFERENCE_NC_ABSPR;

alter table NC_SAMPLE_INFO
   drop constraint FK_NC_SAMPL_REFERENCE_NC_SYSPR;

alter table NC_SAMPLE_INFO
   drop constraint FK_NC_SAMPL_REFERENCE_NC_BASPR;

alter table NC_SAMPLE_INFO
   drop constraint FK_NC_SAMPL_REFERENCE_NC_PROD_;

drop table NC_SAMPLE_INFO cascade constraints;

/*==============================================================*/
/* Table: NC_SAMPLE_INFO                                        */
/*==============================================================*/
create table NC_SAMPLE_INFO 
(
   PK_SAMPLE_INFO       CHAR(20 CHAR)        not null,
   PK_PROD_TYPE         CHAR(20 CHAR),
   PK_BASPROD_NAME      CHAR(20 CHAR),
   PK_BASPROD_TYPE      CHAR(20 CHAR),
   PK_BASPROD_POINT     CHAR(20 CHAR),
   PK_BASPROD_STRUCT    CHAR(20 CHAR),
   PK_BASPROD_CONTACT   CHAR(20 CHAR),
   PK_BASPROD_TEMP      CHAR(20 CHAR),
   PK_BASEN_TYPE        CHAR(20 CHAR),
   SAMPLE_INFO_CODE     VARCHAR2(200 CHAR),
   DEF1                 VARCHAR2(200 CHAR),
   DEF2                 VARCHAR2(200 CHAR),
   DEF3                 VARCHAR2(200 CHAR),
   DEF4                 VARCHAR2(200 CHAR),
   DEF5                 VARCHAR2(200 CHAR),
   NAME                 VARCHAR2(20 CHAR),
   C_PROD_TYPE_C1       VARCHAR2(20 CHAR),
   DESCRIPTION          VARCHAR2(254 CHAR),
   ENSTARD              VARCHAR2(20 CHAR),
   TEST_LIST            VARCHAR2(40 CHAR),
   SAMPLING_POINT       VARCHAR2(254 CHAR),
   GRADE                VARCHAR2(20 CHAR),
   C_ALLOWED_CONTACT    VARCHAR2(100 CHAR),
   STAGE                VARCHAR2(20 CHAR),
   ISENABLE             SMALLINT,
   constraint PK_NC_SAMPLE_INFO primary key (PK_SAMPLE_INFO)
);

alter table NC_SAMPLE_INFO
   add constraint FK_NC_SAMPL_REFERENCE_NC_PROD_ foreign key (PK_PROD_TYPE)
      references NC_PROD_TYPE (PK_PROD_TYPE);

alter table NC_SAMPLE_INFO
   add constraint FK_NC_SAMPL_REFERENCE_NC_BASPR foreign key (PK_BASPROD_NAME)
      references NC_BASPROD_NAME (PK_BASPROD_NAME);

alter table NC_SAMPLE_INFO
   add constraint FK_NC_SAMPL_REFERENCE_NC_SYSPR foreign key (PK_BASPROD_TYPE)
      references NC_BASPROD_TYPE (PK_BASPROD_TYPE);

alter table NC_SAMPLE_INFO
   add constraint FK_NC_SAMPL_REFERENCE_NC_ABSPR foreign key (PK_BASPROD_POINT)
      references NC_BASPROD_POINT (PK_BASPROD_POINT);

alter table NC_SAMPLE_INFO
   add constraint FK_NC_SAMPL_REFERENCE_NC_CDSPR foreign key (PK_BASPROD_STRUCT)
      references NC_BASPROD_STRUCT (PK_BASPROD_STRUCT);

alter table NC_SAMPLE_INFO
   add constraint FK_NC_SAMPL_REFERENCE_NC_ESPR foreign key (PK_BASPROD_CONTACT)
      references NC_BASPROD_CONTACT (PK_BASPROD_CONTACT);

alter table NC_SAMPLE_INFO
   add constraint FK_NC_SAMPL_REFERENCE_NC_FSPR foreign key (PK_BASPROD_TEMP)
      references NC_BASPROD_TEMP (PK_BASPROD_TEMP);

--样品取数
truncate table NC_SAMPLE_INFO;
select * from NC_SAMPLE_INFO;

insert into NC_SAMPLE_INFO
select distinct
substr(SYS_GUID(),1,20) ,b."产品大类表主键",b.nbn,b.nbt,b.nbp,b.nbs,b.nbc,b.nbte,b.pk_basen_type
,b."序号",b."def1",b."def2",b."def3",b."def4",b."def5",b."产品名称",
b."产品类别",b."产品系列",b."企标",b."Test_list名",b."规格号",b."结构类型",b."触点类型",b."温度",dr
from
(
-----
select distinct
npt.pk_prod_type  "产品大类表主键",
(select nbn.pk_basprod_name from NC_BASPROD_NAME nbn where nbn.nc_basprod_name = p.name) nbn,
(select nbt.pk_basprod_type from NC_BASPROD_TYPE nbt where nbt.nc_basprodtype_name = p.description) nbt,
(select nbp.pk_basprod_point  from NC_BASPROD_POINT nbp where nbp.nc_basprodpoint_name = pg.sampling_point) nbp,
(select nbs.pk_basprod_struct  from NC_BASPROD_STRUCT nbs where nbs.nc_basprodstruct_name = pg.grade) nbs,
(select nbc.pk_basprod_contact from NC_BASPROD_CONTACT nbc where nbc.nc_basprodcontact_name = p.c_allowed_contact) nbc,
(select nbte.pk_basprod_temp from NC_BASPROD_TEMP nbte where nbte.nc_basprodtemp_name = pgs.stage) nbte,
--(select nbtt.pk_basen_type  from NC_BASEN_TYPE nbtt where nbtt.nc_bbasen_name = p.name) nbtt,
nbtt.pk_basen_type,
RANK() over(order by p.name,pg.sampling_point asc) "序号",
'' "def1" ,'' "def2",'' "def3",'' "def4",'' "def5" ,p.name "产品名称",
p.c_prod_type_c1 "产品类别",p.description "产品系列",p.code "企标",p.test_list "Test_list名",pg.sampling_point "规格号",
pg.grade "结构类型",p.c_allowed_contact "触点类型",pgs.stage "温度",1 dr
from product p , product_grade pg,prod_grade_STAGE pgs,NC_PROD_TYPE npt,NC_BASEN_TYPE nbtt,NC_BASPROD_TYPE n
where p.ACTIVE = 'T' and p.removed = 'F'
and p.name = pg.product and p.name = pgs.product and p.name = nbtt.nc_bbasen_name and n.pk_basprod_type = nbtt.pk_basprod_type
and p.c_prod_type_c1 = npt.c_prod_type_c1 
----
) b 
/*
select distinct
substr(SYS_GUID(),1,20) ,b."产品大类表主键",b.nbn,b.nbt,b.nbp,b.nbs,b.nbc,b.nbte,b.nbtt

,b."序号",b."def1",b."def2",b."def3",b."def4",b."def5",b."产品名称",
b."产品类别",b."产品系列",b."企标",b."Test_list名",b."规格号",b."结构类型",b."触点类型",b."温度",dr
from
(select distinct
npt.pk_prod_type  "产品大类表主键",
(select nbn.pk_basprod_name from NC_BASPROD_NAME nbn where nbn.nc_basprod_name = p.name) nbn,
(select nbt.pk_basprod_type from NC_BASPROD_TYPE nbt where nbt.nc_basprodtype_name = p.description) nbt,
(select nbp.pk_basprod_point  from NC_BASPROD_POINT nbp where nbp.nc_basprodpoint_name = pg.sampling_point) nbp,
(select nbs.pk_basprod_struct  from NC_BASPROD_STRUCT nbs where nbs.nc_basprodstruct_name = pg.grade) nbs,
(select nbc.pk_basprod_contact from NC_BASPROD_CONTACT nbc where nbc.nc_basprodcontact_name = p.c_allowed_contact) nbc,
(select nbte.pk_basprod_temp from NC_BASPROD_TEMP nbte where nbte.nc_basprodtemp_name = pgs.stage) nbte,
--(select nbtt.pk_basen_type  from NC_BASEN_TYPE nbtt where nbtt.nc_bbasen_name = p.name) nbtt,
RANK() over(order by p.name,pg.sampling_point asc) "序号",
'' "def1" ,'' "def2",'' "def3",'' "def4",'' "def5" ,p.name "产品名称",
p.c_prod_type_c1 "产品类别",p.description "产品系列",p.code "企标",p.test_list "Test_list名",pg.sampling_point "规格号",
pg.grade "结构类型",p.c_allowed_contact "触点类型",pgs.stage "温度",0 dr
from product p , product_grade pg,prod_grade_STAGE pgs,NC_PROD_TYPE npt
where p.ACTIVE = 'T' and p.removed = 'F'
and p.name = pg.product and p.name = pgs.product 
and p.c_prod_type_c1 = npt.c_prod_type_c1 ) b
*/ 
--where b.nbte  not in (select e.pk_basprod_temp from NC_BASPROD_TEMP e)
--and (p.description = 'HF115F-/Q……（0060）')




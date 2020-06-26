--一级产品分类
create table nc_first_type 
(
   pk_first_type      CHAR(20 CHAR)        not null,
   PROD_TYPE            VARCHAR2(20),
   nc_first_code      CHAR(50 CHAR),
   nc_first_name      CHAR(200 CHAR),
   is_enable          SMALLINT,
   constraint PK_NC_FIRST_TYPE primary key (pk_first_type)
);

alter table nc_first_type
   add constraint FK_NC_FIRST_REFERENCE_C_PROD_T foreign key (PROD_TYPE)
      references LIMS_DATA.C_PROD_TYPE (PROD_TYPE);
--
Insert into nc_first_type
select substr(SYS_GUID(),1,20),c.prod_type,RANK() over(order by c.prod_type asc) ncode,c.description,1
from c_prod_type c 

--二级产品分类
create table nc_second_type 
(
   pk_second_type     CHAR(20 CHAR)        not null,
   PROD_TYPE            VARCHAR2(50),
   NAME                 VARCHAR2(50),
   pk_first_type      CHAR(20 CHAR),
   nc_second_code     CHAR(50 CHAR),
   nc_second_name     CHAR(200 CHAR),
   is_enable          SMALLINT,
   nc_descript        CHAR(200 CHAR),
   constraint PK_NC_SECOND_TYPE primary key (pk_second_type)
);

alter table nc_second_type
   add constraint FK_NC_SECON_REFERENCE_C_PROD_T foreign key (PROD_TYPE, NAME)
      references LIMS_DATA.C_PROD_TYPE_C1 (PROD_TYPE, NAME);

alter table nc_second_type
   add constraint FK_NC_SECON_REFERENCE_NC_FIRST foreign key (pk_first_type)
      references nc_first_type (pk_first_type);
--
Insert into nc_second_type
select substr(SYS_GUID(),1,20),c1.prod_type,c1.name,
cft.pk_first_type,RANK() over(order by c1.name asc),c1.name,1,c1.description
from c_prod_type c,c_prod_type_c1 c1,nc_first_type cft
where c1.prod_type = c.prod_type and cft.prod_type = c.prod_type
-----------------------------------------------------------------------------------------------------------
--三级分类

create table nc_third_type 
(
   pk_third_type      CHAR(20 CHAR)        not null,
   PROD_TYPE            VARCHAR2(50),
   C_PROD_TYPE_C1       VARCHAR2(50),
   P_NAME               VARCHAR2(50),
   pk_first_type      CHAR(20 CHAR),
   pk_second_type     CHAR(20 CHAR),
   nc_third_code      CHAR(50 CHAR),
   nc_third_name      CHAR(200 CHAR),
   is_enable          SMALLINT,
   nc_descript        CHAR(200 CHAR),
   constraint PK_NC_THIRD_TYPE primary key (pk_third_type)
);

alter table nc_third_type
   add constraint FK_NC_THIRD_REFERENCE_C_PROD_T foreign key (PROD_TYPE, C_PROD_TYPE_C1, P_NAME)
      references LIMS_DATA.C_PROD_TYPE_C2 (PROD_TYPE, C_PROD_TYPE_C1, P_NAME);

alter table nc_third_type
   add constraint FK_NC_THIRD_REFERENCE_NC_FIRST foreign key (pk_first_type)
      references nc_first_type (pk_first_type);

alter table nc_third_type
   add constraint FK_NC_THIRD_REFERENCE_NC_SECON foreign key (pk_second_type)
      references nc_second_type (pk_second_type);
--------------
Insert into nc_third_type
select substr(SYS_GUID(),1,20),c2.prod_type,c2.c_prod_type_c1,c2.p_name,nft.pk_first_type,nst.pk_second_type,
RANK() over(order by c2.p_name asc),c2.p_name,1,c2.description
from C_PROD_TYPE c,C_PROD_TYPE_C1 c1,C_PROD_TYPE_C2 c2,nc_first_type nft,nc_second_type nst
where c.prod_type = c1.prod_type 
and   c1.name = c2.c_prod_type_c1 
and   c.prod_type = c2.prod_type
and   nft.prod_type = nst.prod_type
and   nst.name = c2.c_prod_type_c1 
and   nft.prod_type = c2.prod_type;


--------------------------------全分类----------------------------------------------------
--先去掉NC_PROD_TYPE表三个外键

--关联表清除

delete from nc_sample_info;
delete from nc_third_type;
delete from nc_second_type;
delete from nc_first_type;
delete from nc_prod_type;




Insert into NC_PROD_TYPE
 select substr(SYS_GUID(),1,20),c.prod_type,
c1.prod_type,c1.name,
--c2.prod_type,c2.c_prod_type_c1,c2.p_name,
decode(c2.prod_type,null,c1.name,c2.prod_type) prod_type,
decode(c2.c_prod_type_c1,null,c1.name,c2.c_prod_type_c1) c_prod_type_c1,
decode(c2.p_name,null,c1.name,c2.p_name) c_prod_type_c1,
c1.report_name,c1.report_name,c2.report_name,'1' from C_PROD_TYPE c left join C_PROD_TYPE_C1 c1 on c.prod_type = c1.prod_type
left join C_PROD_TYPE_C2 c2 on c2.c_prod_type_c1 = c1.name;

Insert into nc_first_type
select substr(SYS_GUID(),1,20),c.prod_type,RANK() over(order by c.prod_type asc) ncode,c.description,1
from c_prod_type c ;

Insert into nc_second_type
select substr(SYS_GUID(),1,20),c1.prod_type,c1.name,
cft.pk_first_type,RANK() over(order by c1.name asc),c1.name,1,c1.description
from c_prod_type c,c_prod_type_c1 c1,nc_first_type cft
where c1.prod_type = c.prod_type and cft.prod_type = c.prod_type;

Insert into nc_third_type
select substr(SYS_GUID(),1,20),c2.prod_type,c2.c_prod_type_c1,c2.p_name,nft.pk_first_type,nst.pk_second_type,
RANK() over(order by c2.p_name asc),c2.p_name,1,c2.description
from C_PROD_TYPE c,C_PROD_TYPE_C1 c1,C_PROD_TYPE_C2 c2,nc_first_type nft,nc_second_type nst
where c.prod_type = c1.prod_type 
and   c1.name = c2.c_prod_type_c1 
and   c.prod_type = c2.prod_type
and   nft.prod_type = nst.prod_type
and   nst.name = c2.c_prod_type_c1 
and   nft.prod_type = c2.prod_type;


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
) b ;







DROP TABLE NC_BASPROD_NAME;
DROP TABLE NC_BASPROD_TYPE;
DROP TABLE NC_BASPROD_POINT;
DROP TABLE NC_BASPROD_STRUCT;
DROP TABLE NC_BASPROD_CONTACT;
DROP TABLE NC_BASPROD_TEMP;
DROP TABLE NC_PROD_TYPE;
DROP TABLE NC_FIRST_TYPE;
DROP TABLE NC_SECOND_TYPE;
DROP TABLE NC_THIRD_TYPE;
DROP TABLE NC_UNITS_TYPE;
drop table NC_PROJ_PREFIX;
drop table NC_PROJ_TYPE;
DROP TABLE NC_RESULT_TYPE;
drop table NC_BASEN_TYPE;
DROP TABLE NC_TEST_INIT;
DROP TABLE NC_STATUS_TYPE;
drop table NC_PRODAUTH_TYPE;
drop table NC_SAFE_TYPE;
drop table NC_REPORT_TYPE;
drop table NC_REPORT_LANG;
drop table NC_RATAIN_HANDLE;
drop table NC_CUSTOMER_TYPE;
drop table NC_SAMPLE_GROUP;
drop table NC_TEST_TYPE;
DROP TABLE NC_CONTACT_BRAND;
drop table NC_TESTREQUEST_TYPE;
drop table NC_SAMPLE_INFO;

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



--产品类别
create table NC_PROD_TYPE
(
  pk_prod_type   CHAR(20 CHAR) not null,
  prod_type      VARCHAR2(20),
  c_p_prod_type  VARCHAR2(50),
  name           VARCHAR2(50),
  c_p_prod_type2 VARCHAR2(50),
  c_prod_type_c1 VARCHAR2(50),
  p_name         VARCHAR2(50),
  f_prod_type    VARCHAR2(20),
  s_prod_type    VARCHAR2(20),
  t_prod_type    VARCHAR2(20),
  isenable       INTEGER
);
--先去掉此表三个外键
Insert into NC_PROD_TYPE
 select substr(SYS_GUID(),1,20),c.prod_type,
c1.prod_type,c1.name,
--c2.prod_type,c2.c_prod_type_c1,c2.p_name,
decode(c2.prod_type,null,c1.name,c2.prod_type) prod_type,
decode(c2.c_prod_type_c1,null,c1.name,c2.c_prod_type_c1) c_prod_type_c1,
decode(c2.p_name,null,c1.name,c2.p_name) c_prod_type_c1,
c1.report_name,c1.report_name,c2.report_name,'1' from C_PROD_TYPE c left join C_PROD_TYPE_C1 c1 on c.prod_type = c1.prod_type
left join C_PROD_TYPE_C2 c2 on c2.c_prod_type_c1 = c1.name;

--追加
merge into NC_PROD_TYPE n
using (select substr(SYS_GUID(), 1, 20)  a,
              c.prod_type b,
              c1.prod_type c,
              c1.name d,
              c2.prod_type e,
              c2.c_prod_type_c1 f,
              c2.p_name g,
              c1.report_name h,
              c1.report_name i,
              c2.report_name j,
              '1' k
         from C_PROD_TYPE c
         left join C_PROD_TYPE_C1 c1
           on c.prod_type = c1.prod_type
         left join C_PROD_TYPE_C2 c2
           on c2.c_prod_type_c1 = c1.name) l
on (n.name = l.d)
when matched then update set n.prod_type = l.b
when not matched then
  insert
    --(l.a,l.b,l.c,l.d,l.e,l.f,l.g,l.h,l.i,l.j,l.k)
    (n.pk_prod_type,
     n.prod_type,
     n.c_p_prod_type,
     n.name,
     n.c_p_prod_type2,
     n.c_prod_type_c1,
     n.p_name,
     n.f_prod_type,
     n.s_prod_type,
     n.t_prod_type,
     n.isenable)
  values
    (a,b,c,d,e,f,g,h,i,j,k);


--一级分类
create table NC_FIRST_TYPE
(
  pk_first_type CHAR(20 CHAR) not null,
  prod_type     VARCHAR2(20),
  nc_first_code CHAR(50 CHAR),
  nc_first_name CHAR(200 CHAR),
  is_enable     INTEGER
);
INSERT INTO NC_FIRST_TYPE (PK_FIRST_TYPE, PROD_TYPE, NC_FIRST_CODE, NC_FIRST_NAME, IS_ENABLE) VALUES ('46214EE3032F4D76B775', 'CPA', '1', '继电器 ', 1);
INSERT INTO NC_FIRST_TYPE (PK_FIRST_TYPE, PROD_TYPE, NC_FIRST_CODE, NC_FIRST_NAME, IS_ENABLE) VALUES ('0BA7773FAF824B408AD4', 'CPB', '2', '低压电器', 1);
INSERT INTO NC_FIRST_TYPE (PK_FIRST_TYPE, PROD_TYPE, NC_FIRST_CODE, NC_FIRST_NAME, IS_ENABLE) VALUES ('CB3EE699AE8D4D60AEF1', 'CPC', '3', '高低压成套设备', 1);
INSERT INTO NC_FIRST_TYPE (PK_FIRST_TYPE, PROD_TYPE, NC_FIRST_CODE, NC_FIRST_NAME, IS_ENABLE) VALUES ('D8A1E0681E1143678F00', 'CPE', '4', '模块/控制器 ', 1);
INSERT INTO NC_FIRST_TYPE (PK_FIRST_TYPE, PROD_TYPE, NC_FIRST_CODE, NC_FIRST_NAME, IS_ENABLE) VALUES ('DCB0125B2AD74EDB9F07', 'CPF', '5', '传感器 ', 1);
INSERT INTO NC_FIRST_TYPE (PK_FIRST_TYPE, PROD_TYPE, NC_FIRST_CODE, NC_FIRST_NAME, IS_ENABLE) VALUES ('B4D41AAF551F48F0BF1D', 'CPG', '6', '灭弧室 ', 1);
INSERT INTO NC_FIRST_TYPE (PK_FIRST_TYPE, PROD_TYPE, NC_FIRST_CODE, NC_FIRST_NAME, IS_ENABLE) VALUES ('EC9850802B174AD7818D', 'CPL', '7', '连接器 ', 1);
INSERT INTO NC_FIRST_TYPE (PK_FIRST_TYPE, PROD_TYPE, NC_FIRST_CODE, NC_FIRST_NAME, IS_ENABLE) VALUES ('E5EE6E1197F7452F9797', 'CPY', '8', '零部件 ', 1);
INSERT INTO NC_FIRST_TYPE (PK_FIRST_TYPE, PROD_TYPE, NC_FIRST_CODE, NC_FIRST_NAME, IS_ENABLE) VALUES ('E5BC1F38208A40BCA0FD', 'CPZ', '9', '其它类别', 1);



--二级分类
create table NC_SECOND_TYPE
(
  pk_second_type CHAR(20 CHAR) not null,
  prod_type      VARCHAR2(50),
  name           VARCHAR2(50),
  pk_first_type  CHAR(20 CHAR),
  nc_second_code CHAR(50 CHAR),
  nc_second_name CHAR(200 CHAR),
  is_enable      INTEGER,
  nc_descript    CHAR(200 CHAR)
);
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('A330BC43C7824FCB864D', 'CPA', 'CPAB', '46214EE3032F4D76B775', '2', 'CPAB', 1, '汽车继电器');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('8C30A99480F34193A44D', 'CPA', 'CPAC', '46214EE3032F4D76B775', '3', 'CPAC', 1, '新能源继电器');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('27A330EAAB17415889A2', 'CPA', 'CPAD', '46214EE3032F4D76B775', '4', 'CPAD', 1, '电力继电器');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('597EF743F5E94E2C9716', 'CPA', 'CPAE', '46214EE3032F4D76B775', '5', 'CPAE', 1, '工业继电器');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('4FC2C303BA224A10A0B5', 'CPA', 'CPAF', '46214EE3032F4D76B775', '6', 'CPAF', 1, '密封继电器');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('6669714AAAE84FE39148', 'CPA', 'CPAG', '46214EE3032F4D76B775', '7', 'CPAG', 1, '信号继电器');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('9E5C46EF63E247AC83A5', 'CPA', 'CPAH', '46214EE3032F4D76B775', '8', 'CPAH', 1, '固体继电器');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('0043D3393C3A4D57A83B', 'CPB', 'CPBA', '0BA7773FAF824B408AD4', '9', 'CPBA', 1, '控制电器');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('16162AF0DB5C4DA99B04', 'CPB', 'CPBB', '0BA7773FAF824B408AD4', '10', 'CPBB', 1, '配电电器');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('B4967361025A4E9F8B88', 'CPC', 'CPCA', 'CB3EE699AE8D4D60AEF1', '11', 'CPCA', 1, '高压开关柜');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('B5FF06F86D874D8EA7AC', 'CPC', 'CPCB', 'CB3EE699AE8D4D60AEF1', '12', 'CPCB', 1, '低压开关柜');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('B6B258B5BA844A9A9D4C', 'CPE', 'CPEA', 'D8A1E0681E1143678F00', '13', 'CPEA', 1, '工业控制模块');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('732A52AA5B694416B9F6', 'CPE', 'CPEB', 'D8A1E0681E1143678F00', '14', 'CPEB', 1, '控制盒 ');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('442E17577F1742778BFC', 'CPA', 'CPAA', '46214EE3032F4D76B775', '1', 'CPAA', 1, '功率继电器');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('7AF967080197414FB6D9', 'CPE', 'CPEC', 'D8A1E0681E1143678F00', '15', 'CPEC', 1, '安全继电器模块');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('1F64CCB6EF674746AC50', 'CPE', 'CPED', 'D8A1E0681E1143678F00', '16', 'CPED', 1, '汽车电子模块');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('86E0614C3A0448E2B84D', 'CPE', 'CPEE', 'D8A1E0681E1143678F00', '17', 'CPEE', 1, '组合汽车继电器');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('0534AAECC8E7423F8763', 'CPE', 'CPEF', 'D8A1E0681E1143678F00', '18', 'CPEF', 1, '其他模块/控制器');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('9F828CFF78B845BD963C', 'CPF', 'CPFA', 'DCB0125B2AD74EDB9F07', '19', 'CPFA', 1, '氧传感器');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('57D4CB1936854626B836', 'CPG', 'CPGA', 'B4D41AAF551F48F0BF1D', '20', 'CPGA', 1, '真空灭弧室');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('9330191E028D4D4E93C8', 'CPL', 'CPLA', 'EC9850802B174AD7818D', '21', 'CPLA', 1, '工业连接器');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('CFD97A7AC2BA430295F1', 'CPY', 'CPYA', 'E5EE6E1197F7452F9797', '22', 'CPYA', 1, '塑压件 ');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('C80050048ABE4482B18F', 'CPY', 'CPYB', 'E5EE6E1197F7452F9797', '23', 'CPYB', 1, '金工件 ');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('4F132C80B7E442A29260', 'CPY', 'CPYC', 'E5EE6E1197F7452F9797', '24', 'CPYC', 1, '触点  ');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('45E3F2B760E94D00A6D8', 'CPY', 'CPYD', 'E5EE6E1197F7452F9797', '25', 'CPYD', 1, '部件  ');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('38CD902164644777873E', 'CPZ', 'CPZA', 'E5BC1F38208A40BCA0FD', '26', 'CPZA', 1, '连接组件');
INSERT INTO NC_SECOND_TYPE (PK_SECOND_TYPE, PROD_TYPE, NAME, PK_FIRST_TYPE, NC_SECOND_CODE, NC_SECOND_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('3901A73F7F144D76BAEF', 'CPZ', 'CPZB', 'E5BC1F38208A40BCA0FD', '27', 'CPZB', 1, '其他  ');


--三级分类
create table NC_THIRD_TYPE
(
  pk_third_type  CHAR(20 CHAR) not null,
  prod_type      VARCHAR2(50),
  c_prod_type_c1 VARCHAR2(50),
  p_name         VARCHAR2(50),
  pk_first_type  CHAR(20 CHAR),
  pk_second_type CHAR(20 CHAR),
  nc_third_code  CHAR(50 CHAR),
  nc_third_name  CHAR(200 CHAR),
  is_enable      INTEGER,
  nc_descript    CHAR(200 CHAR)
);
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('C9E312FCCDCD4506A18E', 'CPA', 'CPAB', 'CPABA', '46214EE3032F4D76B775', 'A330BC43C7824FCB864D', '2', 'CPABA', 1, 'PCB式汽车继电器');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('20FCBE82F1C54CD09812', 'CPA', 'CPAB', 'CPABB', '46214EE3032F4D76B775', 'A330BC43C7824FCB864D', '3', 'CPABB', 1, '插入式汽车继电器');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('276DDBC0BD534AA5B96B', 'CPA', 'CPAC', 'CPACA', '46214EE3032F4D76B775', '8C30A99480F34193A44D', '4', 'CPACA', 1, '新能源车用继电器');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('8F1A53F6062446B19B66', 'CPA', 'CPAC', 'CPACB', '46214EE3032F4D76B775', '8C30A99480F34193A44D', '5', 'CPACB', 1, '新能源电源控制继电器');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('67849108B4A84D708547', 'CPA', 'CPAD', 'CPADA', '46214EE3032F4D76B775', '27A330EAAB17415889A2', '6', 'CPADA', 1, '计量磁保持继电器');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('9E206192BC174F548E6D', 'CPA', 'CPAD', 'CPADB', '46214EE3032F4D76B775', '27A330EAAB17415889A2', '7', 'CPADB', 1, '功率磁保持继电器');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('73799A36E1B8431C9EF6', 'CPA', 'CPAD', 'CPADC', '46214EE3032F4D76B775', '27A330EAAB17415889A2', '8', 'CPADC', 1, '电力组件');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('F6A3653EF878415FB9C6', 'CPA', 'CPAE', 'CPAEA', '46214EE3032F4D76B775', '597EF743F5E94E2C9716', '9', 'CPAEA', 1, '工业继电器');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('8CF5E9690E5E4001B2B2', 'CPA', 'CPAE', 'CPAEB', '46214EE3032F4D76B775', '597EF743F5E94E2C9716', '10', 'CPAEB', 1, '继电器插座');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('3EF8E83D693243BEA69C', 'CPA', 'CPAE', 'CPAEC', '46214EE3032F4D76B775', '597EF743F5E94E2C9716', '11', 'CPAEC', 1, '安全继电器');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('52FC3EA811B043D78250', 'CPB', 'CPBA', 'CPBAA', '0BA7773FAF824B408AD4', '0043D3393C3A4D57A83B', '12', 'CPBAA', 1, '接触器 ');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('1EB824E28C824347B749', 'CPB', 'CPBA', 'CPBAB', '0BA7773FAF824B408AD4', '0043D3393C3A4D57A83B', '13', 'CPBAB', 1, '电动机保护器');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('7EE409F7704342BA93A7', 'CPA', 'CPAA', 'CPAAA', '46214EE3032F4D76B775', '442E17577F1742778BFC', '1', 'CPAAA', 1, null);
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('D90023FAE5BF4EEAAAFF', 'CPB', 'CPBB', 'CPBBA', '0BA7773FAF824B408AD4', '16162AF0DB5C4DA99B04', '14', 'CPBBA', 1, '塑壳断路器');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('E593951955D74EF983F2', 'CPB', 'CPBB', 'CPBBB', '0BA7773FAF824B408AD4', '16162AF0DB5C4DA99B04', '15', 'CPBBB', 1, '小型断路器');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('AA8C85FCEFFD4598AE6B', 'CPB', 'CPBB', 'CPBBC', '0BA7773FAF824B408AD4', '16162AF0DB5C4DA99B04', '16', 'CPBBC', 1, '双电源转换开关');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('159A7954C6E04275BF88', 'CPB', 'CPBB', 'CPBBD', '0BA7773FAF824B408AD4', '16162AF0DB5C4DA99B04', '17', 'CPBBD', 1, '隔离开关');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('7B6CC2E4311B4FDA91EF', 'CPB', 'CPBB', 'CPBBE', '0BA7773FAF824B408AD4', '16162AF0DB5C4DA99B04', '18', 'CPBBE', 1, '框架断路器');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('C3BFA575698340D995D3', 'CPB', 'CPBB', 'CPBBF', '0BA7773FAF824B408AD4', '16162AF0DB5C4DA99B04', '19', 'CPBBF', 1, '电涌保护器');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('5F179B73B91840179EEF', 'CPF', 'CPFA', 'CPFAA', 'DCB0125B2AD74EDB9F07', '9F828CFF78B845BD963C', '20', 'CPFAA', 1, '开关型片式氧传感器');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('EBABCEACBC8E432B95E9', 'CPG', 'CPGA', 'CPGAA', 'B4D41AAF551F48F0BF1D', '57D4CB1936854626B836', '21', 'CPGAA', 1, '断路器用真空灭弧室');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('B0E1F23A4EB143D49D67', 'CPG', 'CPGA', 'CPGAB', 'B4D41AAF551F48F0BF1D', '57D4CB1936854626B836', '22', 'CPGAB', 1, '接触器用真空灭弧室');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('F6255A1FC6074FDCACD8', 'CPG', 'CPGA', 'CPGAC', 'B4D41AAF551F48F0BF1D', '57D4CB1936854626B836', '23', 'CPGAC', 1, '负荷开关用真空灭弧室');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('D8E501CB844A4B8A82BB', 'CPL', 'CPLA', 'CPLAA', 'EC9850802B174AD7818D', '9330191E028D4D4E93C8', '24', 'CPLAA', 1, '轨装接线端子');
INSERT INTO NC_THIRD_TYPE (PK_THIRD_TYPE, PROD_TYPE, C_PROD_TYPE_C1, P_NAME, PK_FIRST_TYPE, PK_SECOND_TYPE, NC_THIRD_CODE, NC_THIRD_NAME, IS_ENABLE, NC_DESCRIPT) VALUES ('01D1F226ACBC4FB0BBAF', 'CPL', 'CPLA', 'CPLAB', 'EC9850802B174AD7818D', '9330191E028D4D4E93C8', '25', 'CPLAB', 1, '印制电路板接线端子');


--单位表
create table NC_UNITS_TYPE 
(
   PK_UNITS_TYPE        CHAR(20 CHAR)        not null,
   UNIT_CODE            VARCHAR2(20 char),
   NC_UNITS_CODE        CHAR(50 CHAR),
   NC_UNITS_NAME        CHAR(200 CHAR),
   NC_UNITS_DESCRIPTION VARCHAR(100),
   NC_UNITS_DISP        CHAR(200 CHAR),
   constraint PK_NC_UNITS_TYPE primary key (PK_UNITS_TYPE)
);

Insert into NC_UNITS_TYPE
select substr(SYS_GUID(),1,20), u.unit_code ,RANK() over(order by u.unit_code asc),
u.display_string,u.description,u.display_string
from units u;



--委托单前缀表
create  table NC_PROJ_PREFIX 
(
   PK_SAFE_TYPE         CHAR(20 CHAR)        not null,
   NC_SAFE_CODE         VARCHAR(20),
   NC_SAFE_NAME         VARCHAR(100),
   ISENABLE              SMALLINT,
   constraint PK_NC_PROJ_PREFIX primary key (PK_SAFE_TYPE)
);
INSERT INTO NC_PROJ_PREFIX (PK_SAFE_TYPE, NC_SAFE_CODE, NC_SAFE_NAME, ISENABLE) VALUES ('QAZWSXEDCRFVTGBYHN12', 'DEFAULT', 'A', 1);


--委托类型
create table NC_PROJ_TYPE 
(
   PK_PROJ_TYPE         CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   VERSION              NUMBER(10),
   PROJ_TYPE_CODE       VARCHAR(20),
   ISENABLE             SMALLINT,
   ISDEFAULT			SMALLINT,
   constraint PK_NC_PROJ_TYPE primary key (PK_PROJ_TYPE)
);


Insert into NC_PROJ_TYPE
select substr(SYS_GUID(),1,20),p.name,p.version,RANK() over(order by p.name asc),1,0
from project_template p
where p.group_name = 'DEFAULT';

UPDATE NC_PROJ_TYPE SET ISDEFAULT = 1 WHERE NAME = '常规检测';



--分析中的值类型
create table NC_RESULT_TYPE 
(
   PK_RESULT_TYPE       CHAR(20 CHAR)        not null,
   NC_RESULT_CODE       CHAR(50 CHAR),
   NC_RESULT_NAME       CHAR(200 CHAR),
   NC_RESULT_DESCRIPTION VARCHAR(100),
   NC_RESULT_NAMECN     CHAR(200 CHAR),
   constraint PK_NC_RESULT_TYPE primary key (PK_RESULT_TYPE)
);
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'1','N','Numberic','数值');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'2','T','TEXT','文本');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'3','L','List','列表');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'4','U','List Allow User Entry','列表允许用户输入');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'5','D','Date','日期');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'6','K','Caculated','计算型');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'7','F','File Name','文件名');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'8','E','Exponential','指数型');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'9','R','RTF Note','RTF备注');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'10','H','Html Note','HTML备注');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'11','S','Standard','标准品');
insert into NC_RESULT_TYPE values (substr(SYS_GUID(),1,20),'12','Y','DateTime','日期时间');

--企业标准基础表
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

insert into NC_BASEN_TYPE
select substr(SYS_GUID(),1,20), 
(select nbt.pk_basprod_type from NC_BASPROD_TYPE nbt where nbt.nc_basprodtype_name = b.description),
RANK() over(order by b.name,b.description asc),b.name,'','','','',''
from (select distinct p.description ,p.name
from product p where p.removed = 'F') b;



-- 试验前参数详单
create table NC_TEST_INIT 
(
   PK_TEST_INIT         CHAR(20 CHAR)        not null,
   PK_RESULT_TYPE       CHAR(20 CHAR),
   PK_UNITS_TYPE        CHAR(20 CHAR),
   TEST_INIT_CODE       CHAR(50 CHAR),
   TEST_INIT_NAME       CHAR(200 CHAR),
   NC_ENSTARD           NVARCHAR2(100),
   NC_ENTRY             NUMBER(10),
   NC_ANALYSIS_NAME     VARCHAR2(50 CHAR),
   NC_ANALYSIS_VERSION  NUMBER(10),
   NC_ANALYSIS_TYPE     VARCHAR2(1 CHAR),
   NC_SPEC_RULE         VARCHAR2(50 CHAR),
   NC_MIN_VALUE         VARCHAR2(53 CHAR),
   NC_MAX_VALUE         VARCHAR2(53 CHAR),
   NC_TEXT_VALUE        VARCHAR2(256 CHAR),
   NC_STAGE             VARCHAR2(20 CHAR),
   NC_SAMPLE_POINT      VARCHAR2(20 CHAR),
   NC_CONTACT_TYPE      VARCHAR2(254),
   NC_COIL_TYPE         VARCHAR2(100),
   NC_COIL_CURRENT      VARCHAR2(100),
   DEF1                 VARCHAR2(256),
   DEF2                 VARCHAR2(256),
   DEF3                 VARCHAR2(256),
   DEF4                 VARCHAR2(256),
   DEF5                 VARCHAR2(256),
   constraint PK_NC_TEST_INIT primary key (PK_TEST_INIT)
);

--插入数据
insert into NC_TEST_INIT
SELECT substr(SYS_GUID(),1,20) ,nrt.pk_result_type,u.pk_units_type,RANK() over(order by p.product,p.entry_code asc),c.name,
p.product,p.entry_code,p.analysis,p.version,'',p.spec_rule, p.min_value,p.max_value,p.text_value,p.stage,p.sampling_point,
c.c_contact_type,c.C_COIL_TYPE,p.grade,c.order_number,'5EA5CA7222E54CD38815','','',''
  FROM COMPONENT c, product_spec p,nc_result_type nrt,NC_UNITS_TYPE u
 WHERE c.ANALYSIS = '继电器企标初始参数'
   and c.version = 1
   and c.analysis = p.analysis
   and c.name = p.component
   and trim(nrt.nc_result_name) = c.result_type
   and u.unit_code = c.units;

 





--产品属性
create table NC_STATUS_TYPE 
(
   PK_STATUS_HANDLE     CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_STATUS_CODE       VARCHAR(20),
   NC_STATUS_NAME       VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_STATUS_TYPE primary key (PK_STATUS_HANDLE)
);

insert into NC_STATUS_TYPE 
select substr(SYS_GUID(),1,20) , l.name,le.list,le.name,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name 
and l.name = '产品属性'
and le.value like '%老产品%';




--产品鉴定类型
create table NC_PRODAUTH_TYPE 
(
   PK_PRODAUTH_TYPE     CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_PRODAUTH_CODE     VARCHAR(20),
   NC_PRODAUTH_NAME     VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_PRODAUTH_TYPE primary key (PK_PRODAUTH_TYPE)
);

insert into NC_PRODAUTH_TYPE 
select substr(SYS_GUID(),1,20) , l.name,le.list,le.name,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name 
and l.name like '产品鉴定类型'
and le.value like '%A类%';


--安全认证类型
create table NC_SAFE_TYPE 
(
   PK_SAFE_TYPE         CHAR(20 CHAR)        not null,
   LIST                 VARCHAR2(20 char),
   NAME                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_SAFE_CODE         VARCHAR(20),
   NC_SAFE_NAME         VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_SAFE_TYPE primary key (PK_SAFE_TYPE)
);

insert into NC_SAFE_TYPE 
select substr(SYS_GUID(),1,20) , l.name,le.name,le.list,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name 
and l.name like '安全认证类型'
and le.value like '%A类%';



--报告类型
create table NC_REPORT_TYPE 
(
   PK_REPORT_TYPE       CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   RP_REPORT_CODE       VARCHAR2(20),
   RP_REPORT_NAME       VARCHAR2(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_REPORT_TYPE primary key (PK_REPORT_TYPE)
);

insert into NC_REPORT_TYPE 
select substr(SYS_GUID(),1,20) , l.name,le.list,le.name,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name and l.name = 'C_COA_FORMAT';



--报告语言
create table NC_REPORT_LANG 
(
   PK_REPORT_LANG       CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   RP_REPORT_CODE       VARCHAR2(20),
   RP_REPORT_NAME       VARCHAR2(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_REPORT_LANG primary key (PK_REPORT_LANG)
);

insert into NC_REPORT_LANG 
select substr(SYS_GUID(),1,20) , l.name,le.list,le.name,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name and l.name = 'C_COA_LANGUAGE';


--验后样品处理
create table NC_RATAIN_HANDLE 
(
   PK_RATAIN_HANDLE     CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_RATAIN_CODE       VARCHAR(20),
   NC_RATAIN_NAME       VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_RATAIN_HANDLE primary key (PK_RATAIN_HANDLE)
);


insert into NC_RATAIN_HANDLE 
select substr(SYS_GUID(),1,20) , l.name,le.list,le.name,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name and l.name = 'C_RATAIN_HANDLE';



--客户类型
create table NC_CUSTOMER_TYPE 
(
   PK_CUSTOMER_TYPE     CHAR(20 CHAR)        not null,
   LIST                 VARCHAR2(20 char),
   NAME                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_CUSTOMER_CODE     VARCHAR(20),
   NC_CUSTOMER_NAME     VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_CUSTOMER_TYPE primary key (PK_CUSTOMER_TYPE)
);
   
insert into NC_CUSTOMER_TYPE 
select substr(SYS_GUID(),1,20) , l.name,le.name,le.list,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name 
and l.name = '客户要求-需求类型'
and le.value like '%客户%';




--样品组别
create table NC_SAMPLE_GROUP 
(
   PK_SAMPLE_GROUP      CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_SAMPLE_CODE       VARCHAR(20),
   NC_SAMPLE_NAME       VARCHAR(20),
   ISENABLE             SMALLINT,
   constraint PK_NC_SAMPLE_GROUP primary key (PK_SAMPLE_GROUP)
);
insert into NC_SAMPLE_GROUP 
select substr(SYS_GUID(),1,20) , l.name,le.name,le.list,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name 
and l.name = 'C_SAMPLE_GROUP';



--检验性质
create table NC_TEST_TYPE 
(
   PK_RATAIN_HANDLE     CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_RATAIN_CODE       VARCHAR(20),
   NC_RATAIN_NAME       VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_TEST_TYPE primary key (PK_RATAIN_HANDLE)
);

insert into NC_TEST_TYPE 
select substr(SYS_GUID(),1,20) , l.name,le.list,le.name,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name 
and l.name like '检验性质类型'
and le.value like '%A类%';



--触点牌号
create table NC_CONTACT_BRAND 
(
   PK_CONTACT_BRAND     CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_CONTACT_CODE      VARCHAR(20),
   NC_CONTACT_NAME      VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_CONTACT_BRAND primary key (PK_CONTACT_BRAND)
);

insert into NC_CONTACT_BRAND 
select substr(SYS_GUID(),1,20) , l.name,le.list,le.name,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name 
and l.name = 'C_CONTACT_BRAND';



--测试类型
create table NC_TESTREQUEST_TYPE 
(
   PK_TESTREQUEST_TYPE  CHAR(20 CHAR)        not null,
   NAME                 VARCHAR2(20 char),
   LIST                 VARCHAR2(20 char),
   LIS_NAME             VARCHAR2(20 char),
   NC_TESTREQUEST_CODE  VARCHAR(20),
   NC_TESTREQUEST_NAME  VARCHAR(100),
   ISENABLE             SMALLINT,
   constraint PK_NC_TESTREQUEST_TYPE primary key (PK_TESTREQUEST_TYPE)
);
  
insert into NC_TESTREQUEST_TYPE 
select substr(SYS_GUID(),1,20) , l.name,le.list,le.name,RANK() over(order by le.value asc),le.value,1 
from list l ,list_entry le 
where le.list = l.name 
and l.name = '产品鉴定类型'
and le.value like '%DV%';


----删除重复数据

delete  NC_BASPROD_NAME u 
  where u.nc_basprod_name in (select u.nc_basprod_name from 
    NC_BASPROD_NAME u group by u.nc_basprod_name having count(u.nc_basprod_name) > 1) 
      and rowid not in (select min(rowid) from 
        NC_BASPROD_NAME u group by u.nc_basprod_name having count(u.nc_basprod_name )>1) ;

--样品添加参照
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
) b ;




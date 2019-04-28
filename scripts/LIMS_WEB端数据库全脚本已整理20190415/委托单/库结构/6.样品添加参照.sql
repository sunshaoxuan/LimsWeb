--��Ʒ��Ӳ���
create table NC_SAMPLE_INFO 
(
   PK_SAMPLE_INFO       CHAR(20 CHAR)        not null,
   PK_PROD_TYPE         CHAR(20 CHAR),
   SAMPLE_INFO_CODE     VARCHAR2(200 CHAR),
   "def1"               VARCHAR2(200 CHAR),
   "def2"               VARCHAR2(200 CHAR),
   "def3"               VARCHAR2(200 CHAR),
   "def4"               VARCHAR2(200 CHAR),
   "def5"               VARCHAR2(200 CHAR),
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
---
insert into NC_SAMPLE_INFO
select distinct
substr(SYS_GUID(),1,20) , b."��Ʒ���������",b."���",b."def1",b."def2",b."def3",b."def4",b."def5",b."��Ʒ����",
b."��Ʒ���",b."��Ʒϵ��",b."���",b."Test_list��",b."����",b."�ṹ����",b."��������",b."�¶�",dr
from 
(select distinct
npt.pk_prod_type  "��Ʒ���������",
RANK() over(order by p.name,pg.sampling_point asc) "���",
'' "def1" ,'' "def2",'' "def3",'' "def4",'' "def5" ,p.code "��Ʒ����",
p.c_prod_type_c1 "��Ʒ���",p.description "��Ʒϵ��",p.name "���",p.test_list "Test_list��",pg.sampling_point "����",
pg.grade "�ṹ����",p.c_allowed_contact "��������",pgs.stage "�¶�",0 dr
from product p , product_grade pg,prod_grade_STAGE pgs,NC_PROD_TYPE npt
where p.ACTIVE = 'T' and p.removed = 'F'
and p.name = pg.product and p.name = pgs.product 
and p.c_prod_type_c1 = npt.c_prod_type_c1 
--and (p.description = 'HF115F-/Q������0060��')
) b;
